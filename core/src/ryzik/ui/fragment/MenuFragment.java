package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.ui.BackgroundActor;
import ryzik.ui.dialog.Dialog;
import ryzik.ui.Separator;
import ryzik.ui.TextActor;
import ryzik.ui.dialog.WorldSelectDialog;

public class MenuFragment extends Fragment{
    @Override
    public void build(Group group) {
        final Table leftTable = new Table();
        final BackgroundActor background = new BackgroundActor(Vars.atlas.find("menuBackground"));
        group.addActor(background);

        TextActor text = new TextActor("ryzik");
        text.setSize(128,64);

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                leftTable.setSize(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight());
            }
        });

        TextButton newGameButton = new TextButton("new game", Vars.skin);
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
        leftTable.center().add(exitButton).row();

        group.addActor(leftTable);
    }
}
