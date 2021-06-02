package ryzik.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.Vars;
import ryzik.type.item.Inventory;

public class InventoryActor {
    private final Inventory inventory;

    private ItemSlotActor[][] inventorySlots;
    private HotBar hotBar;

    private Table inventoryTable;
    private float slotSize;

    public InventoryActor(Inventory inventory, float slotSize) {
        this.inventory = inventory;
        this.slotSize = slotSize;
        this.inventorySlots = new ItemSlotActor[inventory.getWidth()][inventory.getHeight()];

        hotBar = new HotBar(inventory, slotSize);
        inventoryTable = new Table();

        fill();
    }

    public void fill() {
        for (int x = 0; x < inventorySlots.length; x++) {
            for (int y = 0; y < inventorySlots[x].length; y++) {
                inventorySlots[x][y] = new ItemSlotActor(Vars.atlas.find("itemslot"), Vars.atlas.find("activeItemslot"));
                inventorySlots[x][y].itemStack = inventory.getInventory()[x][y];
                inventorySlots[x][y].setSize(64, 64);
                inventoryTable.add(inventorySlots[x][y]);
                inventoryTable.add(new Separator(3, 3));
            }
            inventoryTable.row();
            if (x < inventorySlots.length - 1)
                inventoryTable.add(new Separator(3, 3)).row();
        }
    }

    public Table getHotbarTable() {
        return hotBar.getTable();
    }

    public Table getInventoryTable() {
        return inventoryTable;
    }
}
