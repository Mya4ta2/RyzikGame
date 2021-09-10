package ryzik;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import ryzik.content.Events;
import ryzik.editor.WorldEditorScreen;
import ryzik.screen.GameScreen;
import ryzik.screen.LoadingScreen;
import ryzik.screen.MenuScreen;

public class ScreenController {
    private final Game mainActivity;
    private final MenuScreen menu;

    public ScreenController(Game mainActivity) {
        this.mainActivity = mainActivity;
        menu = new MenuScreen();
    }

    public void setMenu() {
        if (mainActivity.getScreen() instanceof GameScreen)
            mainActivity.getScreen().dispose();
        setScreen(menu);
        Events.resize.fire();
    }

    public void setScreen(Screen screen) {
        Gdx.input.setCatchKey(Input.Keys.BACK, false);

        mainActivity.setScreen(screen);
    }

    public void startGame(GameScreen gameScreen) {
        setScreen(gameScreen);

        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    public void openEditor(WorldEditorScreen editorScreen) {
        setScreen(editorScreen);

        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }
}
