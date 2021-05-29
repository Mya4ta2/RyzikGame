package ryzik.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ryzik.Vars;
import ryzik.type.Event;

public class TimerActor extends Actor {
    private final float seconds;
    private float currentSeconds;
    private GlyphLayout glyphLayout;

    private Event onTimerEnd;

    public TimerActor(int seconds) {
        this.seconds = seconds;
        currentSeconds = seconds;
        glyphLayout = new GlyphLayout();
        onTimerEnd = new Event();
    }

    @Override
    public void act(float delta) {
        currentSeconds -= Gdx.graphics.getDeltaTime();

        if (currentSeconds <= 0) {
            onTimerEnd.fire();
            remove();
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        glyphLayout.setText(Vars.font,String.valueOf((int) currentSeconds));

        Vars.font.draw(
                batch,
                String.valueOf((int) currentSeconds),
                getX() + (getWidth() / 2) - glyphLayout.width/2,
                getY() + (getHeight() / 2) + glyphLayout.height/2
        );

        super.draw(batch, parentAlpha);
    }

    public Event getOnTimerEnd() {
        return onTimerEnd;
    }
}
