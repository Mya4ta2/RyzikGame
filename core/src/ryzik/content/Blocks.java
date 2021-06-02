package ryzik.content;

import ryzik.Draw;
import ryzik.Vars;
import ryzik.ctype.ContentList;
import ryzik.type.world.block.Block;

public class Blocks implements ContentList {
    public static Block air, grass, stone, stoneBrick, tree, eat, waveSpawner;

    @Override
    public void load() {
        air = new Block("air");

        grass = new Block("grass");

        stone = new Block("stone");

        stoneBrick = new Block("stoneBrick");

        tree = new Block("tree") {
            @Override
            public void draw(int x, int y) {
                Draw.batch.draw(
                        texture,
                        x * Vars.TileSize,
                        y * Vars.TileSize,
                        Vars.TileSize,
                        Vars.TileSize * 2
                );
            }
        };

        eat = new Block("eat") {
            {
                health = 500;
            }
        };

        waveSpawner = new Block("waveSpawner") {
            {
                health = 10;
            }
        };
    }
}
