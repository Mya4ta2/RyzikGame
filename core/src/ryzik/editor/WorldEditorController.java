package ryzik.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import ryzik.Cursor;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Teams;
import ryzik.ctype.Controller;
import ryzik.type.rounding.RoundingAtlas;
import ryzik.type.world.Building;
import ryzik.type.world.Tile;
import ryzik.type.world.World;
import ryzik.type.world.block.Block;
import ryzik.type.world.floor.Floor;
import ryzik.ui.Joystick;

public class WorldEditorController implements Controller {
    public WorldEditorScreen worldEditorScreen;
    public World world;
    private float cameraSpeed;
    private Joystick joystick;

    public WorldEditorController(World world, WorldEditorScreen editorScreen) {
        this.world = world;
        this.worldEditorScreen = editorScreen;
    }

    @Override
    public void init() {
        Draw.camera.position.setZero();
    }

    public Texture numToTexture(int num, Tile tile) {
        switch (num) {
            case 1: return tile.block.roundingAtlas.upLeft;
            case 2: return tile.block.roundingAtlas.up;
            case 3: return tile.block.roundingAtlas.upRight;
            case 4: return tile.block.roundingAtlas.left;
            case 5: return tile.block.roundingAtlas.right;
            case 6: return tile.block.roundingAtlas.downLeft;
            case 7: return tile.block.roundingAtlas.down;
            case 8: return tile.block.roundingAtlas.downRight;
        }

        return null;
    }

    @Override
    public void update() {
        if (Vars.mobile && joystick == null) initJoystick();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (Cursor.content instanceof Block) {
                Vector2 pos = Cursor.unProject(Draw.camera);

                if (worldEditorScreen.uiRenderer.stage.hit(Cursor.x, Cursor.y,true) == null)
                    world.createBuilding(
                            world.getTilemap().get(
                                    (int) pos.x / Vars.TileSize,
                                    (int) pos.y / Vars.TileSize
                            ),
                            (Block) Cursor.content,
                            Teams.orange
                    );

                world.getTilemap().get(
                        (int) pos.x / Vars.TileSize,
                        (int) pos.y / Vars.TileSize
                ).blockRounding.setCurrentTexture(numToTexture(worldEditorScreen.rounding, world.getTilemap().get(
                        (int) pos.x / Vars.TileSize,
                        (int) pos.y / Vars.TileSize
                )));
            } else if (Cursor.content instanceof Floor) {
                Vector2 pos = Cursor.unProject(Draw.camera);

                if (worldEditorScreen.uiRenderer.stage.hit(Cursor.x, Cursor.y,true) == null)
                    world.getTilemap().get(
                            (int) pos.x / Vars.TileSize,
                            (int) pos.y / Vars.TileSize
                    ).floor = (Floor) Cursor.content;
            }
        }

        cameraSpeed = 1.5f;

        if (Vars.desktop) {
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
        } else if (Vars.mobile) {
            Vector2 normalizedCursorPos = joystick.getCurPosition().nor();

            Draw.camera.position.add(
                    normalizedCursorPos.x * cameraSpeed,
                    normalizedCursorPos.y * cameraSpeed,
                    0
            );
        }
    }

    public void initJoystick() {
        if (Vars.stage.getActors().get(0) instanceof Group) {
            joystick = ((Group) Vars.stage.getActors().get(0)).findActor("joystick");
        }
    }

    @Override
    public void dispose() {

    }
}
