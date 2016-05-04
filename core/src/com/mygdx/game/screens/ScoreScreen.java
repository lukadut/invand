package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.SpaceInvaders;

/**
 * Created by VIT on 2016-05-04.
 */
public class ScoreScreen extends AbstractScreen{

    public ScoreScreen(SpaceInvaders game){
        super(game);
    }

    @Override
    public void show() {
        Image image = new Image(new Texture(Gdx.files.internal("background.png")));
        stage.addActor(image);

    }


    @Override
    public void render(float delta) {
        super.render(delta);
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
