package com.ryzik.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ryzik.type.World;

public class WorldController {
    private World world;

    public WorldController(World world) {
        this.world = world;
    }

    public void update(float delta) {
        world.getPlayer().update(delta);

        processInput();
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
}
