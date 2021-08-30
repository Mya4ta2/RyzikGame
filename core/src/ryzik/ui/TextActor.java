package ryzik.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ryzik.Vars;

public class TextActor extends Actor {
    private String text;
    private GlyphLayout glyphLayout;
    private boolean centerText;

    public TextActor() {
        glyphLayout = new GlyphLayout();
        centerText = true;
    }

    public TextActor(String text) {
        this.text = text;
        glyphLayout = new GlyphLayout();
        centerText = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (text == null) text = "";
        glyphLayout.setText(Vars.font, text);

        if (centerText)
            Vars.font.draw(
                batch,
                text,
                getX() + (getWidth() / 2) - glyphLayout.width/2,
                getY() + (getHeight() / 2) + glyphLayout.height/2
            );
        else
            Vars.font.draw(
                    batch,
                    text,
                    getX() + (getWidth() / 2),
                    getY() + (getHeight() / 2)
            );

        super.draw(batch, parentAlpha);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCenterText() {
        return centerText;
    }

    public void setCenterText(boolean centerText) {
        this.centerText = centerText;
    }
}
