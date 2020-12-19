package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.ryzik.Vars;
import com.ryzik.content.Blocks;
import com.ryzik.content.Floors;

public class Tile {

    private int x, y; // position

    private Block block = Blocks.air;
    private Floor floor = Floors.air;
    private Rectangle bounds = new Rectangle();

    public void draw(SpriteBatch batch) {
        if (floor != Floors.air) floor.draw(batch,x,y);
        if (block != Blocks.air) block.draw(batch,x,y);
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

        bounds.width = 1;
        bounds.height = 1;
        bounds.x = x;
        bounds.y = y;
    }

    public Tile() {
        this.x = 0;
        this.y = 0;

        bounds.width = 1;
        bounds.height = 1;
        bounds.x = x;
        bounds.y = y;
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
