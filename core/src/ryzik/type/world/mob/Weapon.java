package ryzik.type.world.mob;

import com.badlogic.gdx.graphics.g2d.Sprite;
import ryzik.type.OverlapsTask;
import ryzik.type.world.Building;
import ryzik.type.world.bounds.MobBounds;
import ryzik.type.world.item.Item;
import ryzik.type.world.item.weapon.WeaponType;
import ryzik.type.world.bounds.Bounds;

public class Weapon extends Mob {
    public WeaponType type;
    public Sprite sprite;
    public boolean reloaded;
    public float reloadTimer;
    public float angle;
    public float angleOnAttack;

    public Weapon(Item type) {
        this.type = (WeaponType) type;
        bounds = new MobBounds(this.type.width, this.type.height, this);
        sprite = new Sprite(type.texture);
        bounds.onOverlaps.on(new OverlapsTask() {
            @Override
            public void run(Bounds bounds) {
                onOverlaps(bounds);
            }
        });
    }

    public void onOverlaps(Bounds bounds) {
        type.onOverlaps(this, bounds);
    }

    @Override
    public void attack(Mob mob) {
        type.attack(this, mob);
    }

    @Override
    public void attack(Building building) {
        type.attack(this, building);
    }

    public void attack() {
        type.attack(this);
    }

    @Override
    public void draw() {
        type.draw(this);
    }

    @Override
    public void update(float delta) {
        type.update(this, delta);
    }
}
