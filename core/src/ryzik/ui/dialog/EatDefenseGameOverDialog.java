package ryzik.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.screen.EatDefenseGameScreen;
import ryzik.ui.Separator;
import ryzik.ui.TextActor;

public class EatDefenseGameOverDialog extends Dialog {
    private Table table;

    public EatDefenseGameOverDialog(EatDefenseGameScreen eatDefenseGameScreen) {
        Group group = new Group();
        table = new Table();

        TextActor info = new TextActor("played: " + eatDefenseGameScreen.gameState.secondsPlayed);

        TextButton exitButton = new TextButton("menu", Vars.skin);
        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hide();
                Vars.screenController.setMenu();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        table.center().add(info).row();
        table.center().add(new Separator(16)).row();
        table.center().add(exitButton);
        group.addActor(table);
        add(group);
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();

        table.setSize(getWidth(),getHeight());
    }
}
