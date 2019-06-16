package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class CharacterManager {

    private TextureAtlas ships;
    private TextureAtlas projectiles;
    public CharacterManager() {

    }

    public void setShips(TextureAtlas ships) {
        this.ships = ships;
    }

    public void setProjectiles(TextureAtlas projectiles) {
        this.projectiles = projectiles;
    }

    public void setTextures(TextureAtlas ships, TextureAtlas projectiles) {
        this.ships = ships;
        this.projectiles = projectiles;
    }


    public enum Lasers {
        GREEN,
        RED,
        BLUE,
        PURPLE
    }

    public TextureRegion getSprite(Spaceship.Ships ship){
        return ships.findRegion(ship.toString());
    }

    public TextureRegion getLaser(Lasers laser){
        return projectiles.findRegion(laser.toString());

    }

//    public TextureRegion getLaser(Lasers laser){
//        if(laser == Lasers.RED)
//            return projectiles.findRegion("")
//    }
}
