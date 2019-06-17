package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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

    public TextureRegion getSprite(Spaceship.Ships ship) {
        return ships.findRegion(ship.toString());
    }

    public TextureRegion getLaser(Lasers laser) {
        return projectiles.findRegion(laser.toString());

    }

    public enum Lasers {
        GREEN,
        RED,
        BLUE,
        PURPLE
    }

}
