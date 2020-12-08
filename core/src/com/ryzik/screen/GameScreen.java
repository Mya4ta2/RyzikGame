package com.ryzik.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.ryzik.controller.UIController;
import com.ryzik.controller.WorldController;
import com.ryzik.save.MapReader;
import com.ryzik.type.World;
import com.ryzik.view.UIRenderer;
import com.ryzik.view.WorldRenderer;

import java.io.FileNotFoundException;

public class GameScreen implements Screen {

    private World world;
    private WorldRenderer renderer;
    private WorldController controller;
    private UIRenderer uiRenderer;
    private UIController uiController;

    @Override
    public void show() {
        try {
            world = MapReader.getWorldFromFile(Gdx.files.internal("testOh.rsav"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        renderer = new WorldRenderer(world);
        controller = new WorldController(world);
        uiRenderer = new UIRenderer();
        uiController = new UIController(uiRenderer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(delta);
        controller.update(delta);
        uiRenderer.render(delta);
        uiController.update(delta);

        Gdx.input.setInputProcessor(uiRenderer.getCurrentStage());
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width,height);
        uiRenderer.resize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public WorldRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(WorldRenderer renderer) {
        this.renderer = renderer;
    }

    public WorldController getController() {
        return controller;
    }

    public void setController(WorldController controller) {
        this.controller = controller;
    }

    public UIRenderer getUiRenderer() {
        return uiRenderer;
    }

    public void setUiRenderer(UIRenderer uiRenderer) {
        this.uiRenderer = uiRenderer;
    }
}
