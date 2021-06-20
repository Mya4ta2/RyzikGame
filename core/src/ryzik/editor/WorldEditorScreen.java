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
    public TextActor textActor;

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

        textActor = new TextActor();
        textActor.setPosition(200,200);
        uiRenderer.stage.addActor(textActor);

        TextButton textButton = new TextButton("rounding++", Vars.skin);
        textButton.setPosition(200,100);
        textButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rounding += 1;
                if (rounding > 8) rounding = 0;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        uiRenderer.stage.addActor(textButton);

        textButton = new TextButton("rounding--", Vars.skin);
        textButton.setPosition(200,0);
        textButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rounding -= 1;
                if (rounding < 0) rounding = 8;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        uiRenderer.stage.addActor(textButton);

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

        textActor.setText(String.valueOf(rounding));

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
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
