package ryzik.type;

import ryzik.type.world.bounds.Bounds;

import java.util.ArrayList;

public class OverlapsEvent {
    public final ArrayList<OverlapsTask> tasks = new ArrayList<>();

    public void fire(Bounds bounds) {
        for (OverlapsTask overlapsTask : tasks) {
            overlapsTask.run(bounds);
        }
    }

    public void on(OverlapsTask overlapsTask) {
        tasks.add(overlapsTask);
    }
}
