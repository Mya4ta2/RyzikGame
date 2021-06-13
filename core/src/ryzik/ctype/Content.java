package ryzik.ctype;

import ryzik.Vars;

public class Content {
    public short id;

    public Content() {
        id = (short) Vars.content.content.size;
        Vars.content.content.add(this);
    }
}
