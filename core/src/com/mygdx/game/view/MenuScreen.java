package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGame;

public class MenuScreen extends MyScreen {

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        clearScreen();
    }

    @Override
    public void dispose() {

    }
}
