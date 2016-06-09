package com.mygdx.game.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.game.objects.GameObject;
import com.mygdx.game.utils.RandomGenerator;

/**
 * Created by admin on 2016-05-28.
 */
public class SimpleAttack extends GameObject {
    private float damagePoints = 1;

    public SimpleAttack(float x, float y){
        width = 6;
        height = 16;
        texture = setTexture("atak.png");
        boundingBox = new Rectangle(x,y,width,height);
        movingVector = new Vector2(0, -5);
    }

    @Override
    public void move() {
        super.move();
        movingVector.y -= 0.01f;
    }

    public float getDamagePoints(){
        return damagePoints;
    }
}
