package ryzik.type.world;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Timer;
import ryzik.Vars;
import ryzik.ai.EnemyController;
import ryzik.content.Items;
import ryzik.type.world.block.Block;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.mob.MobType;
import ryzik.type.world.mob.Weapon;

public class WaveSpawner extends Building {
    public MobType spawnType;
    public Timer timer;
    public float timeBetweenWaves;

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
    }

    public void start() {
        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                Mob mob = Vars.world.spawnMob(spawnType, team);
                mob.position.set(x, y);
                mob.currentWeapon = new Weapon(Items.cockroachHand);
                mob.controller = new EnemyController(mob);
            }
        }, timeBetweenWaves, timeBetweenWaves);
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void dispose() {
        timer.stop();
    }
}
