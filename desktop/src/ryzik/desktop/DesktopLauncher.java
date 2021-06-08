package ryzik.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ryzik.MainActivity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MainActivity(), config) {
			{
				config.height = 800;
				config.width = 1280;
				config.title = "Ryzik";
				config.addIcon("icon.png", Files.FileType.Internal);
			}
		};
	}
}
