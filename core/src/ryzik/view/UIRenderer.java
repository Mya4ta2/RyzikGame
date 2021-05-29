package ryzik.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.ctype.Renderer;

public class UIRenderer implements Renderer {
    public Stage stage;
    public OrthographicCamera camera;
    public Viewport viewport;

    @Override
    public void init() {
        stage = new Stage();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        stage.setViewport(viewport);
        Vars.stage = stage;

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

        viewport.apply();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        camera.position.set(width/2f, height/2f, 0);
        viewport.update(width,height);
    }
}
