package ryzik.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Blocks;
import ryzik.content.Events;
import ryzik.content.Items;
import ryzik.type.EatDefenseGameState;
import ryzik.type.world.Building;
import ryzik.ui.dialog.Dialog;
import ryzik.ui.dialog.EatDefenseGameOverDialog;

public class EatDefenseGameScreen extends GameScreen {
    public EatDefenseGameState gameState;

    @Override
    public void show() {
        super.show();
        gameState = new EatDefenseGameState();

        Array<Building> eatBuildings = world.getBuildings(Blocks.eat);
        if (eatBuildings.size <= 0) throw new RuntimeException();
        else gameState.eat = eatBuildings.get(0);
        Vars.gameState = gameState;

        Group group = new Group();
        Vars.ui.eatDefenseGameFragment.build(group);
        uiRenderer.stage.addActor(group);

        Draw.camera.position.set(
                (Vars.player.position.x * Vars.TileSize) + 16,
                (Vars.player.position.y * Vars.TileSize) + 16, 0
        );

        Vars.player.onMove.on(new Runnable() {
            @Override
            public void run() {
                float xDifference = (Vars.player.position.x - (Draw.camera.position.x / Vars.TileSize));
                float yDifference = (Vars.player.position.y - (Draw.camera.position.y / Vars.TileSize));

                Draw.camera.position.add(
                        xDifference,
                        yDifference,
                        0
                );
            }
        });

        Vars.player.inventory.getHotBar()[4].set(Items.sword, 1);
        Vars.player.inventory.getHotBar()[3].set(Items.stick, 5);
        Vars.player.inventory.getHotBar()[2].set(Items.suicide, 5);
        Vars.player.inventory.getHotBar()[0].set(Items.dyurandal, 1);
        //Vars.player.inventory.getHotBar()[1].set(Items.gun, 1);

        gameState.eat.onHealthChange.on(new Runnable() {
            @Override
            public void run() {
                if (gameState.eat.health < 0) gameOver();
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (!gameState.gameOver) {
            gameState.eat.applyDamage(1f);
            gameState.secondsPlayed += Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void gameOver() {
        gameState.gameOver = true;

        final Dialog dialog = new EatDefenseGameOverDialog(this);
        dialog.setSize(Gdx.graphics.getWidth() - 32, Gdx.graphics.getHeight() - 32);
        dialog.setPosition(16,16);

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                dialog.setSize(Gdx.graphics.getWidth() - 32, Gdx.graphics.getHeight() - 32);
                dialog.setPosition(16,16);
            }
        });

        dialog.show();
    }
}
