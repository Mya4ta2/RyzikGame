package ryzik.content;

import com.badlogic.gdx.utils.Array;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.ctype.ContentList;
import ryzik.type.world.block.Block;
import ryzik.type.world.block.WaveSpawnerBlock;

public class Blocks implements ContentList {
    public static Block air, grass, stone, stoneBrick, tree, eat, waveSpawner;

    public static Array<Block> blocks;

    @Override
    public void load() {
        blocks = new Array<>();

        air = new Block("air");

        grass = new Block("grass");
        blocks.add(grass);

        stone = new Block("stone");
        blocks.add(stone);

        stoneBrick = new Block("stoneBrick");
        blocks.add(stoneBrick);

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
        blocks.add(tree);

        eat = new Block("eat") {
            {
                health = 500;
            }
        };
        blocks.add(eat);

        waveSpawner = new WaveSpawnerBlock("waveSpawner") {
            {
                spawnType = MobTypes.cockroach;
                timeBetweenWaves = 5;
                health = 10;
            }
        };
        blocks.add(waveSpawner);
    }
}
