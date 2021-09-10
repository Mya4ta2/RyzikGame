package ryzik.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Cursor;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Blocks;
import ryzik.content.Events;
import ryzik.content.Floors;
import ryzik.type.world.World;
import ryzik.ui.TextActor;
import ryzik.ui.dialog.Dialog;
import ryzik.ui.dialog.WorldEditorResumeDialog;
import ryzik.view.UIRenderer;
import ryzik.view.WorldRenderer;

public class WorldEditorScreen implements Screen {
    public UIRenderer uiRenderer;
    public World world;
    public WorldRenderer worldRenderer;
    public WorldEditorController worldController;

    public int rounding;

    @Override
    public void show() {
        world = new World(50,50);

        for (int x = 0; x < world.getTilemap().getWidth(); x++) {
            for (int y = 0; y < world.getTilemap().getHeight(); y++) {
                world.getTilemap().get(x, y).floor = Floors.grass;
            }
        }

        Draw.camera.position.set(25 * Vars.TileSize, 25 * Vars.TileSize, 0);

        worldRenderer = new WorldRenderer(world);
        worldController = new WorldEditorController(world, this);
        uiRenderer = new UIRenderer();
        uiRenderer.init();

        Group group = new Group();
        Vars.ui.worldEditorFragment.build(group);
        uiRenderer.stage.addActor(group);
    }

    @Override
    public void render(float delta) {
        Draw.batch.begin();
        worldRenderer.render(delta);
        Draw.onDraw.fire();
        Draw.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.BACK))
            openResumeDialog();

        uiRenderer.render(delta);
        worldController.update();
    }

    @Override
    public void resize(int width, int height) {
        uiRenderer.resize(width, height);
    }

    public void openResumeDialog() {
        final Dialog dialog = new WorldEditorResumeDialog(this);

        dialog.setSize(Gdx.graphics.getWidth() - 32, Gdx.graphics.getHeight() - 32);
        dialog.setPosition(16,16);

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                dialog.setSize(Gdx.graphics.getWidth() - 32, Gdx.graphics.getHeight() - 32);
                dialog.setPosition(16,16);
            }
        });

        dialog.show();
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
}
