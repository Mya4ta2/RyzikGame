package com.ryzik.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TextField extends Actor {

    private char currentChar;
    private int currentKeycode;
    private boolean newKey;
    private String text = "";
    private boolean focus = false;
    private BitmapFont font;
    private TextureRegion background;

    public TextField(TextureRegion texture) {
        this.background = texture;
        font = new BitmapFont();
        font.setColor(Color.BLACK);

        setDefaultWH();
        setDefaultXY();

        addListener(new InputListener() {
            @Override
            public boolean handle(Event e) {
                return super.handle(e);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                focus = !focus;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                currentKeycode = keycode;
                newKey = true;
                return super.keyDown(event, keycode);
            }

            @Override
            public boolean keyTyped(InputEvent event, char character) {
                currentChar = character;
                return super.keyTyped(event, character);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(background, getX(),getY(), getWidth(), getHeight());
        font.draw(batch, text, getX(), getY() + 20);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (focus) {
            if (currentChar != ' ') {
                text = text + currentChar;
                currentChar = ' ';
            }

            if (currentKeycode == Input.Keys.BACKSPACE && text.length() > 1 && newKey) {
                text = text.substring(0, text.length()-1);
                System.out.println(text.substring(0, text.length()-1));
                newKey = false;
            }

            if (currentKeycode == Input.Keys.SPACE && newKey) {
                text = text + " ";
                newKey = false;
            }
        }
    }

    public void setDefaultWH() {
        setWidth(300);
        setHeight(30);
    }

    public void setDefaultXY() {
        setX(40);
        setY(40);
    }

    public boolean isFocus() {
        return focus;
    }
}
