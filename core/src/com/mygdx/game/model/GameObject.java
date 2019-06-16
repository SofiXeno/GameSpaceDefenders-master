package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;


abstract class GameObject {

    protected float width, height;
    Polygon bounds;
    Sprite object;

    GameObject(TextureRegion texture, float x, float y, float width, float height) {
        this.height = height;
        this.width = width;
        object = new Sprite(texture);
        object.setSize(width, height);
        object.setOrigin(width / 2f, height / 2f);
        object.setPosition(x, y);

        bounds = new Polygon(new float[]{0f, 0f, width, 0f, width, height, 0f, height});
        bounds.setPosition(x, y);
        object.setOrigin(width / 2f, height / 2f);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void draw(SpriteBatch batch) {
        object.setPosition(bounds.getX(), bounds.getY());
        object.setRotation(bounds.getRotation());
        object.draw(batch);
    }

    public Polygon getBounds() {
        return bounds;
    }
}
