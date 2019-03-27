package com.algodal.game.rockpaperscissors.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.algodal.game.rockpaperscissors.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 360;
		config.height = config.width * 16 / 9;
		config.x = 240;
		config.y = 0;
		System.out.println("Window Size : " + config.width + ", " + config.height);
		System.out.println("Window Position : " + config.x + ", " + config.y);
		new LwjglApplication(new Game(), config);
	}
}
