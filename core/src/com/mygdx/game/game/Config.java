package com.mygdx.game.game;

/**
 * Created by admin on 2016-05-25.
 */
public class Config {
    private static Config config = null;
    private Config(){}
    public static Config get(){
        config = config == null? new Config() : config;
        return config;
    }
    public static float screenRatioX = 1f;
    public static float screenRatioY = 1f;
}
