package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.content.Events;

public class WorldEditorFragment extends Fragment {
    @Override
    public void build(Group group) {
        final Table table = new Table();

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        });

        group.addActor(table);
    }
}
