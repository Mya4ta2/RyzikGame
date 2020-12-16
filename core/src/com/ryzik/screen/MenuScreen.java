package com.ryzik.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
import com.ryzik.ui.Separator;
import com.ryzik.ui.TextButton;

public class MenuScreen implements Screen {

    private MainActivity game;

    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Stage currentStage;

    private TextButton singleplayerButton;
    private TextButton multiplayerButton;
    private TextButton exitButton;

    private Table table;

    public MenuScreen(MainActivity game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        font = new BitmapFont();
        font.setColor(Color.BLACK);

        stage = new Stage();
        stage.setViewport(viewport);
        currentStage = stage;

        table = new Table();

        Gdx.input.setInputProcessor(stage);
        initStages();
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentStage.act();
        currentStage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.end();

        viewport.apply();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        table.setSize(width,height);
        camera.position.set(width/2,height/2, 0);
    }

    public void initStages() {
        Texture buttonUp = new Texture("buttonUp.png");
        Texture buttonDown = new Texture("buttonDown.png");
        Sound buttonSound = Gdx.audio.newSound(Gdx.files.internal("minecraft_click.wav"));

        singleplayerButton = new com.ryzik.ui.TextButton(buttonUp,buttonDown, buttonSound, font);
        singleplayerButton.setHeight(50);
        singleplayerButton.setWidth(150);
        singleplayerButton.setText("single player");
        singleplayerButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getGameScreen());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        multiplayerButton = new com.ryzik.ui.TextButton(buttonUp,buttonDown, buttonSound, font);
        multiplayerButton.setHeight(50);
        multiplayerButton.setWidth(150);
        multiplayerButton.setText("multi player (none)");
        multiplayerButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //none =)
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        exitButton = new com.ryzik.ui.TextButton(buttonUp,buttonDown, buttonSound, font);
        exitButton.setHeight(50);
        exitButton.setWidth(150);
        exitButton.setText("exit");
        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        table.center().add(singleplayerButton);
        table.center().row();
        table.add(new Separator(10));
        table.row();
        table.center().add(multiplayerButton);
        table.center().row();
        table.add(new Separator(10));
        table.row();
        table.center().add(exitButton);
        table.center().row();

        stage.addActor(table);
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
