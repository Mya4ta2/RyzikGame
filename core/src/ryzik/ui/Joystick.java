package ryzik.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Joystick extends Actor {

    public static final float CUR_RADIUS = 20;
    private Texture circle;
    private Texture curJoystick;
    private boolean isJoystickDown = false;
    private float rad = 160;
    private float curX = 0;
    private float curY = 0;

    public Joystick(Texture circle, Texture curJoystick) {
        this.circle = circle;
        this.curJoystick = curJoystick;
        setDefaultWH();
        setDefaultXY();
        addListener(new JoystickInputListener(this));
    }

    public void setDefaultWH() {
        setWidth(160);
        setHeight(160);
        rad = 80;
    }

    public void resetCur() {
        curX = 0;
        curY = 0;
    }

    public void setTouched() {
        isJoystickDown = true;
    }

    public void setUntouched() {
        isJoystickDown = false;
    }

    public void changeCursor(float x, float y) {
        float dx = x - rad;
        float dy = y - rad;
        float lenght = (float) Math.sqrt(dx * dx + dy * dy);
        if (lenght < rad) {
            this.curX = dx;
            this.curY = dy;
        } else {
            float k = rad / lenght;
            this.curX = dx * k;
            this.curY = dy * k;
        }

    }

    @Override
    public Actor hit(float x, float y, boolean touchable){
        Actor actor = super.hit(x, y, touchable);
        if (actor == null) return null;
        else {
            float dx = x - rad;
            float dy = y - rad;

            return  (dx * dx + dy * dy <= rad * rad) ? this : null;
        }
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        super.setHeight(width);
        rad = width / 2;
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        super.setWidth(height);
        rad = height / 2;
    }

    public void setDefaultXY() {
        setX(40);
        setY(40);
    }

    public Vector2 getCurPosition() {
        return new Vector2(curX, curY).nor();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(circle, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        if (isJoystickDown) {
            batch.draw(curJoystick,
                    this.getX() + rad - CUR_RADIUS + curX,
                    this.getY() + rad - CUR_RADIUS + curY,
                    2 * CUR_RADIUS,
                    2 * CUR_RADIUS);
        } else {
            batch.draw(curJoystick,
                    this.getX() + rad - CUR_RADIUS,
                    this.getY() + rad - CUR_RADIUS,
                    2 * CUR_RADIUS,
                    2 * CUR_RADIUS);
        }
    }
}
