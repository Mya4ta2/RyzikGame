package ryzik.view;

import ryzik.Vars;
import ryzik.ctype.Renderer;
import ryzik.type.world.World;

public class MenuBackgroundRenderer implements Renderer {
    private WorldRenderer worldRenderer;

    @Override
    public void init() {
        worldRenderer = new WorldRenderer(new World(Vars.mapsLoader.maps.get(0).createWorld().getTilemap()));
        worldRenderer.init();
    }

    @Override
    public void render(float delta) {
        worldRenderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }
}
