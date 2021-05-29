package ryzik.type;

import ryzik.ctype.Controller;
import ryzik.type.world.mob.Mob;

public abstract class MobController implements Controller {
    public final Mob mob;

    public MobController(Mob mob) {
        this.mob = mob;
    }
}
