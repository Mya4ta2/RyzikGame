package com.ryzik.content;

import com.badlogic.gdx.graphics.Texture;
import com.ryzik.ctype.ContentList;
import com.ryzik.type.Mob;
import com.ryzik.type.MobType;

public class MobTypes implements ContentList {
    public static MobType testEnemy;

    @Override
    public void load() {
        testEnemy = new MobType() {
            {
                texture = new Texture("error.png");
            }
        };
    }
}
