package com.ryzik.type;

import com.ryzik.content.Blocks;
import com.ryzik.content.Floors;

public class World {
    private Tiles tiles;

    private Player player;

    private final int width, height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tiles(width,height);

        test();
    }

    // just debug
    public void test() {
        player = new Player();
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
}
