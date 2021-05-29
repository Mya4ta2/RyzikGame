package ryzik.type.world;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Tilemap {
    final Tile[] array;
    private final int width, height;

    public Tilemap(int width, int height) {
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

    public boolean inBounds(Vector2 position) {
        if (position.x < 0 || position.y < 0) return false;
        return !(position.x > width - 1) && !(position.y > height - 1);
    }

    public void clearTiles() {
        for (Tile tile : array) {
            tile.previous = null;
        }
    }

    public Tile[] getArray() {
        return array;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
