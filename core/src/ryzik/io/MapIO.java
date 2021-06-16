package ryzik.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import ryzik.content.Blocks;
import ryzik.content.Teams;
import ryzik.type.world.Building;
import ryzik.type.world.Map;
import ryzik.type.world.Tile;
import ryzik.type.world.Tilemap;

import java.io.*;

public class MapIO {
    public static void save(Map map) {
        FileHandle mapFile = Gdx.files.local(map.getName() + ".rsav");
        if (!mapFile.exists()) mapFile.writeString(" ",  true);
        DataOutputStream dataOutputStream = new DataOutputStream(mapFile.write(false));

        Writes writes = new Writes(dataOutputStream);

        map.write(writes);

        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map read(String name) {
        FileHandle mapFile = Gdx.files.local(name + ".rsav");
        DataInputStream dataInputStream = new DataInputStream(mapFile.read());

        Reads reads = new Reads(dataInputStream);

        Map map = new Map(name);

        map.read(reads);

        map.createBuilding(map.getTilemap().get(0, 0), Blocks.eat, Teams.orange); //temp

        try {
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
