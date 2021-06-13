package ryzik.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Reads {
    DataInput input;

    public Reads(DataInput input) {
        this.input = input;
    }

    public short s() {
        try {
            return input.readShort();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public int i() {
        try {
            return input.readInt();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public float f() {
        try {
            return input.readFloat();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
