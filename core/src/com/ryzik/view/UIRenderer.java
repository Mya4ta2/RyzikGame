package com.ryzik.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ryzik.MainActivity;
import com.ryzik.ctype.Renderer;
import com.ryzik.ui.Separator;
import com.ryzik.ui.TextButton;

public class UIRenderer implements Renderer {
    private Stage gameStage;
    private Stage resumeStage;
    private Stage settingsStage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Stage currentStage;

    //resume stage
    private TextButton resumeButton;
    private TextButton settingsButton;
    private TextButton exitButton;

    private Table table;
    private MainActivity game;

    public UIRenderer(MainActivity game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        font = new BitmapFont();
        font.setColor(Color.BLACK);

        gameStage = new Stage();
        resumeStage = new Stage();
        settingsStage = new Stage();
        currentStage = gameStage;
        gameStage.setViewport(viewport);
        resumeStage.setViewport(viewport);

        table = new Table();

        initStages();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();

        currentStage.act();
        currentStage.draw();

        camera.update();
        viewport.apply();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);

        table.setSize(width,height);

        camera.position.set(width/2,height/2, 0);
    }

    //i make this for first time, maybe i after remake it =(
    public void initStages() {
        Texture buttonUp = new Texture("buttonUp.png");
        Texture buttonDown = new Texture("buttonDown.png");
        Sound buttonSound = Gdx.audio.newSound(Gdx.files.internal("minecraft_click.wav"));

        resumeButton = new TextButton(buttonUp,buttonDown, buttonSound, font);
        resumeButton.setHeight(50);
        resumeButton.setWidth(150);
        resumeButton.setText("resume");
        resumeButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentStage = gameStage;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        settingsButton = new TextButton(buttonUp,buttonDown, buttonSound, font);
        settingsButton.setHeight(50);
        settingsButton.setWidth(150);
        settingsButton.setText("settings");
        settingsButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentStage = settingsStage;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        exitButton = new TextButton(buttonUp, buttonDown, buttonSound, font);
        exitButton.setHeight(50);
        exitButton.setWidth(150);
        exitButton.setText("exit");
        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getMenuScreen());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        table.center().add(resumeButton);
        table.center().row();
        table.add(new Separator(10));
        table.row();
        table.center().add(settingsButton);
        table.center().row();
        table.add(new Separator(10));
        table.row();
        table.center().add(exitButton);

        resumeStage.addActor(table);
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
