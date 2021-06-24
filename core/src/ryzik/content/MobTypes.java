package ryzik.content;

import ryzik.ai.EnemyController;
import ryzik.ctype.ContentList;
import ryzik.type.world.mob.MobType;

public class MobTypes implements ContentList {
    public static MobType test, ryzik, cockroach, bloodCockroach, tarakeka;

    @Override
    public void load() {
        test = new MobType("test") {
            {
                setWidth(1);
                setHeight(1);
            }
        };

        ryzik = new MobType("ryzik") {
            {
                setWidth(0.9f);
                setHeight(0.9f);
                setHealth(250);
                setSpeed(2);
            }
        };

        cockroach = new MobType("cockroach") {
            {
                setWidth(0.5f);
                setHeight(1);
                setHealth(70);
                setEnemy(true);
                setSpeed(1);
            }
        };

        bloodCockroach = new MobType("bloodCockroach") {
            {
                setWidth(0.75f);
                setHeight(1.5f);
                setHealth(130);
                setEnemy(true);
                setSpeed(1);
            }
        };

        tarakeka = new MobType("tarakeka") {
            {
                setWidth(2f);
                setHeight(3f);
                setHealth(350);
                setEnemy(true);
                setSpeed(1);
            }
        };
    }
}
