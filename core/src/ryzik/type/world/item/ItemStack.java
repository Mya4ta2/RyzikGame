package ryzik.type.world.item;
import ryzik.type.world.mob.Weapon;
import ryzik.type.world.item.weapon.WeaponType;
import ryzik.type.world.mob.Mob;

public class ItemStack {
    private Item itemType;
    private Mob itemMob;
    private int amount;

    public ItemStack(Item item, int amount) {
        this.itemType = item;
        this.amount = amount;

        if (isWeapon()) itemMob = new Weapon(itemType);
    }

    public ItemStack set(Item item, int amount){
        this.itemType = item;
        this.amount = amount;

        if (isWeapon()) itemMob = new Weapon(itemType);
        return this;
    }

    public ItemStack set(ItemStack item){
        this.itemType = item.getItemType();
        this.amount = item.getAmount();

        if (isWeapon()) itemMob = new Weapon(itemType);
        return this;
    }

    public Item getItemType() {
        return itemType;
    }

    public void setItemType(Item item) {
        this.itemType = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void add(int amount) {
        this.amount += amount;
    }

    public boolean empty() {
        return amount <= 0;
    }

    public void rightMouseUseItem() {
        if (!empty() && itemType.consumable) {
            itemType.rightMouseUse();
            if (itemType.consumable) amount -= 1;
        }
    }

    public void leftMouseUseItem() {
        if (!empty()) {
           itemType.leftMouseUse();
           if (itemType.consumable) amount -= 1;
        }
    }

    public boolean isWeapon() {
        return itemType instanceof WeaponType;
    }

    public Mob getItemMob() {
        return itemMob;
    }
}
