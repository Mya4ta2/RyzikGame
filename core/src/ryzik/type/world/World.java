package ryzik.type.world;

import com.badlogic.gdx.utils.Array;
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

    public Building createBuilding(Tile tile, Block block, Team team) {
        Building building = new Building(block);
        tile.building = building;
        building.team = team;
        building.x = tile.x;
        building.y = tile.y;
        addEntity(building);
        return building;
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