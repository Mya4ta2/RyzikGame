package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.editor.WorldEditorScreen;
import ryzik.ui.BackgroundActor;
import ryzik.ui.LinkTextActor;
import ryzik.ui.dialog.Dialog;
import ryzik.ui.Separator;
import ryzik.ui.TextActor;
import ryzik.ui.dialog.WorldSelectDialog;

public class MenuFragment extends Fragment{
    @Override
    public void build(Group group) {
        final Table leftTable = new Table();
        final Table rightTable = new Table();

        TextActor text = new TextActor("ryzik");
        text.setSize(128,64);

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                leftTable.setSize(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight());
                rightTable.setSize(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight());
                rightTable.setPosition(Gdx.graphics.getWidth()/2f, 0);
            }
        });

        TextButton newGameButton = new TextButton("new game", Vars.skin);
        TextButton worldEditorButton = new TextButton("editor", Vars.skin);
        TextButton exitButton = new TextButton("exit", Vars.skin);

        newGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                final Dialog dialog = new WorldSelectDialog();
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
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        worldEditorButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vars.screenController.openEditor(new WorldEditorScreen());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        leftTable.center().add(text).row();
        leftTable.center().add(newGameButton).row();
        leftTable.center().add(new Separator(12)).row();
        leftTable.center().add(worldEditorButton).row();
        leftTable.center().add(new Separator(12)).row();
        leftTable.center().add(exitButton).row();

        LinkTextActor linkTextActor = new LinkTextActor("by javapedik", Vars.authorVKUrl);
        linkTextActor.setSize(224,48);
        rightTable.bottom().right().add(linkTextActor);

        group.addActor(leftTable);
        group.addActor(rightTable);
    }
}
