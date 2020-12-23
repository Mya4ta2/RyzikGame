package com.ryzik.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ryzik.content.Items;
import com.ryzik.type.Item;
import com.ryzik.type.ItemStack;

public class ItemSlot extends Actor {
    private final Texture slot;
    private final Texture activeSlot;
    private boolean selected;

    private ItemStack items;
    private BitmapFont font;

    public ItemSlot(Texture slot, Texture activeSlot) {
        this.slot = slot;
        this.activeSlot = activeSlot;
        items = new ItemStack(Items.air, 0);
        font = new BitmapFont();

        setDefaultPos();
        setDefaultSize();

        //addListener(new ItemSlotInputListener(this));
    }

    public void setDefaultPos() {
        setX(100);
        setY(100);
    }

    public void setDefaultSize() {
        setWidth(42);
        setHeight(42);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (selected) {
            batch.draw(activeSlot,getX(),getY(), getWidth(), getHeight());
        } else {
            batch.draw(slot,getX(),getY(), getWidth(), getHeight());
        }

        if (items.getItem() != Items.air) {
            batch.draw(items.getItem().getTexture(),
                    getX() + 4,
                    getY() + 4,
                    getWidth() - 8,
                    getHeight() - 8);

            font.draw(batch,String.valueOf(items.getAmount()), getX() + 8, getY() + 16);
        }
    }

    public Item getItem() {
        return items.getItem();
    }

    public void setItem(Item item) {
        this.items.setItem(item);
    }

    public ItemStack getItemStack() {
        return items;
    }

    public void setItemStack(ItemStack items) {
        this.items = items;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}