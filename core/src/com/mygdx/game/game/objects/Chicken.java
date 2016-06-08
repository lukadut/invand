package com.mygdx.game.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.RandomGenerator;

import java.util.Random;

/**
 * Created by admin on 2016-04-30.
 */
public class Chicken implements GameObject{
    protected int width = 54, height = 39;
    protected Rectangle boundingBox;
    protected Texture texture;
    private long lastEggTime = 0;
    private float healthPoints = 1;

    Animation walkAnimation;          // #3
    Texture                         walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5
    float stateTime;

    public Chicken(float x, float y){
        texture = setTexture("kurczakWitka.png");
        boundingBox = new Rectangle(x,y,width,height);
        walkSheet = setTexture("kurczakWitka.png");
        TextureRegion[][] tmp = TextureRegion.split(texture,texture.getWidth()/8,texture.getHeight()/1);
        int index = 0;
        walkFrames = new TextureRegion[8];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 8; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.125f, walkFrames);      // #11         // #12
        stateTime = RandomGenerator.random(0,1);                         // #13
    }

    public TextureRegion getFrame(float deltaTime){
        stateTime += deltaTime;
        return walkAnimation.getKeyFrame(stateTime,true);
    }

    public void move() {

    }

    protected Texture setTexture(String fileName){
        return new Texture(Gdx.files.internal(fileName));
    }

    public float getX() {
        return boundingBox.getX();
    }

    public float getXMiddle() {
        return getX() + width/2;
    }

    public float getY() {
        return boundingBox.getY();
    }

    public void setXMiddle(float x) {
        boundingBox.setCenter(x,getY());
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getHealthPoints(){
        return healthPoints;
    }

    public float applyDamage(float damage){
        this.healthPoints -= damage;
        return this.healthPoints;
    }


    public Egg throwEgg(){
        if (System.currentTimeMillis() - lastEggTime > 3000 && RandomGenerator.random(0,1000)<1) {
            lastEggTime = System.currentTimeMillis();
            Egg egg = new Egg(0, getBoundingBox().getY() - getHeight());
            egg.setXMiddle(getXMiddle());
            return egg;

        }
        return null;
    }
}
