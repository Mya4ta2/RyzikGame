package ryzik.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Blocks;
import ryzik.content.Floors;
import ryzik.ctype.Renderer;
import ryzik.type.world.Tile;
import ryzik.type.world.Tilemap;
import ryzik.type.world.mob.Mob;

public class TilemapRenderer implements Renderer {
    private final Tilemap tilemap;

    public TilemapRenderer(Tilemap tilemap) {
        this.tilemap = tilemap;
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        for (int x = tilemap.getWidth()-1; x > -1; x--) {
            for (int y = tilemap.getHeight()-1; y > -1; y--) {
                final Tile tile = tilemap.get(x, y);

                if (tile.floor != Floors.air) {
                    Draw.drawLayers.floor.add(
                            new Drawable() {
                                @Override
                                public void draw(SpriteBatch spriteBatch) {
                                    spriteBatch.draw(tile.floor.texture,
                                            tile.x * Vars.TileSize,
                                            tile.y * Vars.TileSize,
                                            Vars.TileSize,
                                            Vars.TileSize
                                    );
                                }
                            }
                    );
                }

                if (tile.block != Blocks.air) {
                    Draw.drawLayers.block.add(
                            new Drawable() {
                                @Override
                                public void draw(SpriteBatch spriteBatch) {
                                    if (tile.block == Blocks.tree)
                                        tile.block.draw(tile.x, tile.y);
                                    else
                                    Draw.batch.draw(
                                            tile.blockRounding.getCurrentTexture(),
                                            tile.x * Vars.TileSize,
                                            tile.y * Vars.TileSize,
                                            Vars.TileSize,
                                            Vars.TileSize
                                    );
                                }
                            }
                    );
                }

                if (tile.building != null) {
                    Draw.drawLayers.block.add(new Drawable() {
                        @Override
                        public void draw(SpriteBatch spriteBatch) {
                            tile.building.draw();
                        }
                    });
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }
}
