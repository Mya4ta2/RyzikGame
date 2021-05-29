package ryzik.type.world.mob;

import com.badlogic.gdx.math.Vector2;
import ryzik.content.Teams;
import ryzik.type.*;
import ryzik.type.world.Building;
import ryzik.type.world.Mobs;
import ryzik.type.world.Team;
import ryzik.type.world.bounds.Bounds;
import ryzik.type.world.bounds.BuildingBounds;
import ryzik.type.world.bounds.MobBounds;

public class Mob implements Entity {
    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    public Vector2 oldPosition = new Vector2();
    public MobBounds bounds;
    public MobType type;
    public Weapon currentWeapon;
    public MobController controller;
    public Mobs mobs;
    public Team team = Teams.gray;

    public float health;
    public boolean dead;

    public Event onMove = new Event();
    public Event onHealthChange = new Event();

    public Mob() {
        bounds = new MobBounds(1, 1, this);
    }

    public Mob(MobType type) {
        this.type = type;

        bounds = new MobBounds(type.getWidth(), type.getHeight(), this);
        health = type.getHealth();
        onHealthChange.fire();

        if (type.isEnemy()) {
            bounds.onOverlaps.on(new OverlapsTask() {
                @Override
                public void run(Bounds bounds) {
                    if (bounds instanceof MobBounds) {
                        Mob mob = ((MobBounds) bounds).mob;

                        //TODO сделать команды (красная синяя голубая...) вместо mob.type.isEnemy()
                        if (mob.type != null && !mob.type.isEnemy()) {
                            attack(mob);
                        }
                    }

                    if (bounds instanceof BuildingBounds) {
                        Building building = ((BuildingBounds) bounds).building;

                        if (building.team != team) {
                            attack(building);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void update(float delta) {
        if (!velocity.isZero()) onMove.fire();

        oldPosition.set(position);
        position.add(velocity.scl(delta));
        bounds.setPosition(position.x, position.y);

        if (controller != null && !dead) controller.update();
        if (currentWeapon != null) {
            currentWeapon.position.set(position.x + (16/32f), position.y + (16/32f));
            currentWeapon.update(delta);
        };
    }

    public void draw() {
        if (type != null)
            type.draw(position);
    }

    public void applyDamage(float damage) {
        health -= damage;

        if (health < 0) dead();

        onHealthChange.fire();
    }

    public void attacked(Mob mob) {
        velocity.add(
                (position.x - mob.position.x) * 8,
                (position.y - mob.position.y) * 8
        );
    }

    public void attack(Mob mob) {
        if (currentWeapon != null && currentWeapon.reloaded) {
            currentWeapon.attack(mob);
            mob.attacked(this);
        }
    }

    public void attack(Building building) {
        if (currentWeapon != null && currentWeapon.reloaded) {
            currentWeapon.attack(building);
        }
    }

    public void respawn() {
        dead = false;
        health = type != null ? type.getHealth() : 0;
        onHealthChange.fire();
        mobs.getArray().add(this);
        if (controller != null)
            controller.init();
    }

    public void dead() {
        dead = true;
        health = 0;
        onHealthChange.fire();
        mobs.getArray().removeValue(this,true);
        if (controller != null)
            controller.dispose();
    }
}
