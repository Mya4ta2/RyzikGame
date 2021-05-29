package ryzik;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import ryzik.type.Event;
import ryzik.view.DrawLayers;

public class Draw {
    public static OrthographicCamera camera;
    public static Viewport viewport;
    public static SpriteBatch batch;
    public static Event onDraw;
    public static DrawLayers drawLayers;
}
