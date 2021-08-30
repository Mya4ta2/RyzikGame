package ryzik;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ryzik.content.Events;
import ryzik.editor.WorldEditorScreen;
import ryzik.screen.LoadingScreen;
import ryzik.screen.MenuScreen;
import ryzik.type.Event;
import ryzik.ui.UI;
import ryzik.view.DrawLayers;

public class MainActivity extends Game {
    @Override
    public void create() {
        Vars.atlas = new Atlas();
        Vars.atlas.errorTexture = new Texture("errorTexture.png");

        Vars.content = new ContentLoader();
        Vars.content.load();

        Draw.batch = new SpriteBatch();
        Draw.onDraw = new Event();
        Draw.camera = new OrthographicCamera();
        Draw.viewport = new ScreenViewport(Draw.camera);
        Draw.onDraw.on(new Runnable() {
            @Override
            public void run() {
                Draw.batch.setProjectionMatrix(Draw.camera.combined);
                Draw.viewport.apply();
                Draw.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        });
        Draw.drawLayers = new DrawLayers();

        Vars.ui = new UI();
        Vars.ui.load();

        Vars.skin = new Skin(Gdx.files.internal("skin/metal-ui.json"));
        Vars.font = Vars.skin.getFont("font");

        Vars.mapsLoader = new MapsLoader();
        Vars.mapsLoader.load();

        Vars.screenController = new ScreenController(this);
        setScreen(new LoadingScreen());

        Vars.applicationType = Gdx.app.getType();
        Vars.mobile = Vars.applicationType == Application.ApplicationType.Android;
        Vars.desktop = Vars.applicationType == Application.ApplicationType.Desktop;
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Cursor.update();

        super.render();
    }

    @Override
    public void resize(int width, int height) {
        Events.resize.fire();
        super.resize(width, height);
    }
}
