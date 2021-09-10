package ryzik.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.editor.WorldEditorScreen;
import ryzik.io.MapIO;
import ryzik.screen.EatDefenseGameScreen;

public class EatDefenseResumeDialog extends Dialog {
    private Table table;

    public EatDefenseResumeDialog() {
        super();
        closeButtonTable.setVisible(false);

        TextButton resumeButton = new TextButton("resume", Vars.skin);
        TextButton exitButton = new TextButton("exit", Vars.skin);

        resumeButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hide();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vars.screenController.setMenu();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        Group group = new Group();
        table = new Table();

        table.center().add(resumeButton).row();
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
