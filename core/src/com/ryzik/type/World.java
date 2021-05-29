package com.ryzik.type;

import com.badlogic.gdx.math.Vector2;
import com.ryzik.content.Blocks;
import com.ryzik.content.Floors;
import com.ryzik.content.Items;
import com.ryzik.content.MobTypes;

public class World {
    private final Tiles tiles;
    private final Entitys entitys;
    private DroppedItems droppedItems;
    private String name;

    private Player player;
    private Mob mob;

    private final int width, height;

    public World(int width, int height, String name) {
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new Tiles(width,height);
        droppedItems = new DroppedItems();
        entitys = new Entitys();

        test();
    }

    // just debug
    public void test() {
        player = new Player();
        player.setPosition(new Vector2(width/2, height/2));

        mob = new Mob(MobTypes.testEnemy);
        mob.getPosition().set(new Vector2(width/2 + 5, height/2));

        Building furnace = new Building(Blocks.furnace);
        Building craftingTable = new Building(Blocks.craftingTable);

        furnace.setTile(tiles.get(width/2, height/2-5));
        craftingTable.setTile(tiles.get(width/2-1, height/2-5));

        tiles.get(width/2, height/2-5).setBuilding(furnace);
        tiles.get(width/2-1, height/2-5).setBuilding(craftingTable);

        for (int i = 0; i < Items.items.size(); i++) {
            new DroppedItem(this, new ItemStack(Items.items.get(i),1),15 + i,15);
        }
    }

    public void addEntity(Entity entity) {
        entitys.add(entity);
    }

    public Tiles getTiles() {
        return tiles;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DroppedItems getDroppedItems() {
        return droppedItems;
    }

    public String getName() {
        return name;
    }

    public Mob getMob() {
        return mob;
    }
}
