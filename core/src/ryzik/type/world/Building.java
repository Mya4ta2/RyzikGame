package ryzik.type.world;

import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Blocks;
import ryzik.content.Events;
import ryzik.type.Entity;
import ryzik.type.Event;
import ryzik.type.world.block.Block;
import ryzik.type.world.bounds.BuildingBounds;
import ryzik.type.world.bounds.MobBounds;
import ryzik.type.world.mob.Mob;

//TODO Building не моб =(
public class Building implements Entity {
    public Block type;
    public int x, y;
    public float health;
    public Team team;
    public BuildingBounds bounds;

    public Event onHealthChange = new Event();

    public Building(Block type) {
        this.type = type;
        bounds = new BuildingBounds(type.width, type.height, this);
        health = type.health;
    }

    public void draw() {
        if (type != Blocks.air)
            Draw.batch.draw(type.texture, x * Vars.TileSize, y * Vars.TileSize);
    }

    @Override
    public void update(float delta) {
        bounds.setPosition(x, y);
    }

    public void applyDamage(float damage) {
        health -= damage;

        if (health < 0) destroy();

        onHealthChange.fire();
    }

    public void destroy() {

    }
}
