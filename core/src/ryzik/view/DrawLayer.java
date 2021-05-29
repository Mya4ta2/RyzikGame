package ryzik.view;

import ryzik.Draw;

import java.util.ArrayList;

public class DrawLayer {
    public final ArrayList<Drawable> draw = new ArrayList<>();

    public void draw() {
        for (Drawable drawable : draw) {
            drawable.draw(Draw.batch);
        }

        draw.clear();
    }

    public void add(Drawable drawable) {
        draw.add(drawable);
    }
}
