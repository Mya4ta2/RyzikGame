package ryzik.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ryzik.Draw;
import ryzik.ctype.Renderer;
import ryzik.type.world.mob.Mob;
import ryzik.type.world.World;

public class WorldRenderer implements Renderer {
    private final World world;
    private final TilemapRenderer tilemapRenderer;

    public WorldRenderer(World world) {
        this.world = world;

        tilemapRenderer = new TilemapRenderer(world.getTilemap());
    }

    @Override
    public void init() {
        tilemapRenderer.init();
    }

    @Override
    public void render(float delta) {
        tilemapRenderer.render(delta);
        renderMobs();
    }

    public void renderMobs() {
        Draw.drawLayers.mob.add(new Drawable() {
            @Override
            public void draw(SpriteBatch spriteBatch) {
                for (Mob mob : world.getMobs().getArray().iterator()) {
                    mob.draw();
                }
            }
        });
    }

    @Override
    public void resize(int width, int height) {

    }
}
