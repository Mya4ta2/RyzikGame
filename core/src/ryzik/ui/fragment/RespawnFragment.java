package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.ui.TextActor;
import ryzik.ui.TimerActor;

public class RespawnFragment extends Fragment {
    @Override
    public void build(Group group) {
        final Table table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        });

        TextActor textActor = new TextActor();
        textActor.setText("you die =(");
        textActor.setSize(256,32);
        table.add(textActor).row();

        TimerActor timerActor = new TimerActor(10);
        timerActor.getOnTimerEnd().on(new Runnable() {
            @Override
            public void run() {

            }
        });
        timerActor.setSize(256,64);
        table.center().add(timerActor).row();

        group.addActor(table);
    }
}
