package ryzik.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import ryzik.type.world.Map;
import ryzik.type.world.Tile;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class MapIO {
    public static void save(Map map) {
        FileHandle mapFile = Gdx.files.local(map.getName() + ".rsav");
        if (!mapFile.exists()) mapFile.writeString(" ",  true);
        DataOutputStream dataOutputStream = new DataOutputStream(mapFile.write(false));

        Writes writes = new Writes(dataOutputStream);

        for (Tile tile : map.getTilemap().getArray()) {
            tile.write(writes);
        }

        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
