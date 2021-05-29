package ryzik.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import ryzik.Cursor;
import ryzik.Vars;
import ryzik.content.Items;
import ryzik.type.world.item.ItemStack;

public class ItemSlotActor extends Actor {
    public ItemStack itemStack;
    public Texture slotTexture;
    public Texture activeSlotTexture;
    public boolean active;

    public ItemSlotActor(Texture slotTexture, Texture activeSlotTexture) {
        this.slotTexture = slotTexture;
        this.activeSlotTexture = activeSlotTexture;

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Cursor.selectedItem != null) {
                    itemStack = Cursor.selectedItem;
                    Cursor.selectedItem = null;
                } else {
                    if (itemStack != null && !itemStack.empty()) {
                        Cursor.selectedItem = itemStack;
                        itemStack = new ItemStack(Items.air,0);
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                active ? activeSlotTexture : slotTexture,
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );

        if (itemStack != null && !itemStack.empty()) {
            batch.draw(
                    itemStack.getItemType().texture,
                    getX() + 4,
                    getY() + 4,
                    getWidth() - 8,
                    getHeight() - 8
            );

            Vars.font.draw(
                    batch,
                    String.valueOf(itemStack.getAmount()),
                    getX() + 16 + itemStack.getItemType().texture.getWidth(),
                    getY() + 24
            );
        }

//        if (active) {
//            setSize(84, 84);
//        }

        super.draw(batch, parentAlpha);
    }
}
