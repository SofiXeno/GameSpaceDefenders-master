package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;
//hhjhjh

public class DesktopLauncher {
	public static final int WIDTH = 800, HEIGHT = 900;

	public static void main (String[] arg) {

				LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.title = "Space Defenders by Romanenko Mykhailo & Sofiia Xenofontova";
		config.width = WIDTH;
		config.height = HEIGHT;
		config.resizable = false;
		MyGame mg = new MyGame();
		new LwjglApplication(mg, config);
	}
}
