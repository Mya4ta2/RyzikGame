package ryzik.type.world.mob;

public class Bullet extends Mob {
    public BulletType type;

    @Override
    public void draw() {
        super.draw();

        type.draw(position);
    }
}
