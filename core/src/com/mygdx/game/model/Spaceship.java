package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

import java.awt.*;
import java.util.Collection;

public class Spaceship extends GameObject {
    public enum Ships{
        GREEN_PLAYER,
        RED_PLAYER,
        BLUE_PLAYER,
        ENEMY1,
        ENEMY2,
        ENEMY3,
        ENEMY4



    }
    private int health;
    private Weapon weapon;
    protected boolean player;
    public Spaceship(TextureRegion texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
        health=100;
    }

    public int processDamage(int dmg){
        if(health>dmg) {
            health-=dmg;
            return 1;
        }
        else return 0;
    }

    public boolean isPlayer() {
        return player;
    }

    public int getHealth() {
        return health;
    }
    public void setWeapon(Weapon w){
        weapon = w;
    }
    public Weapon getWeapon() {
        return weapon;
    }


    public Polygon getHitbox() {
        return bounds;
    }
}
