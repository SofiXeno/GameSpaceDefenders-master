package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;

import javax.swing.*;

public class PauseScreen extends MyScreen{
    private Label pauseLabel;
    public PauseScreen( MyGame game){
        super(game);
    }
    @Override
    public void show() {
        pauseLabel = new Label("Game is paused.", game.fontSkin.get("default", Label.LabelStyle.class));
        pauseLabel.setAlignment(Align.center);
        pauseLabel.scaleBy(0.3f);
        pauseLabel.setPosition(viewportWidth/2+pauseLabel.getWidth()/2, viewportHeight/2+pauseLabel.getWidth()/2);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {
        clearScreen();

        if (Gdx.input.isKeyJustPressed(Input.Keys.P))
            game.changeScreen(MyGame.Screens.PLAY);
       game.getBatch().begin();
        pauseLabel.draw(game.getBatch(), 0f);
        game.getBatch().end();
    }


    @Override
    public void dispose() {

    }
}
