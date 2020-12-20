package com.ryzik.content;

import com.ryzik.ctype.ContentList;
import com.ryzik.type.Block;
import com.ryzik.type.Floor;

import java.util.ArrayList;
import java.util.List;

public class Blocks implements ContentList {
    public static Block air, stone, sand, grass, woodPlank, sandBrick, brick;

    public static List<Block> blocks = new ArrayList<>();

    @Override
    public void load() {
        air = new Block("air") {
            {

            }
        };

        stone = new Block("stone") {
            {

            }
        };

        grass = new Block("grass") {
            {

            }
        };

        sand = new Block("sand") {
            {

            }
        };

        woodPlank = new Block("woodPlank") {
            {

            }
        };

        sandBrick = new Block("sandBrick") {
            {

            }
        };

        brick = new Block("brick") {
            {

            }
        };
    }
}
