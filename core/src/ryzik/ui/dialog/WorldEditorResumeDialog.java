package ryzik.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.editor.WorldEditorScreen;
import ryzik.io.MapIO;

public class WorldEditorResumeDialog extends Dialog {
    private Table table;

    public WorldEditorResumeDialog(final WorldEditorScreen editorScreen) {
        super();
        closeButtonTable.setVisible(false);

        TextButton resumeButton = new TextButton("resume", Vars.skin);
        TextButton saveButton = new TextButton("save", Vars.skin);

        resumeButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hide();
                return super.touchDown(event, x, y, pointer, button);
            }
        });


        Group group = new Group();
        table = new Table();

        table.center().add(resumeButton).row();
        table.center().add(saveButton);

        group.addActor(table);
        add(group);
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();

        table.setSize(getWidth(),getHeight());
    }
}
