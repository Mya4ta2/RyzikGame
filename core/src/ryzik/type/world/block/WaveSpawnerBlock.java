package ryzik.type.world.block;

import ryzik.type.world.Building;
import ryzik.type.world.WaveSpawner;
import ryzik.type.world.mob.MobType;

public class WaveSpawnerBlock extends Block {
    public MobType spawnType;
    public float timeBetweenWaves;

    public WaveSpawnerBlock(String name) {
        super(name);
    }

    @Override
    public WaveSpawner getBuilding() {
        return new WaveSpawner(this,spawnType,timeBetweenWaves);
    }
}
