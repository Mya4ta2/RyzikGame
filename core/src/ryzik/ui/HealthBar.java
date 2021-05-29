package ryzik.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ryzik.type.world.mob.Player;

public class HealthBar extends ProgressBar {
    private final Player player;

    public HealthBar(float stepSize, boolean vertical, Skin skin, final Player player) {
        super(0, player.type.getHealth(), stepSize, vertical, skin);
        this.player = player;
        setValue(player.health);

        player.onHealthChange.on(new Runnable() {
            @Override
            public void run() {
                setValue(player.health);
            }
        });
    }
}
