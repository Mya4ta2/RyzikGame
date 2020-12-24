package com.ryzik.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ryzik.view.UIRenderer;

public class UIController {

    private UIRenderer uiRenderer; // oh... but nothing

    public UIController(UIRenderer uiRenderer) {
        this.uiRenderer = uiRenderer;
    }

    public void update(float delta) {
        processInput();
    }

    public void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (uiRenderer.getCurrentStage() == uiRenderer.getInventoryStage()) {
                uiRenderer.setCurrentStage(uiRenderer.getGameStage());
            } else {
                uiRenderer.setCurrentStage(uiRenderer.getInventoryStage());
            }
        }
    }
}
