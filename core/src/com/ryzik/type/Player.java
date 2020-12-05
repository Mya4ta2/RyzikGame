package com.ryzik.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ryzik.Vars;
import com.ryzik.ctype.MappableContent;

public class Player implements MappableContent {
    private float WIDTH = 1.5f;
    private float HEIGHT = 1.5f;

    private Vector2 position = new Vector2();
    private Vector2 oldPosition = new Vector2();
    private Vector2 velocity = new Vector2();
    private Rectangle bounds = new Rectangle();
    private TextureRegion rigthTexture;
    private TextureRegion leftTexture;

    private int MaxHealth = 100;
    private int health;
    private boolean walkLeft = false;

    private float speed = 8;
    private float sprintSpeed = 12;
    private float currentSpeed = speed;
    private boolean sprint;

    public Player() {
        this.position = Vector2.Zero;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;
    }

    public Player(Vector2 position) {
        this.position = position;

        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;

        heal();
    }

    public void update(float delta) {
        oldPosition.set(position);
        position.add(velocity.scl(delta));

        bounds.x = position.x;
        bounds.y = position.y;

        if (sprint) currentSpeed = sprintSpeed;
        else currentSpeed = speed;

    }

    @Override
    public void draw(SpriteBatch batch, int x, int y) {

        if (velocity.x > 0) walkLeft = false; else walkLeft = true;

        if (walkLeft) {
            batch.draw(
                    leftTexture,
                    position.x * Vars.TILE_SIZE,
                    position.y * Vars.TILE_SIZE,
                    WIDTH * Vars.TILE_SIZE,
                    HEIGHT * Vars.TILE_SIZE);
        } else {
            batch.draw(
                    rigthTexture,
                    position.x * Vars.TILE_SIZE,
                    position.y * Vars.TILE_SIZE,
                    WIDTH * Vars.TILE_SIZE,
                    HEIGHT * Vars.TILE_SIZE);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(float WIDTH) {
        this.WIDTH = WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(float HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    public boolean isSprint() {
        return sprint;
    }

    public void setSprint(boolean sprint) {
        this.sprint = sprint;
    }

    public Vector2 getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Vector2 oldPosition) {
        this.oldPosition = oldPosition;
    }

    public void heal() {
        this.health = MaxHealth;
    }

    public void heal(int health) {
        this.health += health;
        if (this.health > MaxHealth) this.health = MaxHealth;
    }

    public int getMaxHealth() {
        return MaxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        MaxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public TextureRegion getRigthTexture() {
        return rigthTexture;
    }

    public void setRigthTexture(TextureRegion rigthTexture) {
        this.rigthTexture = rigthTexture;
    }

    public TextureRegion getLeftTexture() {
        return leftTexture;
    }

    public void setLeftTexture(TextureRegion leftTexture) {
        this.leftTexture = leftTexture;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
