package ryzik.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import ryzik.Vars;
import ryzik.view.UIRenderer;

public class LoadingScreen implements Screen {
    private UIRenderer renderer;

    @Override
    public void show() {
        renderer = new UIRenderer();
        renderer.init();

        Group group = new Group();
        Vars.ui.loadingFragment.build(group);
        renderer.stage.addActor(group);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Vars.screenController.setMenu();
            }
        }, 5);
    }

    @Override
    public void render(float delta) {
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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
