package ryzik.view;

import ryzik.Draw;

public class DrawLayers {
    public DrawLayer floor, mob, block;

    public DrawLayers() {
        floor = new DrawLayer();
        mob = new DrawLayer();
        block = new DrawLayer();

        Draw.onDraw.on(new Runnable() {
            @Override
            public void run() {
                floor.draw();
                mob.draw();
                block.draw();
            }
        });
    }
}
