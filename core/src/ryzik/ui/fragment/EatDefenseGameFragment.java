package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.type.EatDefenseGameState;
import ryzik.ui.*;

public class EatDefenseGameFragment extends Fragment {
    @Override
    public void build(Group group) {
        if (Vars.mobile) buildMobile(group);
        else if (Vars.desktop) buildDesktop(group);
    }

    private void buildMobile(Group group) {
        final Table rightUp = new Table();
        final Table up = new Table();
        final Table leftUp = new Table();
        final Table table = new Table();
        final Table joystickTable = new Table();
        final Table attackJoystickTable = new Table();

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                up.setSize(Gdx.graphics.getWidth()/3f,64);
                up.setPosition(Gdx.graphics.getWidth()/3f,Gdx.graphics.getHeight() - up.getHeight());

                leftUp.setSize(Gdx.graphics.getWidth()/3f,64);
                leftUp.setPosition(0,Gdx.graphics.getHeight() - leftUp.getHeight());

                rightUp.setSize(Gdx.graphics.getWidth()/3f,64);
                rightUp.setPosition(Gdx.graphics.getWidth()/3f + leftUp.getWidth(),Gdx.graphics.getHeight() - rightUp.getHeight());
                table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - rightUp.getHeight());

                joystickTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                attackJoystickTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        });

        final InventoryActor inventoryActor = new InventoryActor(Vars.player.inventory, 128);
        table.bottom().add(inventoryActor.getInventoryTable()).row();
        table.bottom().add(inventoryActor.getHotbarTable());

        Events.keyE.on(new Runnable() {
            @Override
            public void run() {
                inventoryActor.getInventoryTable().setVisible(!inventoryActor.getInventoryTable().isVisible());
            }
        });

        Vars.stage.setScrollFocus(inventoryActor.getHotbarTable().getChild(0));

        HealthBar healthBar = new HealthBar(1,false, Vars.skin, Vars.player);
        rightUp.right().top().add(healthBar);
        final TextActor fps = new TextActor();
        fps.setSize(128,64);
        Events.update.on(new Runnable() {
            @Override
            public void run() {
                fps.setText(String.valueOf(Gdx.graphics.getFramesPerSecond()));
            }
        });

        leftUp.left().top().add(fps);

        inventoryActor.getInventoryTable().setVisible(false);

        EatHealthBar eatHealthBar = new EatHealthBar(Vars.eat, Vars.skin);
        up.center().add(eatHealthBar);

        //TODO move textures to Vars.skin
        Joystick joystick = new Joystick(Vars.atlas.find("joystick"), Vars.atlas.find("joystickCur"));
        joystick.setName("joystick");
        joystick.setWidth(260);

        Joystick attackJoystick = new Joystick(Vars.atlas.find("joystick"), Vars.atlas.find("joystickCur"));
        attackJoystick.setName("attackJoystick");
        attackJoystick.setWidth(260);

        joystickTable.left().bottom().add(new Separator(32, 32));
        joystickTable.left().bottom().add(joystick).row();
        joystickTable.left().bottom().add(new Separator(32, 32));
        attackJoystickTable.right().bottom().add(attackJoystick);
        attackJoystickTable.right().bottom().add(new Separator(32, 32)).row();
        attackJoystickTable.right().bottom().add(new Separator(32, 32));

        group.addActor(joystickTable);
        group.addActor(attackJoystickTable);

        group.addActor(leftUp);
        group.addActor(up);
        group.addActor(rightUp);
        group.addActor(table);

        CursorActor cursorActor = new CursorActor();
        group.addActor(cursorActor);
    }

    private void buildDesktop(Group group) {
        final Table rightUp = new Table();
        final Table up = new Table();
        final Table leftUp = new Table();
        final Table table = new Table();

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                up.setSize(Gdx.graphics.getWidth()/3f,64);
                up.setPosition(Gdx.graphics.getWidth()/3f,Gdx.graphics.getHeight() - up.getHeight());

                leftUp.setSize(Gdx.graphics.getWidth()/3f,64);
                leftUp.setPosition(0,Gdx.graphics.getHeight() - leftUp.getHeight());

                rightUp.setSize(Gdx.graphics.getWidth()/3f,64);
                rightUp.setPosition(Gdx.graphics.getWidth()/3f + leftUp.getWidth(),Gdx.graphics.getHeight() - rightUp.getHeight());
                table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - rightUp.getHeight());
            }
        });

        final InventoryActor inventoryActor = new InventoryActor(Vars.player.inventory, 64);
        table.bottom().add(inventoryActor.getInventoryTable()).row();
        table.bottom().add(inventoryActor.getHotbarTable());

        Events.keyE.on(new Runnable() {
            @Override
            public void run() {
                inventoryActor.getInventoryTable().setVisible(!inventoryActor.getInventoryTable().isVisible());
            }
        });

        Vars.stage.setScrollFocus(inventoryActor.getHotbarTable().getChild(0));

        rightUp.right().top().add(new HealthBar(1,false, Vars.skin, Vars.player));
        final TextActor fps = new TextActor();
        fps.setSize(128,64);
        Events.update.on(new Runnable() {
            @Override
            public void run() {
                fps.setText(String.valueOf(Gdx.graphics.getFramesPerSecond()));
            }
        });

        leftUp.left().top().add(fps);

        inventoryActor.getInventoryTable().setVisible(false);

        EatHealthBar eatHealthBar = new EatHealthBar(Vars.eat, Vars.skin);
        up.center().add(eatHealthBar);

        group.addActor(leftUp);
        group.addActor(up);
        group.addActor(rightUp);
        group.addActor(table);

        CursorActor cursorActor = new CursorActor();
        group.addActor(cursorActor);
    }
}
