package ryzik.type.world;

import ryzik.Vars;
import ryzik.type.item.DroppedItem;
import ryzik.type.world.mob.Mob;

public class WorldController {
    private final World world;

    public WorldController(World world) {
        this.world = world;
    }

    public void init() {

    }

    public void update(float delta) {
        world.updateEntitys(delta);

        processMobCollision();
    }

    public void processMobCollision() {
        for (int i = 0; i < world.getMobs().getArray().size; i++) {
            Mob mob = world.getMobs().getArray().get(i);

            for (int a = 0; a < world.getMobs().getArray().size; a++) {
                Mob mob1 = world.getMobs().getArray().get(a);

                if (mob1.equals(mob)) continue;
                mob1.bounds.overlaps(mob.bounds);
            }

            if (mob.equals(Vars.player)) continue;
            if (Vars.player.bounds.overlaps(mob.bounds)) {
                if (mob instanceof DroppedItem) {
                    if (Vars.player.inventory.raiseItem(((DroppedItem) mob).itemStack))
                        mob.dead();
                }

                Vars.player.position.set(Vars.player.oldPosition);
            }

            if (Vars.player.currentWeapon != null)
                Vars.player.currentWeapon.bounds.overlaps(mob.bounds);
        }

        for (Tile tile : world.getTilemap().getArray()) {
            if (tile.building != null) {
                if (tile.building.bounds.overlaps(Vars.player.bounds)) {
                    Vars.player.position.set(Vars.player.oldPosition);
                }
            }
        }
    }
}
