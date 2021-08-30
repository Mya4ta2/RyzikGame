package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.content.Events;
import ryzik.ui.TextActor;

public class LoadingFragment extends Fragment {
    @Override
    public void build(Group group) {
        final Table table = new Table();

        Runnable resize = new Runnable() {
            @Override
            public void run() {
                table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        };

        resize.run();
        Events.resize.on(resize);

        table.center().add(new TextActor("loading"));

        group.addActor(table);
    }
}
