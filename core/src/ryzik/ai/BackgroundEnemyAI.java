package ryzik.ai;

import com.badlogic.gdx.math.Vector2;
import ryzik.type.MobController;
import ryzik.type.world.mob.Mob;

public class BackgroundEnemyAI extends MobController {
    private final Vector2 startPoint = new Vector2();
    private final Vector2 endPoint = new Vector2();

    private float speed;

    public BackgroundEnemyAI(Mob mob) {
        super(mob);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        mob.velocity.add(speed, speed);

        if (mob.position.y - endPoint.y > 0 ||
                mob.position.x - endPoint.x > 0) respawn();
    }

    @Override
    public void dispose() {

    }

    public void respawn() {
        mob.position.set(startPoint);
    }

    public Vector2 getStartPoint() {
        return startPoint;
    }

    public Vector2 getEndPoint() {
        return endPoint;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
