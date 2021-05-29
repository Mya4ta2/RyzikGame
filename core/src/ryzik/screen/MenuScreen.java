package ryzik.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import ryzik.Vars;
import ryzik.view.UIRenderer;

public class MenuScreen implements Screen {
    public UIRenderer uiRenderer;

    @Override
    public void show() {
        uiRenderer = new UIRenderer();
        uiRenderer.init();
        Vars.stage = uiRenderer.stage;

        Group group = new Group();
        Vars.ui.menuFragment.build(group);
        uiRenderer.stage.addActor(group);
    }

    @Override
    public void render(float delta) {
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
