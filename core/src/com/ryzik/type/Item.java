package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ryzik.content.Items;

public class Item {
    private final String name;

    private TextureRegion texture;

    public Item(String name) {
        this.name = name;
        Items.items.add(this);
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
}
