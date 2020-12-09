package com.ryzik.content;

import com.ryzik.ctype.ContentList;
import com.ryzik.type.Weapon;

public class Weapons implements ContentList {

    public static Weapon air, test;

    @Override
    public void load() {
        air = new Weapon("air") {
            {

            }
        };

        test = new Weapon("test") {
            {

            }
        };
    }
}
