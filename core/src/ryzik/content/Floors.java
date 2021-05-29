package ryzik.content;

import ryzik.ctype.ContentList;
import ryzik.type.world.floor.Floor;

public class Floors implements ContentList {
    public static Floor air, grass, stone;

    @Override
    public void load() {
        air = new Floor("air");
        grass = new Floor("grass");
        stone = new Floor("stone");
    }
}
