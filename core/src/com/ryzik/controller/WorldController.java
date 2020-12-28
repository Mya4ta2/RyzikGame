package com.ryzik.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.ryzik.Cursor;
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
        updateBuildings(delta);
    }

    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            world.getPlayer().getVelocity().y += world.getPlayer().getCurrentSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            world.getPlayer().getVelocity().y -= world.getPlayer().getCurrentSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            world.getPlayer().getVelocity().x += world.getPlayer().getCurrentSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            world.getPlayer().getVelocity().x -= world.getPlayer().getCurrentSpeed();
        }

        world.getPlayer().setSprint(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT));
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

        for (int i = 0; i < world.getDroppedItems().getArray().size; i++) {
            if (world.getPlayer().getBounds().overlaps(world.getDroppedItems().getArray().get(i).getBounds())){
                world.getPlayer().getInventory().raiseItem(world.getDroppedItems().getArray().get(i));
            }
        }
    }

    public void updateBuildings(float delta) {
        for (int i = 0; i < world.getTiles().getArray().length; i++) {
            if (world.getTiles().getArray()[i].getBuilding() != null) {
                if (world.getTiles().getArray()[i].getBuilding().getBounds().contains(Cursor.worldX/32, Cursor.worldY/32) &&
                        Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    world.getTiles().getArray()[i].getBuilding().touchDown();
                }

                world.getTiles().getArray()[i].getBuilding().update(delta);
            }
        }
    }
}
