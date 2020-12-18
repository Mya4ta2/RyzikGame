package com.ryzik.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Image extends Actor {

    private TextureRegion texture;

    public Image(TextureRegion region) {
        this.texture = region;

        setWidth(region.getRegionWidth());
        setHeight(region.getRegionHeight());
        setDefaultXY();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture,getX(),getY(), getWidth(), getHeight());
    }

    public void setDefaultWH() {
        setWidth(160);
        setHeight(160);
    }

    public void setDefaultXY() {
        setX(40);
        setY(40);
    }
}
