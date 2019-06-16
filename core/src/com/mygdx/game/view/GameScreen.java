package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.MyGame;
import com.mygdx.game.model.*;
import com.mygdx.game.utils.UI;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameScreen extends MyScreen {
    private int score;
    private Random random;
    private double difficulty;
    private CharacterManager charManager;
    private TextureAtlas ships;

    public CharacterManager getCharManager() {
        return charManager;
    }

    private TextureAtlas projs;
    private Player player;
    private UI ui;
    private LinkedList<Projectile> projectiles;
    private LinkedList<Spaceship> spaceships;
    private ScheduledExecutorService ses;
    private Spawner s;

    public GameScreen(MyGame game, double difficulty) {
        super(game);
        this.difficulty = difficulty;

    }

    public void initialize() {
        charManager = game.m;
        player = new Player( 0f, 0f, 5f, 5f, this, Spaceship.Ships.PLAYER_RED);
        projectiles = new LinkedList<Projectile>();
        spaceships = new LinkedList<Spaceship>();
        spaceships.add(player);
        ui = new UI();

        random = new Random();
        s = new Spawner(2000, this);

    }

    @Override
    public void show() {

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(!s.isAlive())
            s.start();
        s.setActive(true);
        beginAutoshoot();
        addEnemy(2, 1);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        deltaCff = delta;
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        if (game.getScreen() == this) {
            drawEnemies(game.getBatch());
            player.draw(game.getBatch());
            handleBattle(game.getBatch());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            s.setActive(false);
            ses.shutdown();
            game.changeScreen(MyGame.Screens.PAUSE);
        }
        game.getBatch().end();
        ui.draw();
    }

    private void drawEnemies(SpriteBatch batch) {
        for(int i = 1; i<spaceships.size(); i++){
            if(spaceships.get(i).getBounds().getY()+spaceships.get(i).getHeight()<-viewportHeight/2) {
                spaceships.remove(i);
                i--;
            }else
            spaceships.get(i).draw(batch);
        }
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        game.getBatch().dispose();
        ui.dispose();
    }

    public Random getRandom() {
        return random;
    }

    public void addEnemy(int xPos, int type) {
        if(xPos+6.1f>viewportWidth/2)
            xPos +=(viewportWidth/2-xPos-6.1f);
        switch (type) {
            case 0:
                spaceships.add(new Enemy(Spaceship.Ships.ENEMY1, xPos, viewportWidth/2, 5f, 5f*2.06f, this));
                break;
            case 1:
                spaceships.add(new Enemy(Spaceship.Ships.ENEMY2, xPos, viewportWidth/2, 5f, 5f*1.9f, this));
                break;
            case 2:
                spaceships.add(new Enemy(Spaceship.Ships.ENEMY3, xPos, viewportWidth/2, 4f, 4f*1.75f, this));
                break;
            case 3:
                spaceships.add(new Enemy(Spaceship.Ships.ENEMY4, xPos, viewportWidth/2, 6f, 6f*2.52f, this));
                break;
        }
    }

    public void projectileFired(float x, float y, int damage, float speed, boolean friendly, Spaceship.Ships type) {
        Projectile p;
        switch(type){
            case PLAYER_RED:
                 p = new Projectile(charManager.getLaser(CharacterManager.Lasers.RED), x, y, 1.5f, 3f, damage, speed, friendly);
                break;
            case PLAYER_BLUE:
                p = new Projectile(charManager.getLaser(CharacterManager.Lasers.BLUE), x, y, 1.5f, 3f, damage, speed, friendly);
                break;
            case PLAYER_GREEN:
                p = new Projectile(charManager.getLaser(CharacterManager.Lasers.BLUE), x, y, 1.5f, 3f, damage, speed, friendly);
                break;
            case ENEMY4:
                p = new Projectile(charManager.getLaser(CharacterManager.Lasers.PURPLE), x, y, 2f, 4f, damage, speed, friendly);
                break;
                default:
                    p = new Projectile(charManager.getLaser(CharacterManager.Lasers.PURPLE), x, y, 1.5f, 3f, damage, speed, friendly);

        }
        projectiles.add(p);
    }

    private void handleBattle(SpriteBatch batch) {
        Projectile p;
        for (int i = 0; i < projectiles.size(); i++) {
            p = projectiles.get(i);
            if (p.getBounds().getY() > halfHeight || p.getBounds().getY() + p.getHeight() < -halfHeight) {
                projectiles.remove(i);
                i--;
            }
            checkHits(p, batch);
        }


    }

    private void checkHits(Projectile p, SpriteBatch batch) {
        p.draw(batch);
        for (int i = 0; i < spaceships.size(); i++) {

            if (p.isFriendly() == spaceships.get(i).isPlayer())
                continue;
            if (spaceships.get(i).getHitbox().contains(p.getBounds().getX() + p.getWidth() / 2, p.getBounds().getY() + p.getHeight() / 2)) {

                if (spaceships.get(i).processDamage(p.getDamage()) == 0) {
                    if (i != 0) {
                        score+=((Enemy)spaceships.get(i)).getPoints();
                                spaceships.remove(i);
                    }
                    //  else{
                    //DO SOMETHING
                    //}
                    i--;
                }else
                    ui.updateHealth(player.getHealth());
                projectiles.remove(p);
            }
        }
    }

    public void beginAutoshoot() {
        ses = Executors.newScheduledThreadPool(1);
        ses.scheduleAtFixedRate(player, 500, player.getWeapon().getCooldownTime(), TimeUnit.MILLISECONDS);
    }

    public void setShips(TextureAtlas textureAtlas) {
        this.ships = textureAtlas;
    }

    public void setProjs(TextureAtlas textureAtlas) {
        this.projs = textureAtlas;
    }
}
