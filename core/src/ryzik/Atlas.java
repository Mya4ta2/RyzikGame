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

    public RoundingAtlas getRoundingAtlas(String name) {
        RoundingAtlas roundingAtlas = new RoundingAtlas();

        roundingAtlas.upLeft = find(name + "-upLeft");
        roundingAtlas.up = find(name + "-up");
        roundingAtlas.upRight = find(name + "-upRight");
        roundingAtlas.left = find(name + "-left");
        roundingAtlas.right = find(name + "-right");
        roundingAtlas.downLeft = find(name + "-downLeft");
        roundingAtlas.down = find(name + "-down");
        roundingAtlas.downRight = find(name + "-downRight");

        return roundingAtlas;
    }
}
