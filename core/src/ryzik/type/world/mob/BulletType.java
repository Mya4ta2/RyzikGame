package ryzik.type.world.mob;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ryzik.Vars;

public class BulletType extends MobType{
    public final String name;
    public Texture texture;
    public float damage;

    public BulletType(String name) {
        super(name);

        this.name = name;
        texture = Vars.atlas.find("bullets/" + name + ".png");
    }

    @Override
    public void draw(Vector2 position) {
        super.draw(position);
    }
}
