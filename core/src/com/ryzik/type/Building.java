package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Building implements Entity, Draw {
    private int x;
    private int y;
    private float health;
    private Block block;
    private Tile tile;

    public Building(Block block) {
        this.block = block;
        health = block.getHealth();
    }

    @Override
    public void draw(SpriteBatch batch) {
        block.draw(batch, x, y);
    }

    @Override
    public void update(float delta) {

    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        x = tile.getX();
        y = tile.getY();
        this.tile = tile;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
