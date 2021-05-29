package ryzik.type.world;

import com.badlogic.gdx.utils.Array;
import ryzik.type.world.mob.Mob;

import java.io.Serializable;

public class Mobs {
    private final Array<Mob> array = new Array<>();

    public Array<Mob> getArray() {
        return array;
    }

    public void add(Mob mob) {
        mob.mobs = this;
        array.add(mob);
    }
}
