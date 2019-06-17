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
        setWeapon(Weapon.getDefaultSingle());
        setType(type);
        player = true;
        setDamage(15);
        float ratio = width/256;
        hitbox = new Polygon(new float[]{9*ratio, 67*ratio, 28*ratio, 126*ratio, 92*ratio, 156*ratio, 92*ratio, 226*ratio, 162*ratio, 226*ratio, 162*ratio, 156*ratio, 227*ratio, 126*ratio, 248*ratio, 67*ratio});
        controller = new PlayerController(bounds, width, height, hitbox, ratio);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        controller.handle();
    }

    @Override
    public void run() {
        if (getWeapon().getType() == 2)
            shoot(damage - 2, type);
        else if (getWeapon().getType() == 3)
            shoot(damage - 5, type);
        else
            shoot(damage, type);

    }

    @Override
    public Polygon getHitbox(){
        return hitbox;
    }
}
