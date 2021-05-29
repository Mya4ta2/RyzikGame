package ryzik.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ryzik.Vars;

public class Separator extends Actor {
    public Separator() {
        //default size and pos
        setX(100);
        setY(100);
        setWidth(100);
        setHeight(100);
    }

    public Separator(int height) {
        setX(100);
        setY(100);
        setWidth(100);
        setHeight(height);
    }

    public Separator(int height, int width) {
        setX(100);
        setY(100);
        setWidth(width);
        setHeight(height);
    }
}
