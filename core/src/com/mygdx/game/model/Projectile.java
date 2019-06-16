package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.view.GameScreen;

public class Projectile extends GameObject {
    private int damage;
    private float speed;
    private boolean friendly;

    public Projectile(TextureRegion texture, float x, float y, float width, float height, int damage, float speed, boolean friendly) {
        super(texture, x, y, width, height);
        this.damage = damage;
        this.speed = speed * GameScreen.deltaCff;
        this.friendly = friendly;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void draw(SpriteBatch batch) {
        object.setPosition(bounds.getX(), bounds.getY());
        object.setRotation(bounds.getRotation());
        object.draw(batch);
        bounds.setPosition(bounds.getX(), bounds.getY() + speed);
    }
}
