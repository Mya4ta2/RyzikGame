package com.ryzik.type;

import com.badlogic.gdx.math.Vector2;
import com.ryzik.content.Blocks;
import com.ryzik.content.Floors;
import com.ryzik.content.Items;

public class World {
    private Tiles tiles;
    private DroppedItems droppedItems;

    private Player player;

    private final int width, height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tiles(width,height);
        droppedItems = new DroppedItems();

        test();
    }

    // just debug
    public void test() {
        player = new Player();
        player.setPosition(new Vector2(15/2, 15/2));

        new DroppedItem(this, Items.test,5,10.3f);
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
}
