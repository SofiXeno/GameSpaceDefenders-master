package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class UI {
    private Stage gameStage;
    private Skin skin;
    private Label health, score;

    public UI() {
        gameStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin.json"));
        health = new Label("Health: 100/100", skin.get("default", Label.LabelStyle.class));
        score = new Label("Score: 0", skin.get("default", Label.LabelStyle.class));
        health.setFontScale(0.5f);
        health.setPosition(0, 0);
        score.setFontScale(0.5f);
        score.setPosition(Gdx.graphics.getWidth()-score.getWidth()-1, 0);
        score.setAlignment(Align.right);
        gameStage.addActor(score);
        gameStage.addActor(health);

    }

    public void updateScore(int score){
        this.score.setText("Score: "+score);
    }

    public void updateHealth(int health) {
        this.health.setText("Health: " + health + "/100");
    }

    public void draw() {
        gameStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        gameStage.act();
        gameStage.draw();
    }

    public void dispose() {
        gameStage.dispose();
    }
}
