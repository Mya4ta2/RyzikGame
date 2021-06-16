package ryzik.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import ryzik.Cursor;
import ryzik.ctype.Content;
import ryzik.type.world.block.Block;
import ryzik.type.world.floor.Floor;

public class ContentSlot extends Actor {
    private Texture background;
    private Content content;

    public ContentSlot(Texture background, final Content content) {
        this.background = background;
        this.content = content;

        setDefaultSize();

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Cursor.content = content;
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void setDefaultSize() {
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(background, getX(), getY(), getWidth(), getHeight());
        batch.draw(getContentTexture(), getX(), getY());
    }

    public Texture getContentTexture() {
        Texture texture;
        if (content instanceof Block) texture = ((Block) content).texture;
        else if (content instanceof Floor) texture = ((Floor) content).texture;
        else throw new RuntimeException();

        return texture;
    }
}
