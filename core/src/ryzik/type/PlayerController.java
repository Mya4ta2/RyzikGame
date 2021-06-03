package ryzik.type;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ryzik.Cursor;
import ryzik.Vars;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.mob.Player;
import ryzik.ui.Joystick;

public class PlayerController extends MobController {
    private Joystick joystick;
    private Joystick attackJoystick;

    public PlayerController(Mob mob) {
        super(mob);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        if (Vars.applicationType == Application.ApplicationType.Android)
            updateAndroid();
        else
            updateDesktop();
    }

    private void updateAndroid() {
        if (joystick == null && attackJoystick == null) initJoysticks();

        if (joystick != null)
            mob.velocity.add(
                joystick.getCurPosition().x * mob.type.getSpeed(),
                joystick.getCurPosition().y * mob.type.getSpeed()
            );
    }

    private void updateDesktop() {
        if (mob.currentWeapon != null)
            mob.currentWeapon.angle = Cursor.angle;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            mob.velocity.y += mob.type.getSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            mob.velocity.y -= mob.type.getSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            mob.velocity.x += mob.type.getSpeed();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            mob.velocity.x -= mob.type.getSpeed();
        }
    }

    private void initJoysticks() {
        if (Vars.stage.getActors().get(0) instanceof Group) {
            joystick = ((Group) Vars.stage.getActors().get(0)).findActor("joystick");
            attackJoystick = ((Group) Vars.stage.getActors().get(0)).findActor("attackJoystick");

            attackJoystick.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (mob.currentWeapon != null) mob.currentWeapon.angle = attackJoystick.getAngle();
                    mob.attack();
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
    }

    @Override
    public void dispose() {

    }
}
