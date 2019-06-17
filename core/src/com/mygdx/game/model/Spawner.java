package com.mygdx.game.model;

import com.mygdx.game.view.GameScreen;
import com.mygdx.game.view.MyScreen;

import java.util.Random;

public class Spawner {
    private int spawnRate;
    private Random r;
    private GameScreen gs;
    private boolean active;
    private int currentTime;
    private int timeTillNextSpawn;

    public Spawner(int initialSpawnRate, GameScreen gs) {
        spawnRate = initialSpawnRate;
        r = new Random();
        this.gs = gs;
        active = true;
        currentTime = 0;
        timeTillNextSpawn = spawnRate+r.nextInt((int)(90*gs.getDifficulty()));
    }


    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public void setActive(boolean b) {
        active = b;
    }

    public boolean isActive() {
        return active;
    }


    private int randomiseSpawn(int spawn) {
        if (spawn < 300)
            return 0;
        else if (spawn < 600)
            return 1;
        else if (spawn < 850)
            return 2;
        else
            return 3;
    }

    public void spawn() {

        currentTime++;
        if (currentTime == timeTillNextSpawn) {
            currentTime = 0;
            timeTillNextSpawn = spawnRate+r.nextInt((int)(90*gs.getDifficulty()));
            int rnd = r.nextInt((int) (MyScreen.viewportWidth / 2));
            if (r.nextBoolean())
                rnd *= -1;
            gs.addEnemy(rnd, randomiseSpawn(r.nextInt(1000)));
        }
    }
    }

