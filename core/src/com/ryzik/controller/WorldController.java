package com.ryzik.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ryzik.content.Blocks;
import com.ryzik.type.Tile;
import com.ryzik.type.World;

public class WorldController {
    private World world;

    public WorldController(World world) {
        this.world = world;
    }

    public void update(float delta) {
        world.getPlayer().update(delta);

        processInput();
        processCollisions();
    }

    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            world.getPlayer().getVelocity().y += world.getPlayer().getSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            world.getPlayer().getVelocity().y -= world.getPlayer().getSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            world.getPlayer().getVelocity().x += world.getPlayer().getSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            world.getPlayer().getVelocity().x -= world.getPlayer().getSpeed();
        }
    }

    public void processCollisions() {
        //take player near tiles and check collisions with player
        for (int i = -5; i < 5; i++) {
            for (int j = -5; j < 5; j++) {
                if ((world.getPlayer().getPosition().x + i) > 0 && (world.getPlayer().getPosition().x + i) < world.getWidth() &&
                   (world.getPlayer().getPosition().y + j) > 0 && (world.getPlayer().getPosition().y + j) < world.getHeight()) {
                    Tile tile = world.getTiles().get(
                            (int) world.getPlayer().getPosition().x + i,
                            (int) world.getPlayer().getPosition().y + j);

                    if (world.getPlayer().getBounds().overlaps(tile.getBounds()) && tile.getBlock() != Blocks.air) {
                        world.getPlayer().getPosition().set(world.getPlayer().getOldPosition());
                    }
                }
            }
        }
    }
}
