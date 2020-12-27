package com.ryzik.content;

import com.ryzik.ctype.ContentList;
import com.ryzik.type.Item;

import java.util.ArrayList;
import java.util.List;

public class Items implements ContentList {

    public static Item air, stick, stone, sand, grass, woodPlank, sandBrick, brick;

    public static List<Item> items = new ArrayList<>();

    @Override
    public void load() {
        air = new Item("air") {
            {

            }
        };

        stick = new Item("stick") {
            {

            }
        };

        stone = new Item("stone") {
            {

            }
        };

        sand = new Item("sand") {
            {

            }
        };

        grass = new Item("grass") {
            {

            }
        };

        woodPlank = new Item("woodPlank") {
            {

            }
        };

        sandBrick = new Item("sandBrick") {
            {

            }
        };

        brick = new Item("brick") {
            {

            }
        };
    }
}
