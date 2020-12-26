package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.ryzik.Vars;
import com.ryzik.content.Items;
import com.ryzik.ctype.MappableContent;

public class DroppedItem implements Entity, Draw {
    private Item item;
    private float x,y;
    private Rectangle bounds;

    public DroppedItem(World world, Item item, float x, float y) {
        this.item = item;
        this.x = x;
        this.y = y;

        bounds = new Rectangle();
        bounds.x = (int) x;
        bounds.y = (int) y;
        bounds.width = 1;
        bounds.height = 1;

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

    public Item getItem() {
        return item;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
