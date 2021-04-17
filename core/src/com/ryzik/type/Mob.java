package com.ryzik.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ryzik.Vars;

public class Mob implements Entity, Draw {
    private MobType type;

    private Vector2 velocity = new Vector2();
    private Vector2 position = new Vector2();

    public Mob(MobType type) {
        this.type = type;
    }

    @Override
    public void update(float delta) {
        position.add(velocity.scl(delta));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                type.texture,
                position.x * Vars.TILE_SIZE,
                position.y * Vars.TILE_SIZE,
                type.width * Vars.TILE_SIZE,
                type.height * Vars.TILE_SIZE
        );
    }

    public MobType getType() {
        return type;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getPosition() {
        return position;
    }
}
