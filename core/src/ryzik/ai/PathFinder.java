package ryzik.ai;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import ryzik.content.Blocks;
import ryzik.type.world.Tile;
import ryzik.type.world.Tilemap;

import java.util.ArrayList;

public class PathFinder {
    private final Tilemap tilemap;
    private Tile goalTile;

    public PathFinder(Tilemap tilemap) {
        this.tilemap = tilemap;
    }

    public ArrayList<Tile> findPath(Tile start, Tile goal) {
        goalTile = goal;
        ArrayList<Tile> reachable = new ArrayList<>();
        ArrayList<Tile> explored = new ArrayList<>();

        reachable.add(start);

        while (reachable.size() != 0) {
            Tile tile = reachable.get(0);

            if (tile == goal) {
                ArrayList<Tile> path = new ArrayList<Tile>();

                while (tile != null) {
                    path.add(tile);
                    tile = tile.previous;
                }

                tilemap.clearTiles();
                return path;
            }

            reachable.remove(tile);
            explored.add(tile);
            ArrayList<Tile> newReachable = new ArrayList<>(getTiles(tile));
            newReachable.removeAll(explored);

            for (Tile adjacent : newReachable) {
                if (!reachable.contains(adjacent)) {
                    adjacent.previous = tile;
                    reachable.add(adjacent);
                }
            }
        }

        return null;
    }

    public ArrayList<Tile> getTiles(Tile tile) {
        ArrayList<Tile> tiles = new ArrayList<>();

        Tile nearestTile;

        nearestTile = getTile(tile,1,0);
        if (nearestTile != null) tiles.add(nearestTile);

        nearestTile = getTile(tile,-1,0);
        if (nearestTile != null) tiles.add(nearestTile);

        nearestTile = getTile(tile,0,1);
        if (nearestTile != null) tiles.add(nearestTile);

        nearestTile = getTile(tile,0,-1);
        if (nearestTile != null) tiles.add(nearestTile);

        return tiles;
    }

    public Tile getTile(Tile tile, int x, int y) {
        if (tilemap.inBounds(new Vector2(tile.x + x, tile.y + y))) {
            Tile tileOut = tilemap.get((int) tile.x + x, (int) tile.y + y);
            if (tileOut.equals(goalTile)) return tileOut;
            if (tileOut.block == Blocks.air && tileOut.building == null) {
                return tileOut;
            }
        }

        return null;
    }
}

