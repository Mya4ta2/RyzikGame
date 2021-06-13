package ryzik;

import com.badlogic.gdx.utils.Array;
import ryzik.content.*;
import ryzik.ctype.Content;

public class ContentLoader {
    public Events events;
    public Items items;
    public Blocks blocks;
    public Floors floors;
    public MobTypes mobTypes;
    public Teams teams;

    public Array<Content> content;

    public ContentLoader() {
        items = new Items();
        events = new Events();
        floors = new Floors();
        blocks = new Blocks();
        mobTypes = new MobTypes();
        teams = new Teams();

        content = new Array<>();
    }

    public void load() {
        items.load();
        events.load();
        floors.load();
        blocks.load();
        mobTypes.load();
        teams.load();
    }
}
