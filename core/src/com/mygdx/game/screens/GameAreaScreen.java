package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.SpaceInvaders;

public class GameAreaScreen extends AbstractScreen{

    public GameAreaScreen(SpaceInvaders game){
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
