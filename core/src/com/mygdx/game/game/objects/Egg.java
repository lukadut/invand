package com.mygdx.game.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.RandomGenerator;

/**
 * Created by admin on 2016-04-30.
 */
public class Egg extends GameObject{
    public Egg(float x, float y){
        width = 15;
        height = 19;
        texture = setTexture("egg.png");
        boundingBox = new Rectangle(x,y,width,height);
        movingVector = new Vector2(0, RandomGenerator.random(1.5f,2));
    }
}
