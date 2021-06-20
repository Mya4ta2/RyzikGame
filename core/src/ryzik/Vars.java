package ryzik;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ryzik.type.GameState;
import ryzik.type.world.Building;
import ryzik.type.world.mob.Player;
import ryzik.type.world.World;
import ryzik.ui.UI;

public class Vars {
    public static ContentLoader content;
    public static Atlas atlas;
    public static UI ui;
    public static BitmapFont font;
    public static Skin skin;
    public static Stage stage;
    public static Building eat;
    public static ScreenController screenController;
    public static MapsLoader mapsLoader;
    public static GameState gameState;
    public static Application.ApplicationType applicationType;
    public static boolean mobile, desktop;

    public static Player player;
    public static World world;

    public static final int TileSize = 32;
    public static final int StackSize = 99;

    public static String worldDir = "world";
}
