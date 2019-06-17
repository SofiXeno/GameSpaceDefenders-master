package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.model.CharacterManager;
import com.mygdx.game.utils.Assets;
import com.mygdx.game.view.GameScreen;
import com.mygdx.game.view.MenuScreen;
import com.mygdx.game.view.PauseScreen;

public class MyGame extends Game {
    public Skin fontSkin;
    public CharacterManager m;
    private SpriteBatch batch;
    private Screen gameScreen;
    private Assets assets;
    private Screen menuScreen;
    private Screen pauseScreen;

    public SpriteBatch getBatch() {
        return batch;
    }
    public enum Difficulties{
        VERY_EASY,
        EASY,
        MEDIUM,
        HARD,
        VERY_HARD
    }
    @Override
    public void create() {
        fontSkin = new Skin(Gdx.files.internal("skin.json"));
        assets = new Assets();
        batch = new SpriteBatch();
        gameScreen = new GameScreen(this, Difficulties.VERY_HARD);
        m = new CharacterManager();
        m.setShips(assets.getManager().get("AllShips.atlas", TextureAtlas.class));
        m.setProjectiles(assets.getManager().get("Lights.atlas", TextureAtlas.class));
        ((GameScreen) gameScreen).initialize();
        menuScreen = new MenuScreen(this);
        pauseScreen = new PauseScreen(this);
        setScreen(gameScreen);
    }

    public void changeScreen(Screens value) {
        switch (value) {
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

    public enum Screens {
        PLAY,
        PAUSE,
        MENU
    }
}
