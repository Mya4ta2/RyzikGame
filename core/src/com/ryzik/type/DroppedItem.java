package com.ryzik.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.ryzik.Vars;
import com.ryzik.content.Items;
import com.ryzik.ctype.MappableContent;

public class DroppedItem implements Entity, Draw {
    private ItemStack item;
    private float x,y;
    private Rectangle bounds;

    public DroppedItem(World world, ItemStack item, float x, float y) {
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
        if (item.getItem() != Items.air)
            batch.draw(item.getItem().getTexture(), this.x * Vars.TILE_SIZE, this.y * Vars.TILE_SIZE);
    }

    @Override
    public void update(float delta) {

    }

    public Item getItem() {
        return item.getItem();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getAmount() {
        return item.getAmount();
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
