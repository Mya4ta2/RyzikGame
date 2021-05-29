package ryzik.type.world.item.weapon;

import ryzik.type.world.Building;
import ryzik.type.world.item.Item;
import ryzik.type.world.bounds.Bounds;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.mob.Weapon;

public abstract class WeaponType extends Item {
    public float height = 1, width = 1;
    public int damage;
    public int discarding;
    public float reloadTime;

    public WeaponType(String name) {
        super(name);
    }

    public abstract void draw(Weapon weapon);

    public abstract void update(Weapon weapon, float delta);

    public abstract void onOverlaps(Weapon weapon, Bounds bounds);

    public abstract void attack(Weapon weapon, Mob mob);

    public abstract void attack(Weapon weapon, Building building);

    public abstract void attack(Weapon weapon);
}
