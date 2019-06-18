package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.control.PlayerController;
import com.mygdx.game.view.GameScreen;


public class Player extends Spaceship implements Runnable {
    private PlayerController controller;
    private Polygon hitbox;
    public Player(float x, float y, float width, float height, GameScreen screen, Ships type) {
        super(screen.getCharManager().getSprite(type), x, y, width, height, screen);

        setType(type);
        player = true;
        switch(type){
            case PLAYER_GREEN:
                setDamage(15);
                setWeapon(Weapon.getDefaultSingle());
                break;
            case PLAYER_BLUE:
                setDamage(10);
                setWeapon(Weapon.getDefaultDouble());
                break;
            case PLAYER_RED:
                setDamage(8);
                setWeapon(Weapon.getDefaultMinigun());
        }

        float ratio = width/256;
        hitbox = new Polygon(new float[]{0, 0, 9*ratio, 67*ratio, 28*ratio, 126*ratio, 92*ratio, 156*ratio, 92*ratio, 226*ratio, 162*ratio, 226*ratio, 162*ratio, 156*ratio, 227*ratio, 126*ratio, 248*ratio, 67*ratio, 0, 256*ratio});
        controller = new PlayerController(bounds, width, height, hitbox);
    }
    public void powerUp(){
        health+=10;
        switch (type){
            case PLAYER_RED:
                damage+=1;
                break;
            case PLAYER_BLUE:
                damage+=2;
            case PLAYER_GREEN:
                damage+=4;
        }

    }
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        controller.handle();
    }

    @Override
    public void run() {
       shoot(damage, type);

    }

    @Override
    public Polygon getHitbox(){
        return hitbox;
    }
}
