package com.mygdx.game.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.game.Config;
import com.mygdx.game.game.Consts;

/**
 * Created by admin on 2016-04-30.
 */
public class Ship {
    private Vector2 position;
    private int width, height;
    private Rectangle boundingBox;
    private Texture texture;
    private long lastAttack;
    private float centerY;


    public Ship(float x, float y, int width, int height){
        texture = new Texture(Gdx.files.internal("ship.png"));
        position = new Vector2(x,y);
        this.width = width;
        this.height = height;
        boundingBox = new Rectangle(x,y,width,y-height);
//        this.centerY = boundingBox.getCenter(position).y;
    }

    public float getX() {
        return position.x;
    }

    public float getXMiddle() { return position.x + width/2;}

    public float getY() {
        return position.y;
    }

    @Deprecated
    public void setX( float x){
        position.x = x;
    }
    public void setXMiddle(float x) {
        boundingBox.setCenter(x,getY());
        position.x = boundingBox.x;
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

    public void smoothMove(int screenX, int screenY){
        float xx = getX();
        float middle = xx + 5 *  Gdx.graphics.getDeltaTime() * (screenX/ Config.screenRatioX - (xx+ Consts.HALF_SHIP_WIDTH)) + Consts.HALF_SHIP_WIDTH;
        setXMiddle(middle);
    }

}
