package ryzik.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ryzik.type.world.Building;

public class EatHealthBar extends ProgressBar {
    private Building eat;

    public EatHealthBar(final Building eat, Skin skin) {
        super(0, eat.type.health, 1, false, skin);
        this.eat = eat;

        setValue(eat.health);
        eat.onHealthChange.on(new Runnable() {
            @Override
            public void run() {
                setValue(eat.health);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(eat.type.texture, getX() - 48, getY() - 16, 64, 64);
    }
}
