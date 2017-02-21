package com.ajrod.dj2015.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ajrod.dj2015.Dj2015;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Dj2015.WIDTH;
		config.height = Dj2015.HEIGHT;
		config.title = Dj2015.TITLE;
		new LwjglApplication(new Dj2015(), config);
	}
}
