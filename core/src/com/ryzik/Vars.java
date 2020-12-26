package com.ryzik;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ryzik.type.World;

public class Vars {
    public static final int TILE_SIZE = 32;

    public static TextureRegion ERROR_TEXTURE;

    //sound settings
    public static float SOUND_VOLUME = 1f;
    public static float MUSIC_VOLUME = 1f;
    public static float UI_VOLUME = 1f;

    //content folders
    public static final String worldFolder = "world";

    //oh no...
    public static TextureRegion PLAYER_LEFT_TEXTURE;
    public static TextureRegion PLAYER_RIGHT_TEXTURE;

    //player settings
    public static World world;
    public static final int INVENTORY_WIDTH = 6;
    public static final int INVENTORY_HEIGHT = 5;
    public static final int STACK_SIZE = 100;
}
