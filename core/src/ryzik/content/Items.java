package ryzik.content;

import ryzik.Vars;
import ryzik.ctype.ContentList;
import ryzik.type.world.item.Item;
import ryzik.type.world.item.weapon.RangedType;
import ryzik.type.world.item.weapon.SwordType;

public class Items implements ContentList {
    public static Item air, stick, suicide, sword, gun, dyurandal;

    @Override
    public void load() {
        air = new Item("air");
        suicide = new Item("snus") {
            {
                consumable = true;
            }

            @Override
            public void rightMouseUse() {
                Vars.player.dead();
                super.rightMouseUse();
            }
        };

        stick = new SwordType("stick"){
            {
                reloadTime = 0.5f;
                damage = 5;
                discarding = 5;
            }
        };

        sword = new SwordType("sword") {
            {
                reloadTime = 0.5f;
                damage = 25;
                discarding = 5;
            }
        };

        dyurandal = new SwordType("dyurandal") {
            {
                reloadTime = 0.5f;
                damage = 25;
                discarding = 5;
                height = 2;
            }
        };

        gun = new RangedType("gun") {
            {
                reloadTime = 0.5f;
                damage = 25;
                discarding = 5;
            }
        };
    }
}
