package com.ryzik.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.ryzik.Cursor;
import com.ryzik.content.Items;

public class ItemSlotInputListener extends InputListener {
    private ItemSlot slot;

    public ItemSlotInputListener(ItemSlot slot) {
        this.slot = slot;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (Cursor.item != null && slot.getItem() != Items.air) {
            slot.getItemStack().set(Items.air,0);
            Cursor.item = slot.getItemStack();
        }
        return super.touchDown(event, x, y, pointer, button);
    }
}
