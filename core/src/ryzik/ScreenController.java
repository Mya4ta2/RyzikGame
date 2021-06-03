package ryzik;

import ryzik.content.Events;
import ryzik.screen.GameScreen;
import ryzik.screen.MenuScreen;

public class ScreenController {
    private final MainActivity mainActivity;
    private final MenuScreen menu;

    public ScreenController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        menu = new MenuScreen();
    }

    public void setMenu() {
        mainActivity.setScreen(menu);
        Events.resize.fire();
    }

    public void startGame(GameScreen gameScreen) {
        mainActivity.setScreen(gameScreen);
    }
}
