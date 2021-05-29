package ryzik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Atlas {
    public Texture errorTexture;

    public Texture find(String name) {
        name = name + ".png";

        if (!Gdx.files.internal(name).exists()) return errorTexture;

        return new Texture(name);
    }
}
