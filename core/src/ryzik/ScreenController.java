package ryzik;

import ryzik.content.Events;
import ryzik.editor.WorldEditorScreen;
import ryzik.screen.GameScreen;
import ryzik.screen.LoadingScreen;
import ryzik.screen.MenuScreen;

public class ScreenController {
    private final MainActivity mainActivity;
    private final MenuScreen menu;

    public ScreenController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        menu = new MenuScreen();
    }

    public void setMenu() {
        if (mainActivity.getScreen() instanceof GameScreen)
            mainActivity.getScreen().dispose();
        mainActivity.setScreen(menu);
        Events.resize.fire();
    }

    public void startGame(GameScreen gameScreen) {
        mainActivity.setScreen(gameScreen);
    }

    public void openEditor(WorldEditorScreen editorScreen) {
        mainActivity.setScreen(editorScreen);
    }
}
