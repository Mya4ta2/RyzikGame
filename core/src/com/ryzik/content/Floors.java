package com.ryzik.content;

import com.ryzik.ctype.ContentList;
import com.ryzik.type.Block;
import com.ryzik.type.Floor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Floors implements ContentList {
    public static Floor air, grass, stone, brick, woodenPlank, sandBrick;

    public static List<Floor> floors = new ArrayList<>();

    @Override
    public void load() {
        air = new Floor() {
            {

            }
        };

        grass = new Floor() {
            {

            }
        };

        stone = new Floor() {
            {

            }
        };

        brick = new Floor() {
            {

            }
        };

        woodenPlank = new Floor() {
            {

            }
        };

        sandBrick = new Floor() {
            {

            }
        };
    }
}
