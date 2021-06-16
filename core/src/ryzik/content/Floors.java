package ryzik.content;

import com.badlogic.gdx.utils.Array;
import ryzik.ctype.ContentList;
import ryzik.type.world.floor.Floor;

public class Floors implements ContentList {
    public static Floor air, grass, stone;

    public static Array<Floor> floors;

    @Override
    public void load() {
        floors = new Array<>();

        air = new Floor("air");

        grass = new Floor("grass");
        floors.add(grass);

        stone = new Floor("stone");
        floors.add(stone);
    }
}
