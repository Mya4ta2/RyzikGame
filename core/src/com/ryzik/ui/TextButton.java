package com.ryzik.ui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextButton extends Button {

    private String text;
    private BitmapFont font;
    private GlyphLayout glyphLayout;

    public TextButton(Texture unPressedButton, Texture pressedButton, Sound pressSound, BitmapFont font) {
        super(unPressedButton, pressedButton, pressSound);

        this.font = font;

        setDefaultText();

        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
    }

    public TextButton(Texture unPressedButton, Texture pressedButton, BitmapFont font) {
        super(unPressedButton, pressedButton);

        this.font = font;

        setDefaultText();

        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
    }

    public void setDefaultText() {
        text = "defaultText";
    }

    public void setText(String text) {
        this.text = text;
        glyphLayout.setText(font, text);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float w = glyphLayout.width;
        float h =  glyphLayout.height;

        font.draw(batch,
                text,
                getX() + (getWidth() - w) / 2,
                getY() + getHeight() - (getHeight() - h) / 2);
    }
}
