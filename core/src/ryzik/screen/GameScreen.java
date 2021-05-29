package ryzik.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.ai.EnemyController;
import ryzik.content.*;
import ryzik.type.GameState;
import ryzik.type.item.Inventory;
import ryzik.type.world.Building;
import ryzik.type.world.item.ItemStack;
import ryzik.type.world.World;
import ryzik.type.world.WorldController;
import ryzik.type.world.item.weapon.WeaponType;
import ryzik.type.world.mob.MobType;
import ryzik.type.world.mob.Weapon;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.mob.Player;
import ryzik.view.DebugWorldRenderer;
import ryzik.view.UIRenderer;
import ryzik.view.WorldRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

public abstract class GameScreen implements Screen {
    public GameState gameState;
    public World world;
    public WorldRenderer worldRenderer;
    public WorldController worldController;
    public DebugWorldRenderer debugWorldRenderer;
    public UIRenderer uiRenderer;

    @Override
    public void show() {
        worldRenderer = new WorldRenderer(world);
        debugWorldRenderer = new DebugWorldRenderer(world);
        worldController = new WorldController(world);
        uiRenderer = new UIRenderer();
        Vars.world = world;

        if (Vars.mobile) Draw.camera.zoom = 0.5f;

        worldRenderer.init();
        debugWorldRenderer.init();
        worldController.init();
        uiRenderer.init();
    }

    @Override
    public void render(float delta) {
        Draw.batch.begin();
        worldRenderer.render(delta);
        //debugWorldRenderer.render(delta);
        Draw.onDraw.fire();
        Draw.batch.end();

        //TODO убрать этот keyE подальше отсюдава
        if (!Vars.player.dead) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                Events.keyE.fire();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
                Events.keyQ.fire();
            }
        }

        Events.update.fire();

        uiRenderer.render(delta);
        worldController.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        uiRenderer.resize(width,height);
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
