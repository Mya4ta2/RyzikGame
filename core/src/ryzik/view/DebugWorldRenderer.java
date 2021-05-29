package ryzik.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Blocks;
import ryzik.content.Floors;
import ryzik.ctype.Renderer;
import ryzik.type.world.Tile;
import ryzik.type.world.World;
import ryzik.type.world.mob.Mob;

import java.util.Arrays;

public class DebugWorldRenderer implements Renderer {
    private final World world;
    private ShapeRenderer shapeRenderer;

    public DebugWorldRenderer(World world) {
        this.world = world;
    }

    @Override
    public void init() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        shapeRenderer.setProjectionMatrix(Draw.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (int x = world.getTilemap().getWidth()-1; x > -1; x--) {
            for (int y = world.getTilemap().getHeight()-1; y > -1; y--) {
                final Tile tile = world.getTilemap().get(x, y);
                if (tile.floor != Floors.air) {
                    shapeRenderer.setColor(Color.BLUE);
                    shapeRenderer.rect(tile.x * Vars.TileSize, tile.y * Vars.TileSize, Vars.TileSize, Vars.TileSize);
                }

                if (tile.block != Blocks.air) {
                    shapeRenderer.setColor(Color.RED);
                    shapeRenderer.rect(tile.x * Vars.TileSize, tile.y * Vars.TileSize, Vars.TileSize, Vars.TileSize);
                }
            }
        }

        drawMobs();


        shapeRenderer.end();
    }

    private void drawMobs() {
        for (Mob mob : world.getMobs().getArray().iterator()) {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(
                    mob.position.x * Vars.TileSize,
                    mob.position.y * Vars.TileSize,
                    mob.type != null ? mob.type.getWidth() * Vars.TileSize : Vars.TileSize,
                    mob.type != null ? mob.type.getHeight() * Vars.TileSize : Vars.TileSize);

            shapeRenderer.setColor(Color.BLACK);
            if (mob.currentWeapon != null) {
                float[] vertices = mob.currentWeapon.bounds.shape.getTransformedVertices();

                for (int i = 0; i < vertices.length; i++) {
                    vertices[i] *= Vars.TileSize;
                }

                shapeRenderer.polygon(vertices);
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }
}
