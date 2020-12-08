package com.ryzik.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ButtonInputListener extends InputListener {
    Button button;

    public ButtonInputListener(Button button) {
        this.button = button;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.button.press();
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        this.button.unPress();
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
    }
}
