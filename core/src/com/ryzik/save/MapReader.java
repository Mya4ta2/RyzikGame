package com.ryzik.save;

import com.badlogic.gdx.files.FileHandle;
import com.ryzik.content.Blocks;
import com.ryzik.content.Floors;
import com.ryzik.type.Floor;
import com.ryzik.type.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapReader {

    public static World getWorldFromFile(FileHandle mapFileHandle) throws Exception {
        if (mapFileHandle.exists()) {
            World world;

            String mapFileStr = mapFileHandle.readString();
            String[] strings = mapFileStr.split(";");
            String str1, str2;
            int[][] blockPositions;

            Pattern blockPositionPattern = Pattern.compile("\\((\\[\\d+,\\d+],)+\\)", Pattern.CASE_INSENSITIVE);
            Pattern contentTypePattern = Pattern.compile("(\\D+)", Pattern.CASE_INSENSITIVE);
            Pattern worldSizePattern = Pattern.compile("width=(\\d+) height=(\\d+)", Pattern.CASE_INSENSITIVE);

            //find world size on first line
            Matcher worldSizeMatcher = worldSizePattern.matcher(strings[0]);

            if (worldSizeMatcher.find()) {
                world = new World(Integer.parseInt(worldSizeMatcher.group(1)), Integer.parseInt(worldSizeMatcher.group(2)));
            } else {
                throw new Exception("MapReader can't find world size on first line");
            }
            //

            for (String str : strings) {
                Matcher blockPositionMatcher = blockPositionPattern.matcher(str);
                Matcher contentTypeMatcher = contentTypePattern.matcher(str);

                while (blockPositionMatcher.find()) {
                    blockPositions = parseArray(
                            blockPositionMatcher.group(0)
                                    .replace("("," ")
                                    .replace(")"," ")
                                    .substring(0, blockPositionMatcher.group(0).length()-2)
                                    .trim()
                    );

                    if (contentTypeMatcher.find()) {
                        if (contentTypeMatcher.group(0).contains("Blocks")) {
                            for (int i = 0; i < blockPositions.length; i++) {
                                world.getTiles().get(
                                        blockPositions[i][0],
                                        blockPositions[i][1]
                                ).setBlock(Blocks.sandBrick);
                            }
                        } else if (contentTypeMatcher.group(0).contains("Floors")) {
                            for (int i = 0; i < blockPositions.length; i++) {
                                world.getTiles().get(
                                        blockPositions[i][0],
                                        blockPositions[i][1]
                                ).setFloor(Floors.sandBrick);
                            }
                        }
                    }
                }
            }

            return world;
        } else {
            throw new FileNotFoundException("MapReader can't find the map file");
        }
    }

    public static int[][] parseArray(String str) {
        str = str.replace(" ","");
        str = str.replace("[", "").replace("]", "");
        str = str.replace(",", " ");
        String[] num = str.split(" ");
        int[] numbers = new int[num.length];
        int[][] out = new int[num.length/2][2];

        for (int i = 0; i < num.length; ++i)
        {
            numbers[i] = Integer.parseInt(num[i]);
        }

        int j = 0;
        int k = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (j > 1) {
                j = 0;
                k += 2;
            }
            j++;
            out[i/2][0] = numbers[k];
            out[i/2][1] = numbers[k + 1];
        }

        return out;
    }
}
