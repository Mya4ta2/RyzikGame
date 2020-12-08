package com.ryzik.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ryzik.view.UIRenderer;

public class UIController {

    private UIRenderer uiRenderer; // oh...

    public UIController(UIRenderer uiRenderer) {
        this.uiRenderer = uiRenderer;
    }

    public void update(float delta) {
        processInput();
    }

    public void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            uiRenderer.setCurrentStage(uiRenderer.getResumeStage());
        }
    }
}
