package com.mygdx.game.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.game.objects.Chicken;
import com.mygdx.game.game.objects.GameObject;
import com.mygdx.game.game.objects.Ship;
import com.mygdx.game.game.objects.attacks.SimpleAttack;

/**
 * Created by admin on 2016-04-30.
 */
public class World {
    private Ship ship;
    private Array<GameObject> chickens;
    private Array<GameObject> eggs;
    private Array<GameObject> bullets;
    private Array<GameObject> powerUps;
    private int lives = 3;
    private long score;
    private Boolean waitForSpawn = false;
    private Timer timer;


    public World() {
        timer = Timer.instance();
        float halfWidth = (Consts.SCREEN_WIDTH - Consts.SHIP_WIDTH) / 2;
        ship = new Ship(halfWidth,Consts.EDGE_DISTANCE,Consts.SHIP_WIDTH,Consts.SHIP_HEIGHT);
        chickens = new Array<GameObject>();
        eggs = new Array<GameObject>();
        bullets = new Array<GameObject>();
        powerUps = new Array<GameObject>();
        prepareGame();


    }

    public Ship getShip() {
        return ship;
    }
    public Array<GameObject> getChickens(){
        return chickens;
    }
    public Array<GameObject> getEggs(){
        return eggs;
    }
    public Array<GameObject> getBullets(){
        return bullets;
    }
    public Array<GameObject> getPowerUps(){
        return powerUps;
    }
    public int getLives(){return lives;}
    public boolean paused = false;
    public long getScore() {return score;}
    public Boolean getWaitForSpawn(){return waitForSpawn;}
    public void setWaitForSpawn(Boolean waitForSpawn){this.waitForSpawn = waitForSpawn;}


    protected void prepareGame(){
        spawnChickens();
    }

    public void spawnChickens() {
        for (int y = 70; y < 350; y=y+70) {
            for (int x = 0; x < Consts.SCREEN_WIDTH; x = x + 61) {
                final Chicken chicken = new Chicken(x,Consts.SCREEN_HEIGHT-y);
                chickens.add(chicken);
//                timer.scheduleTask(new Timer.Task() {
//                    @Override
//                    public void run() {
//                        eggs.add(chicken.throwEgg());
//                    }
//                },2,5,3);
            }
        }
//        Chicken chicken = new Chicken(200,Consts.SCREEN_HEIGHT-100);
//                chickens.add(chicken);
    }

    private void prepareNextLevel(){
//        chickens.clear();
//        bullets.clear();
        System.out.println("preparenext");
        spawnChickens();
        waitForSpawn = false;

    }

    public void nextLevel(){
        waitForSpawn = true;
        System.out.println("next level");

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                prepareNextLevel();
            }
        }, 3, 0, 0);
//        timer.start();
    }

    public void killedChicken(){
        score++;
    }

    public void killedPlayer(){
        score -= 100;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void moveShip(int screenX, int screenY){
        if(paused){
            return;
        }
        ship.smoothMove(screenX,screenY);
    }

    public void attack(){
        if(paused){
            return;
        }
        GameObject bullet = new SimpleAttack(0,Consts.EDGE_DISTANCE + Consts.SHIP_HEIGHT);
        bullet.setXMiddle(ship.getXMiddle());
        bullets.add(bullet);
//        score++;
    }
}
