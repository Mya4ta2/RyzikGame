package ryzik.type.item;

import com.badlogic.gdx.math.Vector2;
import ryzik.Draw;
import ryzik.Vars;
import ryzik.content.Items;
import ryzik.type.Entity;
import ryzik.type.world.item.ItemStack;
import ryzik.type.world.mob.Mob;

public class DroppedItem extends Mob implements Entity {
    public ItemStack itemStack;

    public DroppedItem(ItemStack itemStack, Vector2 position) {
        this.itemStack = itemStack;
        this.position = position;
    }

    @Override
    public void draw() {
        if (itemStack.getItemType() != Items.air)
            Draw.batch.draw(itemStack.getItemType().texture, position.x * Vars.TileSize, position.y * Vars.TileSize);
    }
}
