package ryzik.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Blocks;
import ryzik.content.Items;
import ryzik.type.EatDefenseGameState;
import ryzik.type.world.Building;

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

        Vars.player.inventory.getHotBar()[3].set(Items.stick,5);
        Vars.player.inventory.getHotBar()[2].set(Items.suicide, 5);
        //Vars.player.inventory.getHotBar()[1].set(Items.gun, 1);
        Vars.player.inventory.getHotBar()[0].set(Items.sword, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
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
}
