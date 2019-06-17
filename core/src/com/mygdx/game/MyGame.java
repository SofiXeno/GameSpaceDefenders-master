package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.model.CharacterManager;
import com.mygdx.game.model.Spaceship;
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

    private TextureAtlas labels;

    public Assets getAssets() {
        return assets;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public TextureAtlas getProtagonists() {
        return assets.getManager().get("AllProtagonistShips.atlas", TextureAtlas.class);
    }

    public enum Difficulties{
        VERY_EASY,
        EASY,
        MEDIUM,
        HARD,
        VERY_HARD
    }

    public TextureAtlas getLabels() {
        return labels;
    }

    @Override
    public void create() {
        fontSkin = new Skin(Gdx.files.internal("skin.json"));
        assets = new Assets();
        batch = new SpriteBatch();
        gameScreen = new GameScreen(this, Difficulties.VERY_HARD, Spaceship.Ships.PLAYER_RED);
        m = new CharacterManager();
        m.setShips(assets.getManager().get("AllShips.atlas", TextureAtlas.class));
        m.setProjectiles(assets.getManager().get("Lights.atlas", TextureAtlas.class));
        labels = assets.getManager().get("AllLabels.atlas", TextureAtlas.class);
        ((GameScreen) gameScreen).initialize();
        menuScreen = new MenuScreen(this);
        pauseScreen = new PauseScreen(this);
        setScreen(menuScreen);
    }
    public void newMenu(){
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }
    public void newGame(Difficulties diff, Spaceship.Ships ship){
        gameScreen = new GameScreen(this, diff, ship);
        ((GameScreen) gameScreen).initialize();
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
