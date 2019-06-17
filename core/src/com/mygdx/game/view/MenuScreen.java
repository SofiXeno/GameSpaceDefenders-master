package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGame;
import com.mygdx.game.model.Spaceship;

public class MenuScreen extends MyScreen {
    private MyGame.Difficulties chosenDiff;
    private Spaceship.Ships chosenPlayer;
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
