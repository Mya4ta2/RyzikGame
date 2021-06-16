package ryzik.ui;

import ryzik.ui.fragment.*;

public class UI {
    public Fragment gameFragment, eatDefenseGameFragment, respawnFragment, menuFragment, worldEditorFragment;

    public void load() {
        gameFragment = new GameFragment();
        respawnFragment = new RespawnFragment();
        eatDefenseGameFragment = new EatDefenseGameFragment();
        menuFragment = new MenuFragment();
        worldEditorFragment = new WorldEditorFragment();
    }
}
