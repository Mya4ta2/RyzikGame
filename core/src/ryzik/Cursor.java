package ryzik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ryzik.ctype.Content;
import ryzik.type.world.block.Block;
import ryzik.type.world.item.ItemStack;

public class Cursor {
    public static int x, y;
    public static Content content;
    public static ItemStack selectedItem;
    public static float angle;

    public static void update() {
        x = (Gdx.input.getX());
        y = (Gdx.graphics.getHeight() - Gdx.input.getY());

        angle = getAngle(
                0,
                0,
                x,
                y,
                Gdx.graphics.getWidth()/2f,
                Gdx.graphics.getHeight()/2f
        );

        angle = -angle;
        angle *= MathUtils.radiansToDegrees;
        angle += 30; // i no know what it`s number, but she make all work perfect
        if (angle < 0) angle += 360;

        if (selectedItem != null && selectedItem.empty()) selectedItem = null;
    }

    public static float getAngle(float point1X, float point1Y,
                                 float point2X, float point2Y,
                                 float fixedX, float fixedY) {

        float angle1 = (float) Math.atan2(point1Y - fixedY, point1X - fixedX);
        float angle2 = (float) Math.atan2(point2Y - fixedY, point2X - fixedX);

        return angle1 - angle2;
    }

    public static Vector2 unProject(OrthographicCamera camera) {
        float centeredX = x - camera.viewportWidth/2;
        float centeredY = y - camera.viewportHeight/2;
        return new Vector2(centeredX + camera.position.x, centeredY + camera.position.y);
    }
}
