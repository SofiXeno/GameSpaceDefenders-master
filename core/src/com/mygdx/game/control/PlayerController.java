package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.view.GameScreen;

public class PlayerController {
    private float speed = 15f;
    private float breakSpeed = 0.6f;
    private float shipSpeedX, shipSpeedY;
    private float width, height;
    private Polygon playerBounds;
    private Polygon hitbox;
    private float ratio;
    public PlayerController(Polygon carBounds, float width, float height, Polygon hitbox, float ratio) {
        this.playerBounds = carBounds;
        this.width = width;
        this.height = height;
        this.hitbox = hitbox;
        this.ratio = ratio;
    }


    public void handle() {
        float posX = playerBounds.getX();
        float posY = playerBounds.getY();
        //всё, что связано со скоростью
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            shipSpeedX = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            shipSpeedX = -speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            shipSpeedY = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            shipSpeedY = -speed;
        }

        shipSpeedX = downSpeed(shipSpeedX);
        shipSpeedY = downSpeed(shipSpeedY);

        if (playerBounds.getX() + shipSpeedX * Gdx.graphics.getDeltaTime() > -GameScreen.viewportWidth / 2
                && playerBounds.getX() + width + shipSpeedX * Gdx.graphics.getDeltaTime() < GameScreen.viewportWidth / 2)
            posX += shipSpeedX * Gdx.graphics.getDeltaTime();

        if (playerBounds.getY() + shipSpeedY * Gdx.graphics.getDeltaTime() > -GameScreen.viewportHeight / 2
                && playerBounds.getY() + height + shipSpeedY * Gdx.graphics.getDeltaTime() < GameScreen.viewportHeight / 2)

            posY += shipSpeedY * Gdx.graphics.getDeltaTime();
        playerBounds.setPosition(posX,
                posY);
        hitbox.setPosition(posX+9*ratio, posY+67*ratio);

    }

    private float downSpeed(float speed) {
        if (speed > breakSpeed) {
            return speed - breakSpeed;
        }
        if (speed < -breakSpeed)
            return speed + breakSpeed;
        else return 0;
    }

}
