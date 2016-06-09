package com.mygdx.game.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.utils.RandomGenerator;

/**
 * Created by admin on 2016-04-30.
 */
public class Chicken extends GameObject{
    private long lastEggTime = 0;
    private float healthPoints = 1;
    private Animation animation;          // #3
    private TextureRegion[] animationFrames;             // #5
    private float stateTime;

    public Chicken(float x, float y){
        width = 54;
        height = 39;
        texture = setTexture("kurczakWitka.png");
        boundingBox = new Rectangle(x,y,width,height);
        makeAnimation();
    }
    
    private void makeAnimation(){
        TextureRegion[][] tmp = TextureRegion.split(texture,texture.getWidth()/8,texture.getHeight()/1);
        int index = 0;
        animationFrames = new TextureRegion[8];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 8; j++) {
                animationFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(0.125f, animationFrames);      // #11         // #12
        stateTime = RandomGenerator.random(0,1);                         // #13
    }

    private TextureRegion getFrame(float deltaTime){
        stateTime += deltaTime;
        return animation.getKeyFrame(stateTime,true);
    }
    
    public void animate(SpriteBatch batch, float deltaTime){
        if(!batch.isDrawing()){
            batch.begin();
        }
        batch.draw(getFrame(deltaTime),boundingBox.getX(),boundingBox.getY(),width,height);
        batch.end();
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
            egg.setMiddle(getXMiddle(),getYMiddle());
            return egg;

        }
        return null;
    }
}
