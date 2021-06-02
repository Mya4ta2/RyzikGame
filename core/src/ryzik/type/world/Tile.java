package ryzik.type.world;

import ryzik.content.Blocks;
import ryzik.content.Floors;
import ryzik.type.world.block.Block;
import ryzik.type.world.floor.Floor;

public class Tile {
    public int x, y;
    public Block block = Blocks.air;
    public Floor floor = Floors.air;
    public Building building;

    //for PathFinder.java
    public Tile previous;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
}