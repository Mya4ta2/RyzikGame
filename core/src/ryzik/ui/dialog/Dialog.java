package ryzik.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.ui.BackgroundActor;

public class Dialog extends Table {
    public Group group;
    public Table closeButtonTable;
    public BackgroundActor backgroundActor;

    public Dialog() {
        this("sdsa");
    }

    public Dialog(String title) {
        group = new Group();
        closeButtonTable = new Table();

        backgroundActor = new BackgroundActor(Vars.atlas.find("dialogBackground"));
        backgroundActor.setSize(getWidth(), getHeight());

        TextButton closeButton = new TextButton("close", Vars.skin);
        closeButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hide();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        group.addActor(backgroundActor);
        closeButtonTable.right().top().add(closeButton);
        group.addActor(closeButtonTable);
        left().bottom().add(group);
    }

    @Override
    protected void sizeChanged() {
        backgroundActor.setSize(getWidth(), getHeight());
        closeButtonTable.setSize(getWidth(),getHeight());
    }

    @Override
    protected void positionChanged() {
        closeButtonTable.setPosition(getX(),getY());
    }

    public void show() {
        Vars.stage.addActor(this);
    }

    public void hide() {
        Vars.stage.getActors().removeValue(this,true);
    }
}
