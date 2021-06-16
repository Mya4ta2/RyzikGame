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
    public WorldEditorScreen worldEditorScreen;
    public World world;
    private float cameraSpeed;

    public WorldEditorController(World world, WorldEditorScreen editorScreen) {
        this.world = world;
        this.worldEditorScreen = editorScreen;
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

                if (worldEditorScreen.uiRenderer.stage.hit(Cursor.x, Cursor.y,true) == null)
                        world.getTilemap().get(
                                (int) pos.x / Vars.TileSize,
                                (int) pos.y / Vars.TileSize
                        ).block = (Block) Cursor.content;
            }
        }

        cameraSpeed = 1.5f;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            Draw.camera.position.add(0, cameraSpeed,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            Draw.camera.position.add(0, -cameraSpeed,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            Draw.camera.position.add(cameraSpeed, 0,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            Draw.camera.position.add(-cameraSpeed, 0,0);
        }
    }

    @Override
    public void dispose() {

    }
}
