package ryzik.type;

import java.util.ArrayList;

public class Event {
    public final ArrayList<Runnable> tasks = new ArrayList<>();

    public void fire() {
        for (Runnable runnable : tasks) {
            runnable.run();
        }
    }

    public void on(Runnable runnable) {
        tasks.add(runnable);
    }

    public void remove(Runnable runnable) {
        tasks.remove(runnable);
    }
}
