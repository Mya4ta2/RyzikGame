package ryzik.ai;

import com.badlogic.gdx.math.Vector2;
import ryzik.Vars;
import ryzik.content.MobTypes;
import ryzik.type.world.mob.Mob;
import ryzik.type.MobController;
import ryzik.type.world.Tile;
import ryzik.type.world.Tilemap;

import java.util.ArrayList;

public class EnemyController extends MobController {
    private final Tilemap tilemap;
    private final PathFinder pathFinder;
    private Mob target;
    private Runnable onTargetMove;

    @Override
    public void init() {
        onTargetMove.run();
        target.onMove.on(onTargetMove);
    }

    public EnemyController(Mob mob) {
        super(mob);

        tilemap = Vars.world.getTilemap();
        pathFinder = new PathFinder(tilemap);

        target = Vars.player;
        onTargetMove = new Runnable() {
            @Override
            public void run() {
                updatePath();
            }
        };

        init();
    }

    @Override
    public void update() {
        if (path != null)
            updateWalkPath();
    }

    @Override
    public void dispose() {
        target.onMove.remove(onTargetMove);
    }

    public ArrayList<Tile> path;
    public Tile currentTile;
    public float currentTileIndex;

    public void updatePath() {
        path = pathFinder.findPath(
                tilemap.get((int) mob.position.x, (int) mob.position.y),
                tilemap.get((int) target.position.x, (int) target.position.y)
        );

        if (path == null) return;

        clearWalkPath();
    }

    public void updateWalkPath() {
        moveMobToPoint(new Vector2(currentTile.x, currentTile.y));

        if (currentTileIndex >= 0) {
            currentTile = path.get((int) currentTileIndex);
            currentTileIndex -= 0.1f;
        }
    }

    public void clearWalkPath() {
        currentTile = path.get(path.size()-1);
        currentTileIndex = path.size()-1;
    }

    private void moveMobToPoint(Vector2 position) {
        float xDifference = (position.x - mob.position.x) / 15;
        float yDifference = (position.y - mob.position.y) / 15;

        mob.position.add(xDifference, yDifference);
    }
}
