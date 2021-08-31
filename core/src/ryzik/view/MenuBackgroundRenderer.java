package ryzik.view;

import ryzik.Vars;
import ryzik.ctype.Renderer;

public class MenuBackgroundRenderer implements Renderer {
    private TilemapRenderer tilemapRenderer;

    @Override
    public void init() {
        tilemapRenderer = new TilemapRenderer(Vars.mapsLoader.maps.get(0).createWorld().getTilemap());
        tilemapRenderer.init();
    }

    @Override
    public void render(float delta) {
        tilemapRenderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }
}
