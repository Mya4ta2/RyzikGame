package ryzik.type.world.item.weapon;

import com.badlogic.gdx.math.Vector2;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.type.world.Building;
import ryzik.type.world.bounds.Bounds;
import ryzik.type.world.mob.*;

public class RangedType extends WeaponType {
    public RangedType(String name) {
        super(name);
    }

    @Override
    public void draw(Weapon weapon) {
        weapon.sprite.draw(Draw.batch);
    }

    @Override
    public void update(Weapon weapon, float delta) {

    }

    @Override
    public void attack(Weapon weapon, Mob mob) {

    }

    @Override
    public void attack(Weapon weapon, Building building) {

    }

    @Override
    public void onOverlaps(Weapon weapon, Bounds bounds) {

    }

    @Override
    public void attack(Weapon weapon) {
        final Bullet bullet = new Bullet() {
            {
                type = new BulletType("dirt") {
                    @Override
                    public void draw(Vector2 position) {
                        super.draw(position);
                        Draw.batch.draw(Vars.atlas.errorTexture, position.x * Vars.TileSize, position.y * Vars.TileSize);
                    }
                };
            }
        };

        bullet.position.set(weapon.position);
        bullet.velocity.add(100, 100);
        Vars.world.addMob(bullet);
    }
}
