package com.ryzik.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ryzik.Vars;
import com.ryzik.content.Blocks;
import com.ryzik.ctype.Renderer;
import com.ryzik.type.Building;
import com.ryzik.type.World;

public class WorldRenderer implements Renderer {
    private World world;

    //view
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    //test building
    private Building building;

    public WorldRenderer(World world) {
        this.world = world;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        //test building
        building = new Building(Blocks.sand);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawWorld(batch);
        batch.end();

        viewport.apply();
        camera.update();

        //test building
        building.setTile(world.getTiles().get(15,15));
        building.setHealth(building.getHealth()-1);
        world.getTiles().get(15,15).setBuilding(building);

        camera.position.set(
                world.getPlayer().getPosition().x * Vars.TILE_SIZE + Vars.TILE_SIZE/2f,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE + Vars.TILE_SIZE/2f
                ,0);
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
            world.getDroppedItems().getArray().get(i).draw(batch); // x,y ignored
        }

        world.getPlayer().draw(batch);
    }
}
