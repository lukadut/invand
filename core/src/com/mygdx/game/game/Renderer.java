package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.game.objects.Chicken;
import com.mygdx.game.game.objects.Egg;
import com.mygdx.game.game.objects.GameObject;
import com.mygdx.game.game.objects.Ship;
import com.mygdx.game.game.objects.SimpleAttack;

import java.util.Iterator;

/**
 * Created by admin on 2016-04-30.
 */
public class Renderer {
    private World world;
    private Ship ship;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;

    public Renderer(final World world, final SpriteBatch batch) {
        this.world = world;
        this.ship = world.getShip();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT);
        this.batch = batch;
        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont();
    }

    public void render(float delta){
        if(world.isPaused()){
            return;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Score " + world.getScore(), 0, Consts.SCREEN_HEIGHT);

        batch.draw(ship.getTexture(), ship.getX(), ship.getY());

        Iterator<GameObject> bulletIterator = world.getBullets().iterator();
        while(bulletIterator.hasNext()){
            GameObject bullet = bulletIterator.next();
            bullet.move();
            bullet.draw(batch);
//            batch.draw(bullet.getTexture(), bullet.getBoundingBox().getX(), bullet.getBoundingBox().getY(),bullet.getBoundingBox().getWidth(),bullet.getBoundingBox().getHeight());
//            if(bullet.getBoundingBox().overlaps(endOfScreen)){
//                bulletIterator.remove();
//            }
            if(bullet.outOfMap()){
                bullet.dispose();
                bulletIterator.remove();
            }
        }

        Iterator<GameObject> chickenIterator = world.getChickens().iterator();
        while(chickenIterator.hasNext()){
            Chicken chicken = (Chicken)chickenIterator.next();
            chicken.animate(batch,delta);
//            batch.draw(chicken.getFrame(delta), chicken.getBoundingBox().getX(), chicken.getBoundingBox().getY(), chicken.getBoundingBox().getWidth(), chicken.getBoundingBox().getHeight());
            Egg egg = chicken.throwEgg();
            if (egg != null){
                world.getEggs().add(egg);
            }
//            batch.draw(chicken.getFrame(1f),100,100,100,100);

            bulletIterator = world.getBullets().iterator();
            while(bulletIterator.hasNext()) {
                SimpleAttack bullet = (SimpleAttack)bulletIterator.next();
                if(bullet.getBoundingBox().overlaps(chicken.getBoundingBox())){
                    if(chicken.applyDamage(bullet.getDamagePoints()) <= 0){
                        chicken.dispose();
                        chickenIterator.remove();
                        world.killedChicken();
                    }
                    bullet.dispose();
                    bulletIterator.remove();
                    break;
                }
            }
        }

        Iterator<GameObject> eggsIterator = world.getEggs().iterator();
        while(eggsIterator.hasNext()){
            GameObject egg = eggsIterator.next();
            egg.move();
            egg.draw(batch);
//            batch.draw(egg.getTexture(), egg.getBoundingBox().getX(), egg.getBoundingBox().getY(),egg.getBoundingBox().getWidth(),egg.getBoundingBox().getHeight());
            if(egg.getBoundingBox().overlaps(ship.getBoundingBox())){
                world.killedPlayer();
                eggsIterator.remove();
                continue;
            }
            if(egg.outOfMap()){
                egg.dispose();
                eggsIterator.remove();
            }
        }


//        world.getBullets().forEach((GameObject bullet) -> batch.draw(ship.getTexture(), ship.getX(), ship.getY()); );
        if(batch.isDrawing()) {
            batch.end();
        }

        if(world.getChickens().size == 0 && !world.getWaitForSpawn()){
            world.getBullets().clear();
            world.spawnChickens();
//            waitForRespawn = true;
//            System.out.println("tutaj dodaje nextleveltask");
//            timer.scheduleTask(nextLevel,3,0,1);
//            synchronized (timer) {
//                timer.notifyAll();
//            }
//            world.getBullets().clear();
//            world.nextLevel();
        }


    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}