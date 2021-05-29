package ryzik.type.world.item.weapon;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.type.world.Building;
import ryzik.type.world.bounds.Bounds;
import ryzik.type.world.bounds.BuildingBounds;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.bounds.MobBounds;
import ryzik.type.world.mob.Weapon;

public class SwordType extends WeaponType {
    public SwordType(String name) {
        super(name);
    }

    @Override
    public void draw(Weapon weapon) {
        Sprite sprite = weapon.sprite;
        float angleOnAttack = weapon.angleOnAttack;
        float spriteAngle = 0;

        sprite.setFlip(angleOnAttack < 270 && angleOnAttack > 90, false);

        if (weapon.reloaded) {
            spriteAngle = 0;
        } else {
            if (angleOnAttack < 270 && angleOnAttack > 90)
                spriteAngle = MathUtils.lerp(
                        (angleOnAttack + 120),
                        (angleOnAttack + 20),
                        weapon.reloadTimer / weapon.type.reloadTime
                );
            else
                spriteAngle = MathUtils.lerp(
                        (angleOnAttack + 20),
                        (angleOnAttack + 120),
                        weapon.reloadTimer / weapon.type.reloadTime
                );
            sprite.draw(Draw.batch);
        }

        sprite.setRotation(spriteAngle);

        float x = (float) Math.cos((spriteAngle + 90) * Math.PI / 180) + weapon.position.x;
        float y = (float) Math.sin((spriteAngle + 90) * Math.PI / 180) + weapon.position.y;

        sprite.setCenter(x * Vars.TileSize, y * Vars.TileSize);
    }

    @Override
    public void update(Weapon weapon, float delta) {
        if (!weapon.reloaded) {
            weapon.reloadTimer += delta;

            if (weapon.reloadTimer > weapon.type.reloadTime) {
                weapon.reloaded = true;
                weapon.reloadTimer = 0;
            }
        }

        weapon.bounds.shape.setRotation(
                MathUtils.lerp(-(weapon.angleOnAttack + 20),
                        (weapon.angleOnAttack + 120),
                        weapon.reloadTimer / reloadTime)
        );
        weapon.bounds.setPosition(weapon.position.x, weapon.position.y);
    }

    @Override
    public void attack(Weapon weapon, Mob mob) {
        mob.applyDamage(damage);
        mob.attacked(weapon);
    }

    @Override
    public void attack(Weapon weapon, Building building) {
        building.applyDamage(damage);
    }

    @Override
    public void onOverlaps(Weapon weapon, Bounds bounds) {
        if (bounds instanceof MobBounds) {
            if (!weapon.reloaded)
                weapon.attack(((MobBounds) bounds).mob);
        }

        if (bounds instanceof BuildingBounds) {
            if (!weapon.reloaded)
                weapon.attack(((BuildingBounds) bounds).building);
        }
    }

    @Override
    public void attack(Weapon weapon) {
        if (weapon.reloaded) weapon.angleOnAttack = weapon.angle;
        weapon.reloaded = false;
    }
}
