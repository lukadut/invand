package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.SpaceInvaders;

public class MainMenuScreen extends AbstractScreen{
    Table table;
    public MainMenuScreen(SpaceInvaders game){
        super(game);
    }

    @Override
    public void show() {
//        Skin skin;// = new Skin(Gdx.files.internal("uiskin.json"));
//        FileHandle fileHandle = Gdx.files.internal( "uiskin.json" );
//        FileHandle atlasFile = fileHandle.sibling("uiskin.atlas");
//        if (atlasFile.exists()) {
//            skin = new Skin(atlasFile);
//            Gdx.app.log("MyGame", "atlas file is loaded");
//            skin.addRegions(new TextureAtlas(atlasFile));
//        } else {
//            Gdx.app.log("MyGame", "atlas file is NOT loaded");
//        }
//        skin = new Skin(atlasFile);
////        skin = new Skin( skinFile );
//        table = new Table(skin);
//        Table table = super.getTable();
//
//        table.add("jeden");
//        table.add("dwa'");
//
//        TextButton startGameButton = new TextButton("start",getSkin());


        Table table = super.getTable();
        table.add( "Welcome to Tyrian for Android!" ).spaceBottom( 50 );
        table.row();

        // register the button "start game"
        TextButton startGameButton = new TextButton( "Start game", getSkin() );

        table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();

        // register the button "options"
        TextButton optionsButton = new TextButton( "Options", getSkin() );

        table.add( optionsButton ).uniform().fill().spaceBottom( 10 );
        table.row();

        // register the button "high scores"
        TextButton highScoresButton = new TextButton( "High Scores", getSkin() );

        table.add( highScoresButton ).uniform().fill();
    }


}
