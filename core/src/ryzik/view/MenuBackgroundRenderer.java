package ryzik.view;

import com.badlogic.gdx.math.Vector2;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.ai.BackgroundEnemyAI;
import ryzik.content.MobTypes;
import ryzik.content.Teams;
import ryzik.ctype.Renderer;
import ryzik.type.world.World;
import ryzik.type.world.mob.Mob;

import java.util.Random;

public class MenuBackgroundRenderer implements Renderer {
    private WorldRenderer worldRenderer;
    private World world;

    @Override
    public void init() {
        world = new World(Vars.mapsLoader.maps.get(0).createWorld().getTilemap());
        worldRenderer = new WorldRenderer(world);
        worldRenderer.init();

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            //oh yes, magical numbers)
            spawnMob(new Vector2(-random.nextInt(30), -20), (float) (Math.random() * 5));
        }
    }

    @Override
    public void render(float delta) {
        worldRenderer.render(delta);
        world.updateEntitys(delta);
    }

    public void spawnMob(Vector2 offset, float speed) {
        Mob mob = new Mob(MobTypes.cockroach);
        BackgroundEnemyAI backgroundEnemyAI = new BackgroundEnemyAI(mob);
        backgroundEnemyAI.getStartPoint().set(Draw.camera.position.x / Vars.TileSize + offset.x,
                Draw.camera.position.y / Vars.TileSize + offset.y);
        backgroundEnemyAI.getEndPoint().set(Draw.camera.position.x / Vars.TileSize + 20,
                Draw.camera.position.y / Vars.TileSize + 20);
        backgroundEnemyAI.setSpeed(speed);
        backgroundEnemyAI.respawn();
        mob.controller = backgroundEnemyAI;
        world.addMob(mob);
    }

    @Override
    public void resize(int width, int height) {

    }
}
