package ryzik.type;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;

import ryzik.Vars;
import ryzik.type.world.mob.Mob;
import ryzik.ui.Joystick;

public class PlayerController extends MobController {
    private Joystick joystick;

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
        if (Vars.stage.getActors().get(0) instanceof Group) {
            joystick = ((Group) Vars.stage.getActors().get(0)).findActor("joystick");
        }

        if (joystick != null)
            mob.velocity.add(
                joystick.getCurPosition().x * mob.type.getSpeed(),
                joystick.getCurPosition().y * mob.type.getSpeed()
            );
    }

    private void updateDesktop() {
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

    @Override
    public void dispose() {

    }
}
