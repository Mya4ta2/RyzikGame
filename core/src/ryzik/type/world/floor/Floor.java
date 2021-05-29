package ryzik.type.world.floor;

import com.badlogic.gdx.graphics.Texture;
import ryzik.Vars;

public class Floor {
    public final String name;
    public Texture texture;

    public Floor(String name) {
        this.name = name;

        texture = Vars.atlas.find("floor/" + name);
    }
}
