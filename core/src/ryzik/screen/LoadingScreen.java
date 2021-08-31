package ryzik.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.Timer;
import ryzik.Vars;
import ryzik.view.UIRenderer;

import java.util.Random;

public class LoadingScreen implements Screen {
    private UIRenderer renderer;
    private ProgressBar loadingBar;
    private Random random;

    @Override
    public void show() {
        renderer = new UIRenderer();
        renderer.init();

        random = new Random();

        Group group = new Group();
        Vars.ui.loadingFragment.build(group);
        renderer.stage.addActor(group);

        loadingBar = group.findActor("loadingBar");
    }

    @Override
    public void render(float delta) {
        renderer.render(delta);

        loadingBar.setValue(loadingBar.getValue()+Math.abs(random.nextInt(3) - 1));
        if (loadingBar.getValue() >= loadingBar.getMaxValue())
            Vars.screenController.setMenu();
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
