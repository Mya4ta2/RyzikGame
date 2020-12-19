package com.ryzik;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ryzik.content.Blocks;
import com.ryzik.content.Floors;
import com.ryzik.content.Items;
import com.ryzik.content.Weapons;
import com.ryzik.screen.GameScreen;
import com.ryzik.screen.MenuScreen;

public class MainActivity extends Game {

    private GameScreen gameScreen;
    private MenuScreen menuScreen;

    @Override
    public void create() {
        Vars.ERROR_TEXTURE = new TextureRegion(new Texture("error.png"));

        new Blocks().load();
        new Floors().load();
        new Items().load();
        new Weapons().load();

        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);
        setScreen(gameScreen); // init game screen
        loadTextures();

        setScreen(menuScreen);
    }

    public void loadTextures() {
        TextureRegion[][] textureRegions = TextureRegion.split(
                new Texture("at1.png"),
                Vars.TILE_SIZE,
                Vars.TILE_SIZE);

        Blocks.woodPlank.setTexture(textureRegions[1][0]);
        Floors.stone.setTexture(textureRegions[0][2]);
        Floors.brick.setTexture(textureRegions[0][3]);
        Blocks.woodPlank.setTexture(textureRegions[1][0]);
        Floors.woodenPlank.setTexture(textureRegions[1][1]);
        Blocks.sandBrick.setTexture(textureRegions[2][0]);
        Blocks.brick.setTexture(textureRegions[2][1]);
        Floors.grass.setTexture(textureRegions[2][2]);
        Floors.sandBrick.setTexture(textureRegions[3][0]);

        Items.test.setTexture(textureRegions[2][1]);

        Vars.PLAYER_LEFT_TEXTURE = textureRegions[0][0];
        Vars.PLAYER_RIGHT_TEXTURE = textureRegions[0][1];
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public MenuScreen getMenuScreen() {
        return menuScreen;
    }
}
