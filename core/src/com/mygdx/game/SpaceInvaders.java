package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.MainMenuScreen;


public class SpaceInvaders extends Game {
    private FPSLogger fpsLogger;

    public SpaceInvaders(){

    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        if( getScreen() == null ) {
                setScreen( new GameAreaScreen( this) );

        }
    }

    @Override
    public void create() {
        fpsLogger = new FPSLogger();

    }

    @Override
    public void render() {
        super.render();
//        fpsLogger.log();


    }
}