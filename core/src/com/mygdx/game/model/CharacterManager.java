package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class CharacterManager {
    public enum Ships{

    }
    private TextureAtlas ships;
    private TextureAtlas projectiles;
    public CharacterManager(){

    }
    public void setTextures(TextureAtlas ships, TextureAtlas projectiles){
        this.ships=ships;
        this.projectiles = projectiles;
    }
}
