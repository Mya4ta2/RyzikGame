package ryzik.type.item;

import ryzik.content.Items;
import ryzik.type.world.item.Item;
import ryzik.type.world.item.ItemStack;

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

    public boolean raiseItem(ItemStack item) {
        if (getHotBarSimilarItemSlot(item.getItemType()) != null) {
            getHotBarSimilarItemSlot(item.getItemType()).add(item.getAmount());
            return true;
        }

        if (getHotBarEmptySlot() != null) {
            getHotBarEmptySlot().set(item);
            return true;
        }

        if (getInventorySimilarItemSlot(item.getItemType()) != null) {
            getInventorySimilarItemSlot(item.getItemType()).add(item.getAmount());
            return true;
        }

        if (getInventoryAvailableSlot() != null) {
            getInventoryAvailableSlot().set(item);
            return true;
        }

        return false;
    }

    public ItemStack getHotBarEmptySlot() {
        for (int i = 0; i < hotBar.length; i++) {
            if (hotBar[i].getItemType() == Items.air) {
                return hotBar[i];
            }
        }
        return null;
    }

    public ItemStack getHotBarSimilarItemSlot(Item item) {
        for (int i = 0; i < hotBar.length; i++) {
            if (hotBar[i].getItemType() == item && hotBar[i].getAmount() < item.stackSize) {
                return hotBar[i];
            }
        }

        return null;
    }

    public ItemStack getInventoryAvailableSlot() {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j].getItemType() == Items.air) {
                    return inventory[i][j];
                }
            }
        }
        return null;
    }

    public ItemStack getInventorySimilarItemSlot(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j].getItemType() == item && inventory[i][j].getAmount() < item.stackSize) {
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
