package ryzik.content;

import com.badlogic.gdx.graphics.Color;
import ryzik.ctype.ContentList;
import ryzik.type.world.Team;

public class Teams implements ContentList {
    public static Team red, orange, gray;

    @Override
    public void load() {
        red = new Team(Color.RED); // enemy`s team

        orange = new Team(Color.ORANGE); // player team

        gray = new Team(Color.GRAY); // default team, for map blocks
    }
}
