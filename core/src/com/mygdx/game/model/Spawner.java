package com.mygdx.game.model;

import com.mygdx.game.view.GameScreen;
import com.mygdx.game.view.MyScreen;

import java.util.Random;

public class Spawner extends Thread {
    private int spawnRate;
    private Random r;
    private GameScreen gs;
    private boolean active;

    public Spawner(int initialSpawnRate, GameScreen gs) {
        spawnRate = initialSpawnRate;
        r = new Random();
        this.gs = gs;
        active = false;
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


    private int randomiseSpawn(int spawn){
        if(spawn<300)
            return 0;
        else if(spawn<600)
            return 1;
        else if(spawn<850)
            return 2;
        else
            return 3;
    }
    @Override
    public void run() {

        while (true) {
            if(!active)
                continue;
            int xPos = (int)(MyScreen.viewportWidth/2);
            int rnd = r.nextInt(xPos);
            if(r.nextBoolean())
                rnd*=-1;
            gs.addEnemy(rnd, randomiseSpawn(r.nextInt(1000)));
            System.out.println("Thread is running");
            try {
                sleep(Math.abs(r.nextInt(2000) + spawnRate));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
