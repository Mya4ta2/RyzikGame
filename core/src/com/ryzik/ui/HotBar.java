package com.ryzik.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ryzik.content.Items;
/*
need refactor, make slots position setting with Table
 */
public class HotBar extends Actor {
    private final int size;
    private final Texture slotTexture;
    private final Texture activeSlotTexture;
    private final BitmapFont font;
    private GlyphLayout glyphLayout;

    private ItemSlot[] slots;
    private int selectedSlot;
    private float scroll;

    public HotBar(int size, Texture slotTexture, Texture activeSlotTexture, BitmapFont font) {
        this.size = size;
        this.slotTexture = slotTexture;
        this.font = font;

        slots = new ItemSlot[size];
        this.activeSlotTexture = activeSlotTexture;

        glyphLayout = new GlyphLayout();

        fill();
        setSlotsPosition();
        setDefaultPos();
        setDefaultSize();
    }

    public HotBar(int size, Texture slotTexture, Texture activeSlotTexture) {
        this.size = size;
        this.slotTexture = slotTexture;
        this.font = new BitmapFont();

        slots = new ItemSlot[size];
        this.activeSlotTexture = activeSlotTexture;

        glyphLayout = new GlyphLayout();

        fill();
        setSlotsPosition();
        setDefaultPos();
        setDefaultSize();

        slots[0].setItem(Items.air);
        setSelectedSlot(0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < slots.length; i++) {
            slots[i].draw(batch,parentAlpha);
        }

        if (slots[selectedSlot].getItem() != Items.air) {

            float x = (getX() + slots[0].getWidth() * slots.length / 2); // center position on slots

            font.draw(batch,String.valueOf(slots[selectedSlot].getItem().getName()),
                    x + (glyphLayout.width / 2),
                    slots[slots.length-1].getTop() + (getHeight() - glyphLayout.height));
        }
    }

    public void setSelectedSlot(int selectedSlot) {
        for (int i = 0; i < slots.length; i++) {
            slots[i].setSelected(false);
        }
        this.selectedSlot = selectedSlot;
        slots[selectedSlot].setSelected(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (scroll > 0 && selectedSlot < slots.length-1) {
            scroll = 0;
            selectedSlot += 1;
        } else if (scroll > 0 && selectedSlot < slots.length) {
            selectedSlot = 0;
        }

        if (scroll < 0 && selectedSlot > 0) {
            scroll = 0;
            selectedSlot -= 1;
        } else if (scroll < 0 && selectedSlot == 0) {
            selectedSlot = slots.length-1;
        }

        setSelectedSlot(selectedSlot);
        glyphLayout.setText(font, slots[selectedSlot].getItem().getName());
        setSlotsPosition();
    }

    public float getScroll() {
        return scroll;
    }

    public void setScroll(float scroll) {
        this.scroll = scroll;
    }

    public void fill() {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new ItemSlot(slotTexture, activeSlotTexture);
        }
    }

    public void setSlotsPosition() {
        for (int i = 0; i < slots.length; i++) {
            slots[i].setPosition(getX() + (i * (slots[i].getWidth() + 4)), getY());
        }

        setWidth((slots[0].getWidth() + 4) * slots.length-1);
        setHeight(slots[0].getHeight());
    }

    public void setDefaultPos() {
        setX(0);
        setY(0);
    }

    public void setDefaultSize() {
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        setSlotsPosition();
    }

    @Override
    public void setX(float x, int alignment) {
        super.setX(x, alignment);
        setSlotsPosition();
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        setSlotsPosition();
    }

    @Override
    public void setY(float y, int alignment) {
        super.setY(y, alignment);
        setSlotsPosition();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setSlotsPosition();
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
        setSlotsPosition();
    }



    public int getSize() {
        return size;
    }

    public ItemSlot[] getSlots() {
        return slots;
    }

    public void setSlots(ItemSlot[] slots) {
        this.slots = slots;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }
}
