package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.MyGame;
import com.mygdx.game.model.*;
import com.mygdx.game.utils.UI;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameScreen extends MyScreen{
    private CharacterManager charManager;
    private static int difficulty;
    private TextureAtlas ships;
    private TextureAtlas projs;
    private Player player;
    private UI ui;
    private LinkedList<Projectile> projectiles;
    private LinkedList<Spaceship> spaceships;
    private ScheduledExecutorService ses;

    public GameScreen(MyGame game) {
        super(game);


    }
    public void initialize(){
        player = new Player(ships.findRegion("green"), 0f, 0f, 5f, 5f, this);
        projectiles = new LinkedList<Projectile>();
        spaceships = new LinkedList<Spaceship>();
        spaceships.add(player);
        ui = new UI();
        charManager = new CharacterManager();
        addEnemy();
    }
    @Override
    public void show() {

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        beginAutoshoot();
    }

    @Override
    public void render(float delta) {
        clearScreen();

        deltaCff = delta;
        System.out.println(Gdx.graphics.getFramesPerSecond() + "        " + Gdx.graphics.getDeltaTime());
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        if (game.getScreen() == this) {
            player.draw(game.getBatch());
            handleBattle(game.getBatch());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            ses.shutdown();
            game.changeScreen(MyGame.Screens.PAUSE);
        }
        game.getBatch().end();
        ui.draw();
    }

    @Override
    public void hide() {
        ses.shutdown();
    }

    @Override
    public void dispose() {
        game.getBatch().dispose();
        ui.dispose();
    }

    public void addEnemy() {
        spaceships.add(new Enemy(ships.findRegion("red"), 0f, 10f, 5f, 5f));
    }

    public void projectileFired(float x, float y, int damage, float speed, boolean friendly) {
        Projectile p = new Projectile(projs.findRegion("greenlight"), x, y, 1f, 2f, damage, speed, friendly);
        projectiles.add(p);
    }

    private void handleBattle(SpriteBatch batch) {
        Projectile p;
        for (int i = 0; i < projectiles.size(); i++) {
            p = projectiles.get(i);
            p.draw(batch);
            if (p.getBounds().getY() > halfHeight || p.getBounds().getY() + p.getHeight() < -halfHeight) {
                projectiles.remove(i);
                i--;
            }
            checkHits(p, batch);
        }


    }

    private void checkHits(Projectile p, SpriteBatch batch) {
        for (int i = 0; i < spaceships.size(); i++) {
            if (i != 0)
                spaceships.get(i).draw(batch);
            //       if(p.isFriendly()==spaceships.get(i).isPlayer())
            //            continue;
            if (spaceships.get(i).getHitbox().contains(p.getBounds().getX() + p.getWidth() / 2, p.getBounds().getY() + p.getHeight() / 2)) {
                projectiles.remove(p);
                if (spaceships.get(i).processDamage(p.getDamage()) == 0) {
                    if (i != 0)
                        spaceships.remove(i);
                    //  else{
                    //DO SOMETHING
                    //}
                    i--;
                }
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
