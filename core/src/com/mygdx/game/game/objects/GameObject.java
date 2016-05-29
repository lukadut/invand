package com.mygdx.game.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by admin on 2016-05-28.
 */
public interface GameObject {
    public float getX();
    public float getY() ;

    public float getXMiddle();
    public void setXMiddle(float x) ;

    public float getWidth() ;
    public float getHeight() ;

    public Rectangle getBoundingBox();
    public Texture getTexture();
    public void move();
}
