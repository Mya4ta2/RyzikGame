package com.ryzik.type;

import com.badlogic.gdx.math.Vector2;

public class Tiles {
    final Tile[] array;
    private final int width, height;

    public Tiles(int width, int height) {
        this.array = new Tile[width * height];
        this.width = width;
        this.height = height;

        fill();
    }

    public Tile get(int x, int y) {
        return array[y * width + x];
    }

    public void set(int y, int x, Tile tile) {
        array[y * width + x] = tile;
    }

    public void fill(){
        for (int i = 0; i < array.length; i++){
            array[i] = new Tile(i % width, i / width);
        }
    }

    public Tile[] getArray() {
        return array;
    }
}
