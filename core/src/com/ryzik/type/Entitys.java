package com.ryzik.type;

import java.util.ArrayList;

public class Entitys {
    private final ArrayList<Entity> array = new ArrayList<>();

    public void updateAll(float delta) {
        for (Entity entity : array) {
            entity.update(delta);
        }
    }

    public ArrayList<Entity> getArray() {
        return array;
    }

    public void add(Entity entity) {
        array.add(entity);
    }
}
