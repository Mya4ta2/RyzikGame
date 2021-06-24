package ryzik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import ryzik.type.rounding.RoundingAtlas;

public class Atlas {
    public Texture errorTexture;

    public Texture find(String name) {
        name = name + ".png";

        if (!Gdx.files.internal(name).exists()) return errorTexture;

        return new Texture(name);
    }

    public RoundingAtlas getRoundingAtlas(String name, String dir) {
        RoundingAtlas roundingAtlas = new RoundingAtlas();

        roundingAtlas.upLeft = find(dir + "/" + name + "-upLeft");
        roundingAtlas.up = find(dir + "/" + name + "-up");
        roundingAtlas.upRight = find(dir + "/" + name + "-upRight");
        roundingAtlas.left = find(dir + "/" + name + "-left");
        roundingAtlas.right = find(dir + "/" + name + "-right");
        roundingAtlas.downLeft = find(dir + "/" + name + "-downLeft");
        roundingAtlas.down = find(dir + "/" + name + "-down");
        roundingAtlas.downRight = find(dir + "/" + name + "-downRight");

        return roundingAtlas;
    }
}
