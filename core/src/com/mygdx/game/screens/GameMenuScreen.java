package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SpaceInvaders;

public class GameMenuScreen extends AbstractScreen{
    Table table;
    private TextButton startButton;
    private TextButton quitButton;
    private TextButton scoreButton;
    private SpriteBatch batch;
    private Sprite sprite;
    private Skin skin;
    private SpaceInvaders g;

    public GameMenuScreen(SpaceInvaders game){
        super(game);
        this.g = game;

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);

        Gdx.app.log("density", "" + Gdx.graphics.getDensity());
        Gdx.app.log("stage width", "" + stage.getWidth());
        Gdx.app.log("stage height", "" + stage.getHeight());
        Gdx.app.log("screen width", "" + Gdx.graphics.getWidth());
        Gdx.app.log("screen height", "" + Gdx.graphics.getHeight());

        table.setPosition(0, stage.getHeight() / 2);
        startButton = new TextButton("     New Game     ", skin);
        quitButton = new TextButton("     Quit Game     ", skin);
        scoreButton = new TextButton("     High Score     ", skin);

        startButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("START");
//                startButton.setText("THE END IS COMING!!!");
                // initialize(new MyGdxGame(), config);

//                g.setScreen(new GameAreaScreen(g));
//                 g.setScreen( new GameScreen());
            }
        });

        quitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                // quitButton.setText("THE END IS COMING!!!");
                System.out.println("KONIEC");
//                Gdx.app.exit();
            }
        });

        // table.padTop(30);
        table.add(startButton).padBottom(30);
        table.row();
        table.add(scoreButton).padBottom(30);
        table.row();
        table.add(quitButton);

        stage.addActor(table);
    }

    @Override
    public void show() {


    }

    public Stage getStage(){
        return stage;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
