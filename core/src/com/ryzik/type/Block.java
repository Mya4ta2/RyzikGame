package com.ryzik.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ryzik.Vars;
import com.ryzik.content.Blocks;
import com.ryzik.ctype.MappableContent;

public class Block implements MappableContent {
    private TextureRegion texture = Vars.ERROR_TEXTURE;
    private final String name;
    private int health;

    private float width = 1, height = 1;

    public Block(String name) {
        this.name = name;
        Blocks.blocks.add(this);
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void draw(SpriteBatch batch, int x, int y) {
        batch.draw(
                texture,
                x * Vars.TILE_SIZE,
                y * Vars.TILE_SIZE,
                width * Vars.TILE_SIZE,
                height * Vars.TILE_SIZE
        );
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
