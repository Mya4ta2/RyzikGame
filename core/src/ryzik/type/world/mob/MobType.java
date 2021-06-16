package ryzik.type.world.mob;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.ctype.Content;
import ryzik.type.MobController;

public class MobType {
    private final String name;

    private Texture texture;
    private MobController defaultController;
    private float width, height;
    private float health;
    private boolean enemy;
    private float damage;
    private float speed;

    public MobType(String name) {
        this.name = name;

        texture = Vars.atlas.find(name);
    }

    public void draw(Vector2 position) {
        Draw.batch.draw(
                texture,
                position.x * Vars.TileSize,
                position.y * Vars.TileSize,
                width * Vars.TileSize,
                height * Vars.TileSize
                );
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public MobController getDefaultController() {
        return defaultController;
    }

    public void setDefaultController(MobController defaultController) {
        this.defaultController = defaultController;
    }
}
