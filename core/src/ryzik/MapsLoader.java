package ryzik;

import com.badlogic.gdx.utils.Array;
import ryzik.ai.EnemyController;
import ryzik.content.*;
import ryzik.type.item.Inventory;
import ryzik.type.world.Building;
import ryzik.type.world.Map;
import ryzik.type.world.Tilemap;
import ryzik.type.world.WaveSpawner;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.mob.Player;
import ryzik.type.world.mob.Weapon;

import java.util.Random;

public class MapsLoader {
    public Array<Map> maps;

    public MapsLoader() {
        maps = new Array<>();
    }

    public void load() {
        Map map = new Map("test map");
        map.setTilemap(new Tilemap(70,50));

        for (int x = 0; x < map.getTilemap().getWidth(); x++) {
            for (int y = 0; y < map.getTilemap().getHeight(); y++) {
                map.getTilemap().get(x, y).floor = Floors.grass;
            }
        }

        int homeposX = 35;
        int homeposY = 25;

        map.getTilemap().get(homeposX + 3, homeposY).building = null;

        map.createBuilding(map.getTilemap().get(homeposX + 3, homeposY + 3), Blocks.eat, Teams.orange);

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            map.createBuilding(map.getTilemap().get(
                    random.nextInt(map.getTilemap().getWidth()),
                    random.nextInt(map.getTilemap().getHeight())
            ), Blocks.tree, Teams.gray);
        }

        Building building = new WaveSpawner(Blocks.waveSpawner, MobTypes.cockroach, 5);
        map.addBuilding(map.getTilemap().get(homeposX - 3, homeposY - 8), building, Teams.red);

        map.getTilemap().get(4,3).block = Blocks.tree;

        maps.add(map);
    }
}
