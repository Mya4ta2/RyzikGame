package ryzik.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import ryzik.Cursor;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Blocks;
import ryzik.content.Floors;
import ryzik.type.world.World;
import ryzik.view.UIRenderer;
import ryzik.view.WorldRenderer;

public class WorldEditorScreen implements Screen {
    public World world;
    public WorldRenderer worldRenderer;
    public WorldEditorController worldController;

    @Override
    public void show() {
        world = new World(50,50);

        for (int x = 0; x < world.getTilemap().getWidth(); x++) {
            for (int y = 0; y < world.getTilemap().getHeight(); y++) {
                world.getTilemap().get(x, y).floor = Floors.grass;
            }
        }

        Draw.camera.position.set(25 * Vars.TileSize, 25 * Vars.TileSize, 0);
        Cursor.block = Blocks.stoneBrick;

        worldRenderer = new WorldRenderer(world);
        worldController = new WorldEditorController(world);
    }

    @Override
    public void render(float delta) {
        Draw.batch.begin();
        worldRenderer.render(delta);
        Draw.onDraw.fire();
        Draw.batch.end();

        worldController.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
