package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.ui.ContentSelect;
import ryzik.ui.Joystick;
import ryzik.ui.Separator;

public class WorldEditorFragment extends Fragment {

    @Override
    public void build(Group group) {
        if (Vars.mobile) buildMobile(group);
        else if (Vars.desktop) buildDesktop(group);
    }

    public void buildDesktop(Group group) {
        final Table table = new Table();

        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        });

        ContentSelect contentSelect = new ContentSelect();
        contentSelect.setWidth(table.getWidth()/3);
        table.right().top().add(contentSelect);

        group.addActor(table);
    }

    private void buildMobile(Group group) {
        final Table table = new Table();
        final Table joystickTable = new Table();

        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        joystickTable.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                joystickTable.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            }
        });

        ContentSelect contentSelect = new ContentSelect();
        contentSelect.setWidth(table.getWidth()/3);
        table.right().top().add(contentSelect);

        Joystick joystick = new Joystick(Vars.atlas.find("joystick"), Vars.atlas.find("joystickCur"));
        joystick.setName("joystick");
        joystick.setWidth(260);
        joystickTable.left().bottom().add(new Separator(32, 32));
        joystickTable.add(joystick).row();
        joystickTable.left().bottom().add(new Separator(32, 32));

        group.addActor(table);
        group.addActor(joystickTable);
    }
}
