package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ryzik.Vars;
import com.ryzik.content.Items;

public class Item {
    private final String name;
    private int maxStackSize = Vars.STACK_SIZE;

    private TextureRegion texture = Vars.ERROR_TEXTURE;

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

    public String getName() {
        return name;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public void setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }
}
