package com.ryzik.content;

import com.ryzik.ctype.ContentList;
import com.ryzik.type.Block;
import com.ryzik.type.Floor;

import java.util.ArrayList;
import java.util.List;

public class Blocks implements ContentList {
    public static Block air, test;

    public static List<Block> blocks = new ArrayList<>();

    @Override
    public void load() {
        air = new Block("") {
            {

            }
        };

        test = new Block("air") {
            {

            }
        };
    }
}
