package ryzik.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.ui.CursorActor;
import ryzik.ui.HealthBar;
import ryzik.ui.InventoryActor;
import ryzik.ui.TextActor;

public class GameFragment extends Fragment{
    @Override
    public void build(Group group) {
        final Table up = new Table();
        final Table leftUp = new Table();
        final Table table = new Table();

        Events.resize.on(new Runnable() {
            @Override
            public void run() {
                up.setSize(Gdx.graphics.getWidth()/2f,64);
                up.setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight() - up.getHeight());
                leftUp.setSize(Gdx.graphics.getWidth()/2f,64);
                leftUp.setPosition(0,Gdx.graphics.getHeight() - leftUp.getHeight());
                table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - up.getHeight());
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

        up.right().top().add(new HealthBar(1,false, Vars.skin, Vars.player));
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

        group.addActor(up);
        group.addActor(leftUp);
        group.addActor(table);

        CursorActor cursorActor = new CursorActor();
        group.addActor(cursorActor);
    }
}
