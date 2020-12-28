package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ryzik.Vars;

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

        int healthPercent = (int) (health/block.getHealth() * 100);


        if (healthPercent <= 99 && healthPercent > 50) {
            batch.draw(Vars.breakTextures[0], x * Vars.TILE_SIZE, y * Vars.TILE_SIZE);
        } else if (healthPercent < 50 && healthPercent > 5) {
            batch.draw(Vars.breakTextures[1], x * Vars.TILE_SIZE, y * Vars.TILE_SIZE);
        } else if (healthPercent > 5 && healthPercent < 100) {
            batch.draw(Vars.breakTextures[2], x * Vars.TILE_SIZE, y * Vars.TILE_SIZE);
        } else if (healthPercent < 5){
            batch.draw(Vars.breakTextures[3], x * Vars.TILE_SIZE, y * Vars.TILE_SIZE);
        }
    }

    @Override
    public void update(float delta) {
        if (health <= 0) destroy();
    }

    public void destroy() {
        tile.setBuilding(null);
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
