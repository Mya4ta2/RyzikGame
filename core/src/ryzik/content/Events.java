package ryzik.content;

import ryzik.ctype.ContentList;
import ryzik.type.Event;

//TODO евенты не игровой контент, нужно куданибуть их выэтовать
public class Events implements ContentList {
    public static Event resize, update, keyE, keyQ;

    @Override
    public void load() {
        resize = new Event();
        update = new Event();
        keyE = new Event();
        keyQ = new Event();
    }
}
