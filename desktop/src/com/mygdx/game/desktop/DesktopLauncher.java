package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;

import java.awt.*;

public class DesktopLauncher {

    public static void main(String[] arg) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.foregroundFPS = 60;
        config.backgroundFPS = 60;
        config.title = "Space Defenders by Romanenko Mykhailo & Sofiia Xenofontova";
        config.width = (int)screenSize.getWidth()/2;
        config.height =(int) screenSize.getHeight()-150;
        config.resizable = false;
        MyGame mg = new MyGame();
        new LwjglApplication(mg, config);
    }
}
