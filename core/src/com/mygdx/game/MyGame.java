package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.utils.Assets;
import com.mygdx.game.view.GameScreen;
import com.mygdx.game.view.MenuScreen;
import com.mygdx.game.view.PauseScreen;

public class MyGame extends Game {
    public SpriteBatch getBatch() {
        return batch;
    }

    public enum Screens{
        PLAY,
        PAUSE,
        MENU
    }
    private SpriteBatch batch;
    private Screen gameScreen;
    private Assets assets;
    private Screen menuScreen;
    private Screen pauseScreen;
    public Skin fontSkin;
    @Override
    public void create() {
//        fontSkin = new Skin(Gdx.files.internal("skin.json"));
        assets = new Assets();
         batch = new SpriteBatch();
        gameScreen = new GameScreen( this);
        ((GameScreen) gameScreen).setShips(assets.getManager().get("mainAtlas.atlas", TextureAtlas.class));
        ((GameScreen) gameScreen).setProjs(assets.getManager().get("projectiles.atlas", TextureAtlas.class));
        ((GameScreen) gameScreen).initialize();
        menuScreen = new MenuScreen( this);
        pauseScreen = new PauseScreen( this);
        setScreen(gameScreen);
    }
    public void changeScreen(Screens value){
        switch(value ){
            case PLAY:
                setScreen(gameScreen);
                break;
            case MENU:
                setScreen(menuScreen);
                break;
            case PAUSE:
                setScreen(pauseScreen);
        }
    }
    @Override
    public void dispose() {
        super.dispose();
        gameScreen.dispose();
        assets.dispose();
    }
}
