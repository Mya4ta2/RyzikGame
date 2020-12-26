package com.ryzik.type;

import com.ryzik.Vars;
import com.ryzik.content.Items;
import com.ryzik.ui.ItemSlot;

public class Inventory {
    private final int width;
    private final int height;

    private ItemStack[] hotBar;
    private ItemStack[][] inventory;

    public Inventory(int width, int height) {
        this.width = width;
        this.height = height;

        if (height > 1) {
            hotBar = new ItemStack[width];
            inventory = new ItemStack[width][height];
            fill();
        }
    }

    public void fill() {
        for (int i = 0; i < hotBar.length; i++) {
            hotBar[i] = new ItemStack(Items.air,0);
        }

        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                inventory[i][j] = new ItemStack(Items.air, 0);
            }
        }
    }

    public void raiseItem(DroppedItem item) {
        ItemStack slot;

        if (findHotBarSimilarItemSlot(item.getItem()) != null) {
            slot = findHotBarSimilarItemSlot(item.getItem());
            slot.setAmount(slot.getAmount() + item.getAmount());
            Vars.world.getDroppedItems().getArray().removeValue(item,true);
            return;
        }

        if (getHotBarAvailableSlot() != null) {
            slot = getHotBarAvailableSlot();
            slot.setAmount(item.getAmount());
            slot.setItem(item.getItem());
            Vars.world.getDroppedItems().getArray().removeValue(item,true);
            return;
        }

        if (findInventorySimilarItemSlot(item.getItem()) != null) {
            slot = findInventorySimilarItemSlot(item.getItem());
            slot.setAmount(slot.getAmount() + item.getAmount());
            Vars.world.getDroppedItems().getArray().removeValue(item,true);
            return;
        }

        if (getInventoryAvailableSlot() != null) {
            slot = getInventoryAvailableSlot();
            slot.setAmount(item.getAmount());
            slot.setItem(item.getItem());
            Vars.world.getDroppedItems().getArray().removeValue(item,true);
            return;
        }
    }

    public boolean hotBarAvailable() {
        for (int i = 0; i < hotBar.length; i++) {
            if (hotBar[i].getItem() == Items.air) {
                return true;
            }
        }
        return false;
    }

    public ItemStack getHotBarAvailableSlot() {
        for (int i = 0; i < hotBar.length; i++) {
            if (hotBar[i].getItem() == Items.air) {
                return hotBar[i];
            }
        }
        return null;
    }

    public ItemStack getInventoryAvailableSlot() {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j].getItem() == Items.air) {
                    return inventory[i][j];
                }
            }
        }
        return null;
    }

    public ItemStack findHotBarSimilarItemSlot(Item item) {
        for (int i = 0; i < hotBar.length; i++) {
            if (hotBar[i].getItem() == item && hotBar[i].getAmount() < item.getMaxStackSize()) {
                return hotBar[i];
            }
        }
        return null;
    }

    public ItemStack findInventorySimilarItemSlot(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j].getItem() == item && inventory[i][j].getAmount() < item.getMaxStackSize()) {
                    return inventory[i][j];
                }
            }
        }
        return null;
    }

    public ItemStack[] getHotBar() {
        return hotBar;
    }

    public ItemStack[][] getInventory() {
        return inventory;
    }
}
