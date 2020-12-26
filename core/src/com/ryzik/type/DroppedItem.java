package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ryzik.Vars;
import com.ryzik.content.Items;
import com.ryzik.ctype.MappableContent;

public class DroppedItem implements Entity, Draw {
    private Item item;
    private float x,y;

    public DroppedItem(World world, Item item, float x, float y) {
        this.item = item;
        this.x = x;
        this.y = y;
        world.getDroppedItems().getArray().add(this);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (item != Items.air)
            batch.draw(item.getTexture(), this.x * Vars.TILE_SIZE, this.y * Vars.TILE_SIZE);
    }

    @Override
    public void update(float delta) {

    }
}
