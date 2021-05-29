package ryzik.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.screen.EatDefenseGameScreen;
import ryzik.screen.GameScreen;
import ryzik.type.world.Map;
import ryzik.ui.BackgroundActor;

public class GameModeSelectDialog extends Dialog {
    private Table table;
    private Map map;

    public GameModeSelectDialog(final Map map) {
        this.map = map;

        table = new Table();

        TextButton eatDefenceStartButton = new TextButton("eat defence", Vars.skin);
        eatDefenceStartButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen gameScreen = new EatDefenseGameScreen();
                gameScreen.world = map.createWorld();
                Vars.screenController.startGame(gameScreen);
                Events.resize.fire();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        table.center();
        table.add(eatDefenceStartButton).row();

        Group group = new Group();
        group.addActor(table);
        left().bottom().add(group);
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        table.setSize(getWidth(), getHeight());
    }
}
