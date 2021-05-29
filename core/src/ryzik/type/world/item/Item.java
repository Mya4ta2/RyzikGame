package ryzik.type.world.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ryzik.Vars;

public class Item {
    public final String name;
    public int stackSize = Vars.StackSize;
    public Texture texture;
    public boolean consumable;

    public Item(String name) {
        this.name = name;

        texture = Vars.atlas.find("items/" + name);
    }

    public void leftMouseUse() {

    }

    public void rightMouseUse() {

    }
}
