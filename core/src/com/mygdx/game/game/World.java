package com.mygdx.game.game;

import com.mygdx.game.game.objects.Ship;

/**
 * Created by admin on 2016-04-30.
 */
public class World {
    private Ship ship;

    public World() {
        float halfWidth = (Consts.SCREEN_WIDTH - Consts.SHIP_WIDTH) / 2;
        ship = new Ship(halfWidth,120,Consts.SHIP_WIDTH,Consts.SHIP_HEIGHT);
    }

    public Ship getShip() {
        return ship;
    }
}
