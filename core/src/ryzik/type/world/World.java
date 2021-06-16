package ryzik.type.world;

import com.badlogic.gdx.utils.Array;
import ryzik.io.Reads;
import ryzik.io.Writes;
import ryzik.type.Entity;
import ryzik.type.world.block.Block;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.mob.MobType;

import java.io.Serializable;

public class World {
    private Tilemap tilemap;
    private Mobs mobs;
    private Entitys entitys;

    public World(int width, int height) {
        tilemap = new Tilemap(width,height);
        mobs = new Mobs();
        entitys = new Entitys();
    }

    public Mob spawnMob(MobType type, Team team) {
        Mob mob = new Mob(type);
        mob.team = team;
        addMob(mob);

        return mob;
    }

    public void addMob(Mob mob) {
        mobs.add(mob);
        entitys.add(mob);
    }

    public void addEntity(Entity entity) {
        entitys.add(entity);
    }

    public Building addBuilding(Tile tile, Building building, Team team) {
        tile.building = building;
        building.team = team;
        building.x = tile.x;
        building.y = tile.y;
        addEntity(building);
        return building;
    }

    public Building createBuilding(Tile tile, Block block, Team team) {
        Building building = new Building(block);
        tile.building = building;
        building.team = team;
        building.x = tile.x;
        building.y = tile.y;
        addEntity(building);
        return building;
    }

    public void read(Reads reads) {
        int width = reads.i();
        int height = reads.i();

        Tilemap tilemap = new Tilemap(width,height);
        this.tilemap = tilemap;

        for (Tile tile : tilemap.getArray()) {
            tile.read(reads);

            if (tile.building != null) {
                addBuilding(tile, tile.building, tile.building.team);
            }
        }
    }

    public void write(Writes writes) {
        writes.i(getTilemap().getWidth());
        writes.i(getTilemap().getHeight());

        for (Tile tile : getTilemap().getArray()) {
            tile.write(writes);
        }
    }


    public Tilemap getTilemap() {
        return tilemap;
    }

    public Array<Building> getBuildings(Block type) {
        Array<Building> buildings = new Array<>();

        for (int i = 0; i < tilemap.getArray().length; i++) {
            if (tilemap.getArray()[i].building != null && tilemap.getArray()[i].building.type == type)
                buildings.add(tilemap.getArray()[i].building);
        }

        return buildings;
    }

    public void disposeBuildings() {
        for (int x = 0; x < tilemap.getWidth(); x++) {
            for (int y = 0; y < tilemap.getHeight(); y++) {
                if (tilemap.get(x,y).building != null)
                    tilemap.get(x,y).building.dispose();
            }
        }
    }

    public Mobs getMobs() {
        return mobs;
    }

    public void updateEntitys(float delta) {
        entitys.updateAll(delta);
    }

    public Entitys getEntitys() {
        return entitys;
    }
}
