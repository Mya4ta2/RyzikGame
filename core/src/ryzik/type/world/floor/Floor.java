package ryzik.type.world.floor;

import com.badlogic.gdx.graphics.Texture;
import ryzik.Vars;
import ryzik.ctype.Content;

public class Floor extends Content {
    public final String name;
    public Texture texture;

    public Floor(String name) {
        this.name = name;

        texture = Vars.atlas.find("floor/" + name);
    }
}
