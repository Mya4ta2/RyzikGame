package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ryzik.content.Blocks;
import com.ryzik.content.Floors;

public class Tile {

    private int x, y; // position

    private Block block = Blocks.air;
    private Floor floor = Floors.air;

    public void draw(SpriteBatch batch) {
        if (block != Blocks.air) block.draw(batch,x,y);
        if (floor != Floors.air) floor.draw(batch,x,y);
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile() {
        this.x = 0;
        this.y = 0;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
