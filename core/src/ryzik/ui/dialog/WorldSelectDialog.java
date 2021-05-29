package ryzik.ui.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.MapsLoader;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.screen.GameScreen;
import ryzik.ui.BackgroundActor;

public class WorldSelectDialog extends Dialog {
    private Table table;

    public WorldSelectDialog() {
        Table worlds = new Table();
        for (int i = 0; i < Vars.mapsLoader.maps.size; i++) {
            TextButton textButton = new TextButton(
                    Vars.mapsLoader.maps.get(i).getName(),
                    Vars.skin
            );

            final int finalI = i;
            textButton.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    final Dialog gameModeSelect = new GameModeSelectDialog(Vars.mapsLoader.maps.get(finalI));
                    gameModeSelect.setSize(Gdx.graphics.getWidth() - 32, Gdx.graphics.getHeight() - 32);
                    gameModeSelect.setPosition(16,16);
                    Events.resize.on(new Runnable() {
                        @Override
                        public void run() {
                            gameModeSelect.setSize(Gdx.graphics.getWidth() - 32, Gdx.graphics.getHeight() - 32);
                            gameModeSelect.setPosition(16,16);
                        }
                    });
                    gameModeSelect.show();
                    return super.touchDown(event, x, y, pointer, button);
                }
            });

            worlds.add(textButton).row();
        }

        table = new Table();

        ScrollPane scrollPane = new ScrollPane(worlds, Vars.skin);

        table.center().row();
        table.add(scrollPane);

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
