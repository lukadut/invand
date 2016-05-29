package com.mygdx.game.game.objects.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.game.objects.GameObject;

/**
 * Created by admin on 2016-05-28.
 */
public class SimpleAttack implements GameObject {
    protected Vector2 position;
    protected int width = 6, height = 16;
    protected Rectangle boundingBox;
    protected Texture texture;
    private float damagePoints = 1;

    public SimpleAttack(float x, float y){
        texture = setTexture("atak.png");
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
        boundingBox.setY(boundingBox.getY()+5);
    }

    public float getDamagePoints(){
        return damagePoints;
    }
}
