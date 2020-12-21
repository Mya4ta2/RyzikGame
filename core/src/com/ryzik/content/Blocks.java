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
                // lol, air not have health =(
            }
        };

        stone = new Block("stone") {
            {
                setHealth(100);
            }
        };

        grass = new Block("grass") {
            {
                setHealth(100);
            }
        };

        sand = new Block("sand") {
            {
                setHealth(100);
            }
        };

        woodPlank = new Block("woodPlank") {
            {
                setHealth(100);
            }
        };

        sandBrick = new Block("sandBrick") {
            {
                setHealth(100);
            }
        };

        brick = new Block("brick") {
            {
                setHealth(100);
            }
        };
    }
}
