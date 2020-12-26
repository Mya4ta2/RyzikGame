package com.ryzik.content;

import com.ryzik.ctype.ContentList;
import com.ryzik.type.Item;

import java.util.ArrayList;
import java.util.List;

public class Items implements ContentList {

    public static Item air, test, stick;

    public static List<Item> items = new ArrayList<>();

    @Override
    public void load() {
        air = new Item("air") {
            {

            }
        };

        test = new Item("test") {
            {

            }
        };

        stick = new Item("stick") {
            {

            }
        };
    }
}
