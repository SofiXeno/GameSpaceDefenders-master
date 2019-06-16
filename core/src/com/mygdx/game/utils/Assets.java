package com.mygdx.game.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.awt.*;

public class Assets {

    private AssetManager manager;

    public Assets() {
        manager = new AssetManager();
        manager.load("mainAtlas.atlas", TextureAtlas.class);
        manager.load("projectiles.atlas", TextureAtlas.class);
        manager.load("AllShips.atlas", TextureAtlas.class);
        manager.load("Lights.atlas", TextureAtlas.class);;
        manager.finishLoading();
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose() {
        manager.dispose();
    }
}
