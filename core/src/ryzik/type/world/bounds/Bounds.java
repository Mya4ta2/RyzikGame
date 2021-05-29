package ryzik.type.world.bounds;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import ryzik.type.OverlapsEvent;

public class Bounds {
    public OverlapsEvent onOverlaps = new OverlapsEvent();
    public Polygon shape;

    public Bounds(float width, float height) {
        shape = new Polygon(new float[]{
                0, width,
                height, width,
                height, 0,
                0, 0
        });
    }

    public Bounds(Polygon shape) {
        this.shape = shape;
    }

    public boolean overlaps(Bounds bounds) {
        if (Intersector.overlapConvexPolygons(bounds.shape, shape)) {
            onOverlaps.fire(bounds);
            return true;
        }

        return false;
    }

    public void setPosition(float x, float y) {
        shape.setPosition(x, y);
    }
}
