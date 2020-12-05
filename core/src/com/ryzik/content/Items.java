package com.ryzik.content;

import com.ryzik.ctype.ContentList;
import com.ryzik.type.Item;

public class Items implements ContentList {

    public static Item air, test;

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
    }
}
