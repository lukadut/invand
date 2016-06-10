package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.game.Config;
import com.mygdx.game.game.Consts;
import com.mygdx.game.game.Renderer;
import com.mygdx.game.game.World;
import com.mygdx.game.game.handlers.InputHandler;

public class GameAreaScreen extends AbstractScreen{
    private World world;
    private Renderer renderer;
    private InputMultiplexer gameInputHandler;
    private Dialog menuWindow;
    private boolean menu = false;

    private Vector3 touchPos;
    private float cameraRatioX,cameraRatioY;

    public GameAreaScreen(SpaceInvaders game){
        super(game);
        menuWindow = prepareDialogWindow();
        Gdx.input.setCatchBackKey(true);
        world = new World(){
            @Override
            public void setPaused(boolean paused) {
                super.setPaused(paused);
                if(paused){
                    showMenu();
                }
                else {
                    hideMenu();
                }
            }
        };
        renderer = new Renderer(world,getBatch(),getSkin());
        cameraRatioX = (Gdx.graphics.getWidth()*1.0f)/(Consts.SCREEN_WIDTH*1.0f);
        cameraRatioY = (Gdx.graphics.getHeight()*1.0f)/(Consts.SCREEN_HEIGHT*1.0f);
        Config.screenRatioX = cameraRatioX;
        Config.screenRatioY = cameraRatioY;
        gameInputHandler = new InputHandler(cameraRatioX,cameraRatioY,world).get();
        gameInputHandler.addProcessor(stage);


        touchPos = new Vector3(); // stare do ruchu
        renderer.getCamera().unproject(touchPos); // stare do ruchu

        System.out.println("screen height " + Consts.SCREEN_WIDTH);
        System.out.println("camera ratio " + cameraRatioX);



        Gdx.input.setInputProcessor(gameInputHandler);

    }

    private Dialog prepareDialogWindow(){
        Dialog dialog = new Dialog("", getSkin()){
            @Override
            public float getPrefWidth() {
                return Consts.SCREEN_WIDTH / 2;
            }

        };
        dialog.cancel();
        Button returnToGame = new TextButton("Hide Menu",getSkin()){
            @Override
            public float getPrefWidth() {
                return Consts.SCREEN_WIDTH / 2 - 10;
            }

            @Override
            public float getPrefHeight() {
                return 3*super.getPrefHeight();
            }
        };
        Button quitToMainMenu = new TextButton("Exit to Main Menu",getSkin()){
            @Override
            public float getPrefWidth() {
                return Consts.SCREEN_WIDTH / 2 - 10;
            }
            @Override
            public float getPrefHeight() {
                return 3*super.getPrefHeight();
            }
        };

        returnToGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                world.setPaused(false);
            }
        });

        quitToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (menu) {
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                }
            }
        });
        dialog.getTitleTable().setSkin(getSkin());
        dialog.getTitleTable().getCells().removeIndex(0);
        dialog.getTitleTable().row();
        dialog.getTitleTable().add("Menu");
        dialog.getButtonTable().padTop(20).row();
        dialog.getButtonTable().add(returnToGame).padBottom(20).row();
        dialog.getButtonTable().add(quitToMainMenu).padBottom(20).row();

        return dialog;
    }

    @Override
    public void show() {

    }

    private void showMenu(){
        menuWindow.show(stage);
        menu = true;
    }

    private void hideMenu(){
        menuWindow.cancel();
        menu = false;
    }


    @Override
    public void render(float delta) {
        renderer.render(delta);
        if(world.isPaused()){
            if(!menu){
                showMenu();
            }
            super.render(delta);
        }
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
        world.dispose();
        super.dispose();
    }
}
