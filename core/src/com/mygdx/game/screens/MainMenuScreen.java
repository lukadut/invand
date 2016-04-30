package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SpaceInvaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class MainMenuScreen extends AbstractScreen{
    Table table;
    private TextButton startButton;
    private TextButton quitButton;
    private SpriteBatch batch;
    private Sprite sprite;
    private Skin skin;

    public MainMenuScreen(SpaceInvaders game){
        super(game);
    }

    @Override
    public void show() {
//        FileHandle fh = Gdx.files.internal("uiskin.json");
//
//        Gdx.app.log("fh.path()", "" + fh.path());
//        File f = fh.file();
//        Gdx.app.log("f", "" + f.getAbsolutePath());
//        Gdx.app.log("f", "" + f.getPath());
//        Gdx.app.log("f", "" + f.toURI());
//        Gdx.app.log("fh",fh.read().toString());
//
//        try {
//            final BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(fh.read()));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                        System.out.println(line);
//            }
//            reader.close();
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);

        Gdx.app.log("density", "" + Gdx.graphics.getDensity());
        Gdx.app.log("stage width", "" + stage.getWidth());
        Gdx.app.log("stage height", "" + stage.getHeight());
        Gdx.app.log("screen width", "" + Gdx.graphics.getWidth());
        Gdx.app.log("screen height", "" + Gdx.graphics.getHeight());

        table.setPosition(0, stage.getHeight()/2);
        startButton = new TextButton("New Game",getSkin());
        quitButton = new TextButton("Quit Game",getSkin());

        table.padTop(30);
        table.add(startButton).padBottom(30);

        table.row();
        table.add(quitButton);
        
        Image image = new Image(new Texture(Gdx.files.internal("background.png")));
        image.setHeight(stage.getHeight());
        image.setWidth(stage.getWidth());

        stage.addActor(image);
        stage.addActor(table);
        

        

//        batch = getBatch();
//        sprite = new Sprite(new Texture(Gdx.files.internal("background.png")));
//        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//        stage.addActor(sprite);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
