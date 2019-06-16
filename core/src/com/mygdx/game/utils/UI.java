package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class UI {
    private Stage gameStage;
    private Stage menuStage;
    private Skin skin;
    private Label label;

    public UI() {
       // stage = new Stage(new FitViewport(800, 600));
        //skin = new Skin(Gdx.files.internal("skin.json"));
    }

    public void draw(){
//        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        stage.act();
//        stage.draw();
    }

    public void dispose() {
        //stage.dispose();
    }
}
