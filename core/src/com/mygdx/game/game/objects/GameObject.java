package com.mygdx.game.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.game.Consts;

/**
 * Created by admin on 2016-05-28.
 */
public class GameObject {
    protected int width     = 0;
    protected int height    = 0;
    protected Rectangle boundingBox;
    protected Texture texture;
    protected Vector2 movingVector = new Vector2(0,0);

    private static Rectangle borderTop = new Rectangle(0, Consts.SCREEN_HEIGHT,Consts.SCREEN_WIDTH,10000);
    private static Rectangle borderBottom = new Rectangle(0, -10000,Consts.SCREEN_WIDTH,10000);
    private static Rectangle borderLeft = new Rectangle(-10000, 0,10000,Consts.SCREEN_HEIGHT);
    private static Rectangle borderRight = new Rectangle(Consts.SCREEN_WIDTH, 0, 10000,Consts.SCREEN_HEIGHT);

    protected Texture setTexture(String fileName){
        return new Texture(Gdx.files.internal(fileName));
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
        boundingBox.setY(boundingBox.getY() - movingVector.y);
        boundingBox.setX(boundingBox.getX() - movingVector.x);
    }
    public boolean outOfMap(){
        return (borderBottom.contains(boundingBox) || borderTop.contains(boundingBox) || borderLeft.contains(boundingBox) || borderRight.contains(boundingBox) );
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
    public float getYMiddle() {
        return getY() + height/2;
    }
    public void setMiddle(float x, float y) {
        boundingBox.setCenter(x, y);
    }
    public void draw(SpriteBatch batch){
        if(!batch.isDrawing()){
            batch.begin();
        }
        batch.draw(texture,boundingBox.getX(),boundingBox.getY(),width,height);
        batch.end();
    }
    public void dispose(){
        texture.dispose();
    }

}
