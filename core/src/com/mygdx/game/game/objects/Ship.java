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
public class Ship extends  GameObject{
    public Ship(float x, float y){
        texture = new Texture(Gdx.files.internal("ship.png"));
        width = Consts.SHIP_WIDTH;
        height = Consts.SHIP_HEIGHT;
        boundingBox = new Rectangle(x,y,width,y-height);
    }

    public void smoothMove(int screenX, int screenY){
        float xx = getX();
        float middle = xx + 5 *  Gdx.graphics.getDeltaTime() * (screenX/ Config.screenRatioX - (xx+ Consts.HALF_SHIP_WIDTH)) + Consts.HALF_SHIP_WIDTH;
        setMiddle(middle, getYMiddle()-4); // chciałbym wiedzieć, czemu musi być -4 bo inaczej się psuje
    }

}
