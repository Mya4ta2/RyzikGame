package ryzik.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ryzik.Vars;

public class TextActor extends Actor {
    private String text;
    private GlyphLayout glyphLayout;

    public TextActor() {
        glyphLayout = new GlyphLayout();
    }

    public TextActor(String text) {
        this.text = text;
        glyphLayout = new GlyphLayout();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        glyphLayout.setText(Vars.font, text);

        Vars.font.draw(
                batch,
                text,
                getX() + (getWidth() / 2) - glyphLayout.width/2,
                getY() + (getHeight() / 2) + glyphLayout.height/2
        );

        super.draw(batch, parentAlpha);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
