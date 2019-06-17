package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.MyGame;

public abstract class MyScreen implements Screen {
    public static float viewportWidth;
    public static float viewportHeight;
    public static float halfHeight;
    public static float halfWidth;
    public static float deltaCff;
    // protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected MyGame game;

    public MyScreen(MyGame game) {

        this.game = game;
    }

    protected void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        viewportWidth = 40f;
        viewportHeight = 40f * aspectRatio;
        halfHeight = viewportHeight / 2;
        halfWidth = viewportWidth / 2;
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

}
