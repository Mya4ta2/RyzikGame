package com.ryzik.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ryzik.Vars;
import com.ryzik.content.Blocks;
import com.ryzik.ctype.Renderer;
import com.ryzik.type.Tile;
import com.ryzik.type.World;

import java.util.Random;

public class MenuBackgroundRenderer implements Renderer {
    private World world;

    //view
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    //random player walking
    private int wayChangeSpeed = 0;
    private int randomX, randomY;
    private Random random = new Random();
    private boolean collision = true;

    public MenuBackgroundRenderer(World world) {
        this.world = world;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);

        camera.position.set(
                world.getPlayer().getPosition().x * Vars.TILE_SIZE + Vars.TILE_SIZE,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE + Vars.TILE_SIZE, 0);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawWorld(batch);
        batch.end();

        if (wayChangeSpeed > 0) {
            wayChangeSpeed -= delta;
            world.getPlayer().getVelocity().set(randomX, randomY);
        } else {
            wayChangeSpeed = 500;
            randomX = random.nextBoolean() ? -random.nextInt(5): random.nextInt(5);
            randomY = random.nextBoolean() ? -random.nextInt(5): random.nextInt(5);
        }

        if (collision) {
            collision = false;
            randomX = -randomX;
            randomY = -randomY;
        }

        world.getPlayer().update(delta);
        processCollisions();

        viewport.apply();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    public void drawWorld(SpriteBatch batch) {
        for (int i = 0; i < world.getTiles().getArray().length; i++) {
            world.getTiles().getArray()[i].draw(batch);
        }

        for (int i = 0; i < world.getDroppedItems().getArray().size; i++) {
            world.getDroppedItems().getArray().get(i).draw(batch,0,0); // x,y ignored
        }

        world.getPlayer().draw(batch, 0,0); // x, y ignored
    }

    // i so lazy for make controller =)
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
                        collision = true;
                    }
                }
            }
        }
    }
}
