package ryzik.type.world.bounds;

import com.badlogic.gdx.math.Polygon;
import ryzik.type.world.Building;

public class BuildingBounds extends Bounds {
    public final Building building;
    public BuildingBounds(float width, float height, Building building) {
        super(width, height);
        this.building = building;
    }

    public BuildingBounds(Polygon shape, Building building) {
        super(shape);
        this.building = building;
    }
}
