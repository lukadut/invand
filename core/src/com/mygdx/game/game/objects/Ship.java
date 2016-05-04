package com.mygdx.game.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by admin on 2016-04-30.
 */
public class Ship {
    private Vector2 position;
    private int width, height;
    private Rectangle boundingBox;
    private Texture texture;
    private long lastAttack;

    public Ship(float x, float y, int width, int height){
        texture = new Texture(Gdx.files.internal("ship.png"));
        position = new Vector2(x,y);
        this.width = width;
        this.height = height;
        boundingBox = new Rectangle(x,y,width,y-height);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void setX( float x){
        position.x = x;
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
}
