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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ryzik.MainActivity;
import com.ryzik.save.MapReader;
import com.ryzik.type.World;
import com.ryzik.ui.Image;
import com.ryzik.ui.Separator;
import com.ryzik.ui.TextButton;
import com.ryzik.view.MenuBackgroundRenderer;

import java.util.Arrays;

public class MenuScreen implements Screen {

    private MainActivity game;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Stage currentStage;

    //main menu
    private Stage stage;
    private TextButton singleplayerButton;
    private TextButton multiplayerButton;
    private TextButton exitButton;
    private Image logo;

    //world select menu
    private Stage worldSelectStage;
    private TextButton backButton;
    private Array<TextButton> worldButton = new Array<>();
    private World[] availableWorlds;

    private World backgroundWorld;
    private MenuBackgroundRenderer backgroundRenderer;

    private Table table;
    private Table worldSelectTable;

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

        worldSelectStage = new Stage();
        worldSelectStage.setViewport(viewport);

        try {
            backgroundWorld = MapReader.getWorldFromFile(Gdx.files.internal("backgroundMap.rsav"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            availableWorlds = new World[]{
                    MapReader.getWorldFromFile(Gdx.files.internal("backgroundMap.rsav")),
                    MapReader.getWorldFromFile(Gdx.files.internal("testOh.rsav")),
                    MapReader.getWorldFromFile(Gdx.files.internal("testmap.rsav"))
            };
        } catch (Exception e) {
            e.printStackTrace();
        }


        backgroundRenderer = new MenuBackgroundRenderer(backgroundWorld);

        table = new Table();
        worldSelectTable = new Table();

        initStages();
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        backgroundRenderer.render(delta);

        currentStage.act();
        currentStage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();

        backgroundWorld.getPlayer().getVelocity().add(1f,1f);

        viewport.apply();
        camera.update();

        Gdx.input.setInputProcessor(currentStage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        table.setSize(width,height);
        worldSelectTable.setSize(width,height);
        camera.position.set(width/2,height/2, 0);
        backgroundRenderer.resize(width,height);
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
                currentStage = worldSelectStage;
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

        TextureRegion logoTexture = new TextureRegion(new Texture("logo.png"));
        logo = new Image(logoTexture);

        table.center().add(logo);
        table.center().row();
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

        backButton = new com.ryzik.ui.TextButton(buttonUp,buttonDown, buttonSound, font);
        backButton.setHeight(50);
        backButton.setWidth(150);
        backButton.setText("back");
        backButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentStage = stage;
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        worldSelectTable.center().add(backButton).row();
        worldSelectTable.add(new Separator(10));
        worldSelectTable.row();

        for (int i = 0; i < availableWorlds.length; i++) {
            worldButton.add(new TextButton(buttonUp, buttonDown, buttonSound, font));
            worldButton.get(worldButton.size-1).setHeight(50);
            worldButton.get(worldButton.size-1).setWidth(150);
            worldButton.get(worldButton.size-1).setText(availableWorlds[i].getName());
            final int finalI = i;
            worldButton.get(worldButton.size-1).addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    GameScreen gameScreen = new GameScreen(game);
                    gameScreen.setWorld(availableWorlds[finalI]);
                    game.setScreen(gameScreen);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            worldSelectTable.add(worldButton.get(worldButton.size-1));
            worldSelectTable.row();
            worldSelectTable.add(new Separator(10));
            worldSelectTable.row();
        }

        stage.addActor(table);
        worldSelectStage.addActor(worldSelectTable);
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
