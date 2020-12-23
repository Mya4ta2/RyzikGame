package com.ryzik.type;

public class ItemStack {
    private Item item;
    private int amount;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemStack set(Item item, int amount){
        this.item = item;
        this.amount = amount;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
