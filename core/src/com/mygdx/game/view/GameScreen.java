package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;
import com.mygdx.game.model.*;
import com.mygdx.game.utils.GameUI;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameScreen extends MyScreen {
    private Texture gameOver;
    private int timeElapsed;
    private int score;
    private Random random;
    private float difficulty;
    private CharacterManager charManager;
    private Player player;
    private GameUI ui;
    private LinkedList<Projectile> projectiles;
    private LinkedList<Spaceship> spaceships;
    private ScheduledExecutorService ses;
    private Spawner s;
    private boolean gameover;
    private Spaceship.Ships playerSkin;

    public GameScreen(MyGame game, MyGame.Difficulties diff, Spaceship.Ships ship) {
        super(game);
        playerSkin = ship;
        switch (diff) {
            case VERY_EASY:
                difficulty = 1.2f;
                break;
            case EASY:
                difficulty = 1.1f;
                break;
            case MEDIUM:
                difficulty = 1f;
                break;
            case HARD:
                difficulty = 0.8f;
                break;
            case VERY_HARD:
                difficulty = 0.6f;
                break;
        }
    }

    public CharacterManager getCharManager() {
        return charManager;
    }

    public void initialize() {
        gameover = false;
        charManager = game.m;
        projectiles = new LinkedList<Projectile>();
        spaceships = new LinkedList<Spaceship>();
        ui = new GameUI();
        timeElapsed = 0;
        random = new Random();
        gameOver = new Texture(Gdx.files.internal("GameOverLabel.png"));
        s = new Spawner(120, this);
    }

    @Override
    public void show() {
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (player == null) {
            player = new Player(0f, 0f, 5f, 5f, this, playerSkin);
            spaceships.add(player);
        }
        beginAutoshoot();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        deltaCff = delta;
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        renderScreen();
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            ses.shutdown();
            game.changeScreen(MyGame.Screens.PAUSE);
        }
        game.getBatch().end();
        ui.draw();
    }

    private void drawEnemies(SpriteBatch batch) {
        for (int i = 1; i < spaceships.size(); i++) {
            if (spaceships.get(i).getBounds().getY() + spaceships.get(i).getHeight() < -viewportHeight / 2) {
                spaceships.remove(i);
                i--;
            } else
                spaceships.get(i).draw(batch);
        }
    }

    private void renderScreen() {
        game.getBatch().draw(bg, -viewportWidth / 2, -viewportHeight / 2, viewportWidth, viewportHeight);
        if (game.getScreen() == this) {
            if (gameover) {
                game.getBatch().draw(gameOver, -5f, -2.5f, 10f, 5f);
                if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
                    try {
                        MenuScreen.setHS(score);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    game.newMenu();
                }
                return;
            }
            drawEnemies(game.getBatch());
            player.draw(game.getBatch());
            s.spawn();
            handleBattle(game.getBatch());
            timeElapsed++;
            if (timeElapsed == 30 * 60 && difficulty - 0.1 >= 0.5) {
                difficulty -= 0.1;
                player.powerUp();
                ui.updateHealth(player.getHealth());
                timeElapsed = 0;
            }
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
        if (xPos + 6.1f > viewportWidth / 2)
            xPos += (viewportWidth / 2 - xPos - 6.1f);
        switch (type) {
            case 0:
                spaceships.add(new Enemy(Spaceship.Ships.ENEMY1, xPos, viewportWidth / 2, 5f, 5f * 2.06f, this));
                break;
            case 1:
                spaceships.add(new Enemy(Spaceship.Ships.ENEMY2, xPos, viewportWidth / 2, 5f, 5f * 1.9f, this));
                break;
            case 2:
                spaceships.add(new Enemy(Spaceship.Ships.ENEMY3, xPos, viewportWidth / 2, 4f, 4f * 1.75f, this));
                break;
            case 3:
                spaceships.add(new Enemy(Spaceship.Ships.ENEMY4, xPos, viewportWidth / 2, 6f, 6f * 2.52f, this));
                break;
        }
    }

    public void projectileFired(float x, float y, int damage, float speed, boolean friendly, Spaceship.Ships type) {
        Projectile p;
        if (!friendly)
            speed /= difficulty;
        switch (type) {
            case PLAYER_RED:
                p = new Projectile(charManager.getLaser(CharacterManager.Lasers.RED), x - 1.5f / 2, y, 1.5f, 3f, damage, speed, friendly);
                break;
            case PLAYER_BLUE:
                p = new Projectile(charManager.getLaser(CharacterManager.Lasers.BLUE), x - 1.5f / 2, y, 1.5f, 3f, damage, speed, friendly);
                break;
            case PLAYER_GREEN:
                p = new Projectile(charManager.getLaser(CharacterManager.Lasers.GREEN), x - 1.5f / 2, y, 1.5f, 3f, damage, speed, friendly);
                break;
            case ENEMY4:
                p = new Projectile(charManager.getLaser(CharacterManager.Lasers.PURPLE), x - 2f / 2, y, 2f, 4f, damage, speed, friendly);
                break;
            default:
                p = new Projectile(charManager.getLaser(CharacterManager.Lasers.PURPLE), x - 1.5f / 2, y, 1.5f, 3f, damage, speed, friendly);
        }
        projectiles.add(p);
    }

    public float getDifficulty() {
        return difficulty;
    }

    private void handleBattle(SpriteBatch batch) {
        Projectile p;
        for (int i = 0; i < projectiles.size(); i++) {
            p = projectiles.get(i);
            if (p.getBounds().getY() > halfHeight || p.getBounds().getY() + p.getHeight() < -halfHeight) {
                projectiles.remove(i);
                i--;
                continue;
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
                projectiles.remove(p);
                if (spaceships.get(i).processDamage(p.getDamage()) == 0) {
                    if (i != 0) {
                        score += ((Enemy) spaceships.get(i)).getPoints();
                        ui.updateScore(score);
                        spaceships.remove(i);
                    } else {
                        ui.updateHealth(player.getHealth());
                        gameover = true;
                        spaceships.remove(i);
                    }
                    i--;
                } else
                    ui.updateHealth(player.getHealth());
            }
        }
    }

    public void beginAutoshoot() {
        ses = Executors.newScheduledThreadPool(1);
        ses.scheduleAtFixedRate(player, 500, player.getWeapon().getCooldownTime(), TimeUnit.MILLISECONDS);
    }

}
