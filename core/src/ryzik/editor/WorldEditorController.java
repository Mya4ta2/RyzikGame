package ryzik.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import ryzik.Cursor;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.ctype.Controller;
import ryzik.type.world.World;
import ryzik.type.world.block.Block;

public class WorldEditorController implements Controller {
    public World world;
    private float cameraSpeed;

    public WorldEditorController(World world) {
        this.world = world;
    }

    @Override
    public void init() {
        Draw.camera.position.setZero();
    }

    @Override
    public void update() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (Cursor.content instanceof Block) {
                Vector2 pos = Cursor.unProject(Draw.camera);

                world.getTilemap().get((int) pos.x / Vars.TileSize, (int) pos.y / Vars.TileSize).block = (Block) Cursor.content;
            }
        }
    }

    @Override
    public void dispose() {

    }
}
