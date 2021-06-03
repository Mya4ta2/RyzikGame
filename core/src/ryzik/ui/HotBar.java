package ryzik.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.content.Items;
import ryzik.type.item.DroppedItem;
import ryzik.type.item.Inventory;
import ryzik.type.world.item.ItemStack;
import ryzik.type.world.mob.Weapon;

public class HotBar extends Actor {
    private final Inventory inventory;
    private ItemSlotActor[] hotBarSlots;
    private GlyphLayout glyphLayout;

    private Table table;
    private Table slotsTable;
    private int selectedSlotIndex;
    private ItemSlotActor selectedSlot;

    private float slotSize;

    public HotBar(Inventory inventory, float slotSize) {
        this.inventory = inventory;
        this.slotSize = slotSize;
        this.hotBarSlots = new ItemSlotActor[inventory.getWidth()];

        glyphLayout = new GlyphLayout();
        fill();

        addListener(new InputListener(){
            @Override
            public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
                if (amountY > 0) {
                    nextSlotSelect();
                } else {
                    previousSlotSelect();
                }

                return super.scrolled(event, x, y, amountX, amountY);
            }
        });

        Events.keyQ.on(new Runnable() {
            @Override
            public void run() {
                dropItemOnce();
            }
        });
    }

    public void fill() {
        table = new Table();
        slotsTable = new Table();

        setWidth(hotBarSlots.length * slotSize);
        setHeight(slotSize);
        table.add(this).row();

        for (int i = 0; i < hotBarSlots.length; i++) {
            hotBarSlots[i] = new ItemSlotActor(Vars.atlas.find("itemslot"), Vars.atlas.find("activeItemslot"));
            hotBarSlots[i].itemStack = inventory.getHotBar()[i];
            hotBarSlots[i].setSize(slotSize,slotSize);
            slotsTable.add(hotBarSlots[i]);
            slotsTable.add(new Separator(0, 6));

            if (Vars.mobile) {
                final int finalI = i;
                hotBarSlots[i].addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        setSelectedSlot(finalI);
                        return super.touchDown(event, x, y, pointer, button);
                    }
                });
            }
        }

        setSelectedSlot(1);

        table.add(slotsTable);
    }

    public void setSelectedSlot(int index) {
        if (selectedSlot != null) selectedSlot.active = false;
        selectedSlot = hotBarSlots[index];
        selectedSlotIndex = index;
        selectedSlot.active = true;
        if (selectedSlot.itemStack.isWeapon()) Vars.player.currentWeapon = (Weapon) selectedSlot.itemStack.getItemMob();
    }

    public void nextSlotSelect() {
        if (selectedSlotIndex + 1 < inventory.getWidth())
            setSelectedSlot(selectedSlotIndex + 1);
        else
            setSelectedSlot(0);
    }

    public void previousSlotSelect() {
        if (selectedSlotIndex - 1 >= 0)
            setSelectedSlot(selectedSlotIndex - 1);
        else
            setSelectedSlot(inventory.getWidth()-1);
    }

    public void dropItemOnce() {
        ItemStack itemStack = selectedSlot.itemStack;
        if (itemStack.empty()) return;

        itemStack.setAmount(itemStack.getAmount()-1);
        DroppedItem item = new DroppedItem(
                new ItemStack(itemStack.getItemType(),1),
                new Vector2(Vars.player.position.x,  Vars.player.position.y - 1)
        );

        if (itemStack.empty()) itemStack.set(Items.air, 0);

        Vars.world.addMob(item);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        String itemName = selectedSlot.itemStack != null && selectedSlot.itemStack.getItemType() != Items.air
                ? selectedSlot.itemStack.getItemType().name : "";

        glyphLayout.setText(Vars.font,itemName);

        Vars.font.draw(
                batch,
                itemName,
                getX() + (getWidth() / 2) - glyphLayout.width/2,
                getY() + (getHeight() / 2) + glyphLayout.height/2
        );

        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
      //  if (Cursor.selectedItem != null && Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT))
      //      Cursor.selectedItem.useItem();

        if (Vars.desktop) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && !selectedSlot.itemStack.empty()) {
                selectedSlot.itemStack.rightMouseUseItem();
                if (selectedSlot.itemStack.empty()) selectedSlot.itemStack.set(Items.air, 0);
            }

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedSlot.itemStack.isWeapon()) {
                Vars.player.currentWeapon.attack();
                if (selectedSlot.itemStack.empty()) selectedSlot.itemStack.set(Items.air, 0);
            }
        }

        //table.validate();

        super.act(delta);
    }

    public Table getTable() {
        return table;
    }
}
