package ryzik.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.view.MenuBackgroundRenderer;
import ryzik.view.UIRenderer;

public class MenuScreen implements Screen {
    public MenuBackgroundRenderer backgroundRenderer;
    public UIRenderer uiRenderer;

    @Override
    public void show() {
        backgroundRenderer = new MenuBackgroundRenderer();
        backgroundRenderer.init();

        uiRenderer = new UIRenderer();
        uiRenderer.init();
        Vars.stage = uiRenderer.stage;

        Group group = new Group();
        Vars.ui.menuFragment.build(group);
        uiRenderer.stage.addActor(group);
    }

    @Override
    public void render(float delta) {
        backgroundRenderer.render(delta);

        Draw.camera.position.set(36 * Vars.TileSize, 36 * Vars.TileSize, 0);
        Draw.batch.begin();
        Draw.onDraw.fire();
        Draw.batch.end();

        uiRenderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
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
}
