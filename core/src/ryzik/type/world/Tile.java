package ryzik.type.world;

import ryzik.Vars;
import ryzik.content.Blocks;
import ryzik.content.Floors;
import ryzik.io.Reads;
import ryzik.io.Writes;
import ryzik.type.rounding.Rounding;
import ryzik.type.world.block.Block;
import ryzik.type.world.floor.Floor;

public class Tile {
    public int x, y;
    public Block block = Blocks.air;
    public Floor floor = Floors.air;
    public Building building;

    public Rounding blockRounding;

    //for PathFinder.java
    public Tile previous;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updateRounding(Tilemap tilemap) {
        blockRounding = new Rounding(block.roundingAtlas, tilemap, this);
        blockRounding.update();
    }

    public void write(Writes writes) {
        writes.i(x);
        writes.i(y);
        writes.s(block.id);
        writes.s(floor.id);

        writes.s(building != null ? building.type.id : -1);

        if (building != null) {
            building.write(writes);
        }
    }

    public void read(Reads reads) {
        x = reads.i();
        y = reads.i();
        block = Vars.content.getByID(reads.s());
        floor = Vars.content.getByID(reads.s());

        // if building null, she contain -1
        short buildingTypeID = reads.s();

        if (buildingTypeID != -1) {
            building = Vars.content.<Block>getByID(buildingTypeID).getBuilding();
            building.read(reads);
        }
    }

    public boolean air() {
        return block == Blocks.air;
    }
}
