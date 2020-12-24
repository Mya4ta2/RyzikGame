package com.ryzik.type;

import com.ryzik.content.Items;

public class Inventory {
    private final int width;
    private final int height;

    private ItemStack[] hotBar;
    private ItemStack[][] inventory;

    public Inventory(int width, int height) {
        this.width = width;
        this.height = height;

        if (height > 1) {
            hotBar = new ItemStack[width+1];
            inventory = new ItemStack[width+1][height];
            fill();
        }
    }

    public void fill() {
        for (int i = 0; i < hotBar.length; i++) {
            hotBar[i] = new ItemStack(Items.air,128);
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                inventory[j][i] = new ItemStack(Items.air, 128);
            }
        }
    }

    public ItemStack[] getHotBar() {
        return hotBar;
    }

    public ItemStack[][] getInventory() {
        return inventory;
    }
}
