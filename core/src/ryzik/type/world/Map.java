package ryzik.type.world;

import ryzik.Vars;
import ryzik.type.Entity;
import ryzik.type.world.block.Block;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.mob.MobType;
import ryzik.type.world.mob.Player;

public class Map {
    private final String name;
    private Tilemap tilemap;
    private Entitys entitys;
    private Mobs mobs;

    public Map(String name) {
        this.name = name;
        entitys = new Entitys();
        mobs = new Mobs();
    }

    public World createWorld() {
        World world = new World(tilemap.getWidth(),tilemap.getHeight());

        for (int i = 0; i < tilemap.getArray().length ; i++) {
            world.getTilemap().getArray()[i] = tilemap.getArray()[i];
        }

        for (int i = 0; i < entitys.getArray().size(); i++) {
            world.getEntitys().add(entitys.getArray().get(i));

            if (world.getEntitys().getArray().get(i) instanceof Player)
                Vars.player = (Player) world.getEntitys().getArray().get(i);
        }

        for (int i = 0; i < mobs.getArray().size; i++) {
            world.getMobs().add(mobs.getArray().get(i));
        }

        //TODO =)
        Vars.mapsLoader.maps.clear();
        Vars.mapsLoader.load();

        return world;
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

    public Building addBuilding(Tile tile, Building building, Team team) {
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

    public void setTilemap(Tilemap tilemap) {
        this.tilemap = tilemap;
    }

    public String getName() {
        return name;
    }
}
