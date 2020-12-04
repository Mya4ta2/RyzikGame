package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ryzik.Vars;
import com.ryzik.content.Floors;
import com.ryzik.ctype.MappableContent;

public class Floor implements MappableContent {
    private TextureRegion texture;
    private String name;

    private float width = 1, height = 1;

    public Floor(String name) {
        this.name = name;
        Floors.floors.add(this);
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

    @Override
    public void draw(SpriteBatch batch, int x, int y) {
        batch.draw(
                texture,
                x * Vars.TILE_SIZE,
                y * Vars.TILE_SIZE,
                width * Vars.TILE_SIZE,
                height * Vars.TILE_SIZE
        );
    }
}
