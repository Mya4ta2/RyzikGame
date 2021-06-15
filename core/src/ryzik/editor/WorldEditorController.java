package ryzik.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import ryzik.Cursor;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.ctype.Controller;
import ryzik.type.world.World;

public class WorldEditorController implements Controller {
    public World world;

    public WorldEditorController(World world) {
        this.world = world;
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (Cursor.block != null) {
                Vector2 pos = Cursor.unProject(Draw.camera);

                world.getTilemap().get((int) pos.x / Vars.TileSize, (int) pos.y / Vars.TileSize).block = Cursor.block;
            }
        }
    }

    @Override
    public void dispose() {

    }
}
