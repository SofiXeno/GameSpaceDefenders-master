package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.view.GameScreen;

public class Spaceship extends GameObject {
    protected Ships type;
    protected GameScreen screen;
    protected int damage;
    protected boolean player;
    protected int health;
    private Weapon weapon;

    public Spaceship(TextureRegion texture, float x, float y, float width, float height, GameScreen screen) {
        super(texture, x, y, width, height);
        health = 100;
        this.screen = screen;
    }

    public void setType(Ships type) {
        this.type = type;
    }

    public void shoot(int damage, Ships type) {
        int vector = 1;
        float y = bounds.getY();
        if (!player)
            vector *= -1;
        else
            y += getHeight();
        if (getWeapon().getType() == Weapon.SINGLE)
            screen.projectileFired(bounds.getX() + width / 2, y, damage, 20f * vector, player, type);
        else if (getWeapon().getType() == Weapon.DOUBLE) {
            screen.projectileFired(bounds.getX() + width / 4, y, damage, 20f * vector, player, type);
            screen.projectileFired(bounds.getX() + (width / 4) * 3, y, damage, 20f * vector, player, type);
        } else if (getWeapon().getType() == Weapon.MINIGUN)
            screen.projectileFired(bounds.getX() + width / 2, y, damage, 50f * vector, player, type);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int processDamage(int dmg) {
        if (health > dmg) {
            health -= dmg;
            return 1;
        } else return 0;
    }

    public boolean isPlayer() {
        return player;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon w) {
        weapon = w;
    }

    public Polygon getHitbox() {
        return bounds;
    }


    public enum Ships {
        PLAYER_GREEN,
        PLAYER_RED,
        PLAYER_BLUE,
        ENEMY1,
        ENEMY2,
        ENEMY3,
        ENEMY4


    }
}
