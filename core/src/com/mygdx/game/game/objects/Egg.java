package com.mygdx.game.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by admin on 2016-04-30.
 */
public class Egg implements GameObject{
    protected Vector2 position;
    protected int width = 15, height = 19;
    protected Rectangle boundingBox;
    protected Texture texture;

    public Egg(float x, float y){
        texture = setTexture("egg.png");
        position = new Vector2(x,y);
        boundingBox = new Rectangle(x,y,width,height);
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

    public void move(){
        boundingBox.setY(boundingBox.getY()-2);
    }
}
