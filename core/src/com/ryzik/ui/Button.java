package com.ryzik.ui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ryzik.Vars;

public class Button extends Actor {

    private boolean pressed = false;
    private boolean useSound = true;

    private Texture unPressedButton;
    private Texture PressedButton;
    private Sound pressSound;


    public Button(Texture unPressedButton, Texture pressedButton, Sound pressSound) {
        this.unPressedButton = unPressedButton;
        PressedButton = pressedButton;
        this.pressSound = pressSound;

        addListener(new ButtonInputListener(this));
        setDefaultWH();
        setDefaultXY();
    }

    public Button(Texture unPressedButton, Texture pressedButton) {
        this.unPressedButton = unPressedButton;
        PressedButton = pressedButton;
        useSound = false;

        addListener(new ButtonInputListener(this));
        setDefaultWH();
        setDefaultXY();
    }

    public void setDefaultWH() {
        setWidth(160);
        setHeight(160);
    }

    public void setDefaultXY() {
        setX(40);
        setY(40);
    }


    public void press() {
        pressed = true;
        if (useSound) {
            pressSound.play(Vars.UI_VOLUME);
        }
    }

    public void unPress() {
        pressed = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (pressed) {
            batch.draw(PressedButton, getX(), getY(), getWidth(), getHeight());
        } else {
            batch.draw(unPressedButton, getX(), getY(), getWidth(), getHeight());
        }
    }
}
