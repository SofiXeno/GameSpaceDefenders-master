package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.title = "Space Defenders by Romanenko Mykhailo & Sofiia Xenofontova";
		config.width = 1200;
		config.height = 950;
		MyGame mg = new MyGame();
		new LwjglApplication(mg, config);
	}}


