package ryzik.type.world;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Timer;
import ryzik.Vars;
import ryzik.ai.EnemyController;
import ryzik.content.Items;
import ryzik.content.MobTypes;
import ryzik.type.world.block.Block;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.mob.MobType;
import ryzik.type.world.mob.Weapon;

import java.util.Random;

public class WaveSpawner extends Building {
    public MobType spawnType;
    public Timer timer;
    public float timeBetweenWaves;
    public float timerToNextWave;
    public float timeToNextWave;
    public int wave;

    public WaveSpawner(Block type, MobType spawnType, float timeBetweenWaves) {
        super(type);
        this.spawnType = spawnType;
        this.timeBetweenWaves = timeBetweenWaves;
        bounds.shape = new Polygon(); // clear bounds
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (timer == null) start();

        timerToNextWave += delta;
        if (timerToNextWave > timeBetweenWaves) timerToNextWave = 0;

        timeToNextWave = Math.abs(timerToNextWave - timeBetweenWaves);
    }

    public void start() {
        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                wave++;
                spawnWave(wave);
            }
        }, timeBetweenWaves, timeBetweenWaves);
    }

    public void spawnWave(int wave) {
        Random random = new Random();

        int d = wave;

        while (d > 0) {
            Mob mob;
            if (d - 5 > 0) {
                d -= 5;
                mob = Vars.world.spawnMob(MobTypes.tarakeka, team);
            } else if (d - 2 > 0) {
                d -= 2;
                mob = Vars.world.spawnMob(MobTypes.bloodCockroach, team);
            } else {
                d -= 1;
                mob = Vars.world.spawnMob(MobTypes.cockroach, team);
            }

            mob.position.set(x + (random.nextInt(6) - 3), y + (random.nextInt(6) - 3));
            mob.currentWeapon = new Weapon(Items.cockroachHand);
            mob.controller = new EnemyController(mob);
        }
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void dispose() {
        timer.stop();
    }
}
