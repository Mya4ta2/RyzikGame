package ryzik;

import ryzik.content.*;

public class ContentLoader {
    public Events events;
    public Items items;
    public Blocks blocks;
    public Floors floors;
    public MobTypes mobTypes;
    public Teams teams;

    public ContentLoader() {
        items = new Items();
        events = new Events();
        floors = new Floors();
        blocks = new Blocks();
        mobTypes = new MobTypes();
        teams = new Teams();
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
