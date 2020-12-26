package com.ryzik.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ryzik.MainActivity;

import java.util.Arrays;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MainActivity(), config) {
			{
				config.title = "Ryzik";
				config.addIcon("icon.png", Files.FileType.Internal);
				config.height = 480;
				config.width = 640;
			}
		};
	}
}
