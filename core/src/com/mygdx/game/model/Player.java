package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.control.PlayerController;
import com.mygdx.game.view.GameScreen;

public class Player extends Spaceship implements Runnable {
    private PlayerController controller;

    public Player(float x, float y, float width, float height, GameScreen screen, Ships type) {
        super(screen.getCharManager().getSprite(type), x, y, width, height, screen);
        controller = new PlayerController(bounds, width, height);
        setWeapon(Weapon.getDefaultSingle());
        setType(type);
        player = true;
        setDamage(15);
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
}
