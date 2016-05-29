package com.mygdx.game.game.objects;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by admin on 2016-05-28.
 */
public class Attacks {
    private long lastAttack = 0;
    private GameObject attack;


    public Attacks (){

    }

    public void attack(){
        if(TimeUtils.nanoTime() - lastAttack > 500000000){
            System.out.println("atak");
            lastAttack = TimeUtils.nanoTime();
        }
    }
}
