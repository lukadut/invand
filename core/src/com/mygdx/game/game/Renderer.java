package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private Skin skin;
    private Label score;
    private Label lives;
    private Image hearth;
    private Table table;

    public Renderer(final World world, final SpriteBatch batch, final Skin skin) {
        this.world = world;
        this.ship = world.getShip();
        this.skin = skin;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT);
        this.batch = batch;
        batch.setProjectionMatrix(camera.combined);

        lives = new Label(" 3 x",skin);
        score = new Label("0",skin);
        hearth = new Image( new Texture(Gdx.files.internal("hearth1.png")));
        hearth.setWidth(15);
        hearth.setHeight(15);

        font = new BitmapFont();
        table = new Table(skin){

        };
        table.setWidth(Consts.SCREEN_WIDTH);
        table.setPosition(0, Consts.SCREEN_HEIGHT - 20);
        table.add("Score: ").left();
        table.add(score).expand().left();


        table.add(hearth).expand().right();
        table.add(lives).right();
    }

    public void render(float delta){
        if(world.isPaused()){
            return;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        score.setText(world.getScore() + "");
        lives.setText(" " + world.getLives()+" x");

        batch.begin();
//        font.draw(batch, "Score " + world.getScore(), 0, Consts.SCREEN_HEIGHT);
        batch.draw(ship.getTexture(), ship.getX(), ship.getY());

        Iterator<GameObject> bulletIterator = world.getBullets().iterator();
        while(bulletIterator.hasNext()){
            GameObject bullet = bulletIterator.next();
            bullet.move();
            bullet.draw(batch);
            if(bullet.outOfMap()){
                bullet.dispose();
                bulletIterator.remove();
            }
        }

        Iterator<GameObject> chickenIterator = world.getChickens().iterator();
        while(chickenIterator.hasNext()){
            Chicken chicken = (Chicken)chickenIterator.next();
            chicken.animate(batch,delta);
            Egg egg = chicken.throwEgg();
            if (egg != null){
                world.getEggs().add(egg);
            }

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


        if(batch.isDrawing()) {
            batch.end();
        }

        batch.begin();
        table.draw(batch, 1f);
        batch.end();

        if(world.getChickens().size == 0){
            world.getBullets().clear();
            world.spawnChickens();
        }


    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}