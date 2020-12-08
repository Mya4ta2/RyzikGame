package com.ryzik.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ryzik.ctype.Renderer;

public class UIRenderer implements Renderer {
    private Stage gameStage;
    private Stage resumeStage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Stage currentStage;

    private float pixelX;
    private float pixelY;

    public UIRenderer() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        gameStage = new Stage();
        resumeStage = new Stage();
        currentStage = gameStage;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch,"1", pixelX,pixelY * 25);
        font.draw(batch,"2", pixelX,pixelY * 1080);
        font.draw(batch,"3", pixelX * 1900,pixelY * 25);
        font.draw(batch,"4", pixelX * 1900,pixelY * 1080);
        batch.end();

        currentStage.act();
        currentStage.draw();

        camera.update();
        viewport.apply();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        pixelX = Gdx.graphics.getWidth() / 1920f;
        pixelY = Gdx.graphics.getHeight() / 1080f;

        camera.position.set(width/2,height/2, 0);
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public Stage getResumeStage() {
        return resumeStage;
    }
}
