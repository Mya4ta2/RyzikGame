package com.ryzik.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ryzik.Cursor;
import com.ryzik.MainActivity;
import com.ryzik.Vars;
import com.ryzik.content.Items;
import com.ryzik.ctype.Renderer;
import com.ryzik.type.ItemStack;
import com.ryzik.type.TextField;
import com.ryzik.ui.HotBar;
import com.ryzik.ui.ItemSlotPanel;
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

    //resume stage //no, its pause stage, need remake =(
    private TextButton resumeButton;
    private TextButton settingsButton;
    private TextButton exitButton;
    private TextField chatField;

    private Table table;
    private Table gameTable;
    private MainActivity game;
    private ItemSlotPanel itemSlotPanel;
    private HotBar hotBar;

    //inventory stage
    private Stage inventoryStage;
    private Table inventoryTable;
    private HotBar inventoryHotBar;
    private TextButton openResumeButton;

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
        chatField = new TextField(new TextureRegion(new Texture("chatBackground.png")));

        inventoryTable = new Table();
        inventoryStage = new Stage();
        inventoryStage.setViewport(viewport);

        table = new Table();
        gameTable = new Table();

        initStages();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawCursorItem(batch);
        batch.end();

        gameStage.setScrollFocus(hotBar);

        currentStage.act();
        currentStage.draw();

        camera.update();
        viewport.apply();

        if (currentStage == gameStage) currentStage.setScrollFocus(hotBar);
        else currentStage.unfocus(hotBar);

        for (int y = 0; y < Vars.INVENTORY_HEIGHT; y++) {
            for (int x = 0; x < Vars.INVENTORY_WIDTH; x++) {
                if (y > Vars.INVENTORY_HEIGHT-2) {
                    hotBar.getSlots()[x].setItemStack(Vars.world.getPlayer().getInventory().getHotBar()[x]);
                    //oh no
                    inventoryHotBar.getSlots()[x].setItemStack(Vars.world.getPlayer().getInventory().getHotBar()[x]);
                }

                itemSlotPanel.getSlots()[x][y].setItemStack(Vars.world.getPlayer().getInventory().getInventory()[x][y]);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);

        table.setSize(width,height);
        gameTable.setSize(width,height);
        inventoryTable.setSize(width,height);

        camera.position.set(width/2,height/2, 0);
    }

    public void drawCursorItem(SpriteBatch batch) {
        if (Cursor.item != null) {
            batch.draw(Cursor.item.getItem().getTexture(), Gdx.input.getX(), Gdx.input.getY());
        }
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

        gameTable.top().add(new Separator(35)).row();
        hotBar = new HotBar(Vars.INVENTORY_WIDTH, buttonUp, buttonDown);
        inventoryHotBar = new HotBar(Vars.INVENTORY_WIDTH, buttonUp, buttonDown);
        itemSlotPanel = new ItemSlotPanel(Vars.INVENTORY_WIDTH, Vars.INVENTORY_HEIGHT,buttonUp,buttonDown);
        gameTable.top().left().add(hotBar).row();

        openResumeButton = new TextButton(buttonUp, buttonDown, buttonSound, font);
        openResumeButton.setHeight(50);
        openResumeButton.setWidth(150);
        openResumeButton.setText("settings"); // terraria 0_0
        openResumeButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentStage = resumeStage;
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        inventoryTable.top().add(new Separator(35)).row();
        inventoryTable.top().left().add(inventoryHotBar).row();
        inventoryTable.top().left().add(itemSlotPanel).row();
        inventoryTable.add(openResumeButton).row();

        hotBar.getSlots()[0].setItemStack(new ItemStack(Items.brick, 120));
        resumeStage.addActor(table);
        gameStage.addActor(gameTable);
        inventoryStage.addActor(inventoryTable);
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

    public TextField getChatField() {
        return chatField;
    }

    public Stage getInventoryStage() {
        return inventoryStage;
    }
}
