package com.mygdx.game.model;

import com.mygdx.game.view.GameScreen;

import java.util.Random;

public class Spawner extends Thread{
    private int spawnRate;
    private Random r;
    private GameScreen gs;
    private boolean active;
    public Spawner(int initialSpawnRate, GameScreen gs){
        spawnRate = initialSpawnRate;
        r = new Random();
        this.gs = gs;
        active = true;
    }


    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public void shutdown(){
        active = false;
    }

    @Override
    public void run() {
        while(active){
//            gs.addEnemy(r.nextInt());
//            r.nextInt();
//            sleep();
        }
    }
}
