package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.GameScreen;
import com.mygdx.game.view.MyScreen;

public class Enemy extends Spaceship {
    private float movementX, movementY;
    private int points;
    private int cooldown;
    private int preparation;

    public Enemy(Ships ship, float x, float y, float width, float height, GameScreen screen) {
        super(screen.getCharManager().getSprite(ship), x, y, width, height, screen);
        player = false;
        preparation = 0;
        determineEnemy(ship);
        movementY = -(screen.getRandom().nextInt(3) + 1);
        movementX = screen.getRandom().nextInt(4);
        if (screen.getRandom().nextBoolean())
            movementX *= -1;
        setType(ship);
    }

    public int getPoints() {
        return points;
    }

    private void determineEnemy(Ships ship) {
        switch (ship) {
            case ENEMY1:
                setWeapon(new Weapon(600, Weapon.DOUBLE));
                health = 80;
                cooldown =(int) (300*screen.getDifficulty());
                damage = 10;
                points = 100;
                break;
            case ENEMY2:
                setWeapon(Weapon.getDefaultSingle());
                health = 100;
                cooldown = (int) (300*screen.getDifficulty());
                damage = 15;
                points = 200;
                break;
            case ENEMY3:
                setWeapon(new Weapon(600, Weapon.MINIGUN));
                health = 60;
                cooldown = (int) (200*screen.getDifficulty());
                damage = 5;
                movementY--;
                movementX*=3;
                points = 150;
                break;
            case ENEMY4:
                setWeapon(new Weapon(100, Weapon.MINIGUN));
                health = 150;
                cooldown = (int) (400*screen.getDifficulty());
                damage = 30;
                points = 500;
                break;

        }
        movementX/=screen.getDifficulty();
        movementY/=screen.getDifficulty();


    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        move();
        preparation++;
        if (preparation == cooldown) {
            shoot(damage, type);
            preparation = screen.getRandom().nextInt(cooldown + 1) + 1;
        }
    }

    private void move() {
        if (bounds.getX() + movementX * Gdx.graphics.getDeltaTime() > -MyScreen.viewportWidth / 2 && bounds.getX() + getWidth() + movementX * Gdx.graphics.getDeltaTime() < MyScreen.viewportWidth / 2)
            bounds.setPosition(bounds.getX() + movementX * Gdx.graphics.getDeltaTime(), bounds.getY() + movementY * Gdx.graphics.getDeltaTime());
        else
            movementX *= -1;
    }

}
