package ryzik.io;

import java.io.DataOutput;
import java.io.IOException;

public class Writes {
    DataOutput output;

    public Writes(DataOutput output) {
        this.output = output;
    }

    public void s(short s) {
        try {
            output.writeShort(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void i(int i) {
        try {
            output.writeInt(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void f(float f) {
        try {
            output.writeFloat(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
