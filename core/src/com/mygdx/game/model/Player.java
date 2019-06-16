package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.control.PlayerController;
import com.mygdx.game.view.GameScreen;

public class Player extends Spaceship implements Runnable{
    private PlayerController controller;
    private GameScreen screen;
    public Player(TextureRegion texture, float x, float y, float width, float height, GameScreen screen) {
        super(texture, x, y, width, height);
        controller = new PlayerController(bounds, width, height);
        setWeapon(Weapon.getDefaultSingle());
        this.screen = screen;
        player = true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        controller.handle();
    }

    @Override
    public void run() {
        screen.projectileFired(bounds.getX()+width/2, bounds.getY()+height, 10, 20f, true);
    }


}
