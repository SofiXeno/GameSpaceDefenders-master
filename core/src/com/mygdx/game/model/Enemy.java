package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.GameScreen;
import com.mygdx.game.view.MyScreen;

public class Enemy extends Spaceship {
    private int movementX, movementY;

    public int getPoints() {
        return points;
    }

    private int points;
    private int cooldown;
    private int preparation;

    public Enemy(Ships ship, float x, float y, float width, float height, GameScreen screen) {
        super(screen.getCharManager().getSprite(ship), x, y, width, height, screen);
        player = false;
        preparation = 0;
        determineEnemy(ship);
        movementY = -(screen.getRandom().nextInt(3)+1);
        movementX = screen.getRandom().nextInt(4);
        if(screen.getRandom().nextBoolean())
            movementX*=-1;
        setType(ship);
    }


    private void determineEnemy(Ships ship) {
        switch (ship){
            case ENEMY1:
                setWeapon(new Weapon(600, Weapon.DOUBLE));
                cooldown  = 300;
                damage = 10;
                points = 10;
                break;
            case ENEMY2:
                setWeapon(Weapon.getDefaultSingle());
                cooldown = 200;
                damage = 15;
                points = 20;
                break;
            case ENEMY3:
                setWeapon(new Weapon(600, Weapon.MINIGUN));
                cooldown = 100;
                damage = 5;
                movementY--;
                if(movementX>=0)
                movementX+=2;
                else
                    movementY-=2;
                points = 15;
                break;
            case ENEMY4:
                setWeapon(new Weapon(100, Weapon.MINIGUN));
                cooldown = 150;
                damage = 30;
                points = 50;
                break;

        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        move();
        preparation++;
        if (preparation == cooldown) {
            shoot(damage, type);
            preparation = screen.getRandom().nextInt(cooldown+1) + 1;
        }
    }

    private void move() {
        if(bounds.getX()+movementX* Gdx.graphics.getDeltaTime()> -MyScreen.viewportWidth/2 &&bounds.getX()+getWidth()+movementX* Gdx.graphics.getDeltaTime()<MyScreen.viewportWidth/2)
            bounds.setPosition(bounds.getX()+movementX*Gdx.graphics.getDeltaTime(), bounds.getY()+movementY*Gdx.graphics.getDeltaTime());
        else
            movementX*=-1;
    }

}
