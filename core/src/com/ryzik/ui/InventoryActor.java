package com.ryzik.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ryzik.type.Inventory;

public class InventoryActor extends Actor {
    private final Inventory inventory;
    private HotBar hotBar;
    private ItemSlotPanel panel;

    public InventoryActor(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
