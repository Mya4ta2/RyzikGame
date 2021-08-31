package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.Vars;
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

        TextActor loadingText = new TextActor("loading");
        loadingText.setCenterText(true);
        loadingText.setSize(256,64);
        table.center().add(loadingText).row();

        ProgressBar loadingBar = new ProgressBar(0,100,1,false, Vars.skin);
        table.center().add(loadingBar);
        loadingBar.setName("loadingBar");

        group.addActor(table);
    }
}
