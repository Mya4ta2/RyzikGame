package ryzik.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class LinkTextActor extends TextActor {
    public LinkTextActor(String url) {
        super();
        addLinkListener(url);
    }

    public LinkTextActor(String text, String url) {
        super(text);
        addLinkListener(url);
    }

    public void addLinkListener(final String url) {
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.net.openURI(url);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}
