package ryzik.type.rounding;

import com.badlogic.gdx.graphics.Texture;
import ryzik.content.Blocks;
import ryzik.type.world.Tile;
import ryzik.type.world.Tilemap;

public class Rounding {
    private RoundingAtlas roundingAtlas;
    private Tilemap tilemap;
    private Tile tile;
    private Texture currentTexture;

    public Rounding(RoundingAtlas roundingAtlas, Tilemap tilemap, Tile tile) {
        this.roundingAtlas = roundingAtlas;
        currentTexture = roundingAtlas.up;
        this.tilemap = tilemap;
        this.tile = tile;
    }

    public void update() {

    }

    public Tilemap getTilemap() {
        return tilemap;
    }

    public void setTilemap(Tilemap tilemap) {
        this.tilemap = tilemap;
    }

    public Texture getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(Texture currentTexture) {
        this.currentTexture = currentTexture;
    }
}
