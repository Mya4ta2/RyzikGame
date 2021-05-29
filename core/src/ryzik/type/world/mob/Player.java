package ryzik.type.world.mob;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.Cursor;
import ryzik.Vars;
import ryzik.type.PlayerController;
import ryzik.type.item.Inventory;
import ryzik.type.world.item.weapon.SwordType;
import ryzik.ui.dialog.Dialog;
import ryzik.ui.TimerActor;

public class Player extends Mob {
    public Inventory inventory;

    public Player() {
        controller = new PlayerController(this);
    }

    public Player(MobType type) {
        super(type);
        controller = new PlayerController(this);
    }

    @Override
    public void dead() {
        if (dead) return;
        final Dialog dialog = new Dialog();

        Group group = new Group();
        Vars.ui.respawnFragment.build(group);
        ((TimerActor)(((Table) group.getChild(0)).getChild(1))).getOnTimerEnd().on(new Runnable() {
            @Override
            public void run() {
                dialog.hide();
                respawn();
            }
        });
        dialog.center().add(group);
        dialog.show();

        super.dead();
    }

    @Override
    public void draw() {
        super.draw();
        currentWeapon.draw();
    }

    @Override
    public void update(float delta) {
        if (currentWeapon != null && currentWeapon.type instanceof SwordType) {
            currentWeapon.angle = Cursor.angle;
        }

        super.update(delta);
    }
}
