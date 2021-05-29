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

        int homeposX = 25;
        int homeposY = 10;

        for (int i = 0; i < 7; i++) {
            for (int y = 0; y < 7; y++) {
                map.createBuilding(map.getTilemap().get(homeposX + i,homeposY + y), Blocks.stoneBrick, Teams.gray);
            }
        }

        for (int i = 1; i < 6; i++) {
            for (int y = 1; y < 6; y++) {
                map.getTilemap().get(homeposX + i,homeposY + y).building = null;
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int y = 0; y < 7; y++) {
                map.getTilemap().get(homeposX + i,homeposY + y).floor = Floors.stone;
            }
        }

        Player mob = new Player(MobTypes.ryzik);
        mob.team = Teams.orange;
        map.addMob(mob);
        mob.position.set(38, 20);
        mob.inventory = new Inventory(5,5);
        mob.currentWeapon = new Weapon(Items.sword);
        Vars.player = mob;
        mob.health = mob.type.getHealth();

        Mob mob1 = map.spawnMob(MobTypes.cockroach, Teams.red);
        //mob1.controller = new EnemyController(mob1);
        mob1.position.set(20, 20);
        mob1.currentWeapon = new Weapon(Items.sword);

        map.getTilemap().get(homeposX + 3, homeposY).building = null;

        map.createBuilding(map.getTilemap().get(homeposX + 3, homeposY + 3), Blocks.eat, Teams.orange);

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            map.createBuilding(map.getTilemap().get(
                    random.nextInt(map.getTilemap().getWidth()),
                    random.nextInt(map.getTilemap().getHeight())
            ), Blocks.tree, Teams.gray);
        }

        Building building = new WaveSpawner(Blocks.air, MobTypes.cockroach, 2);
        map.addBuilding(map.getTilemap().get(20, 20), building, Teams.red);

        map.getTilemap().get(4,3).block = Blocks.tree;

        maps.add(map);
    }
}
