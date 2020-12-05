package com.ryzik.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ryzik.ctype.Renderer;

public class UIRenderer implements Renderer {
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;

    private float pixelX;
    private float pixelY;

    public UIRenderer() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        stage = new Stage();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.draw(batch,"oh 1", pixelX,pixelY * 16);
        font.draw(batch,"oh 2", pixelX,pixelY * 480);
        font.draw(batch,"oh 3", pixelX * 457,pixelY * 16);
        font.draw(batch,"oh 4", pixelX * 457,pixelY * 480);
        batch.end();

        camera.update();
        viewport.apply();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        camera.position.set(width / 2, height / 2,0);

        pixelX = Gdx.graphics.getWidth() / 480f;
        pixelY = Gdx.graphics.getHeight() / 480f;
    }
}
