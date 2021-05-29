package ryzik.type.world.bounds;

import com.badlogic.gdx.math.Polygon;
import ryzik.type.world.bounds.Bounds;
import ryzik.type.world.mob.Mob;

public class MobBounds extends Bounds {
    public final Mob mob;
    public MobBounds(float width, float height, Mob mob) {
        super(width, height);
        this.mob = mob;
    }

    public MobBounds(Polygon shape, Mob mob) {
        super(shape);
        this.mob = mob;
    }
}
