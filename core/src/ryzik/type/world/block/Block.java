package ryzik.type.world.block;

import com.badlogic.gdx.graphics.Texture;
import ryzik.Draw;
import ryzik.Vars;

public class Block {
    public final String name;
    public Texture texture;
    public float health;
    public float width = 1, height = 1;

    public Block(String name) {
        this.name = name;

        texture = Vars.atlas.find("block/" + name);
    }

    public void draw(int x, int y) {
        Draw.batch.draw(
                texture,
                x * Vars.TileSize,
                y * Vars.TileSize,
                Vars.TileSize,
                Vars.TileSize
        );
    }
}
