package aplmob.dutkastudencki.spaceinvader.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import aplmob.dutkastudencki.spaceinvader.SpaceInvaders;

public class MainMenuScreen extends AbstractScreen{
    Table table;
    private TextButton startButton;
    private TextButton quitButton;
    private TextButton scoreButton;

    public MainMenuScreen(SpaceInvaders game){
        super(game);
    }

    @Override
    public void show() {
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);

        Gdx.app.log("density", "" + Gdx.graphics.getDensity());
        Gdx.app.log("stage width", "" + stage.getWidth());
        Gdx.app.log("stage height", "" + stage.getHeight());
        Gdx.app.log("screen width", "" + Gdx.graphics.getWidth());
        Gdx.app.log("screen height", "" + Gdx.graphics.getHeight());

        table.setPosition(0, stage.getHeight() / 2);
        startButton = new TextButton("     New Game     ", getSkin());
        quitButton = new TextButton("     Quit Game     ", getSkin());
        scoreButton = new TextButton("     High Score     ", getSkin());

        startButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

//                startButton.setText("THE END IS COMING!!!");

                game.setScreen(new GameAreaScreen(game));
//                 g.setScreen( new GameScreen());
            }
        });

        quitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                // quitButton.setText("THE END IS COMING!!!");

                Gdx.app.exit();
            }
        });

        // table.padTop(30);
        table.add(startButton).padBottom(30);
        table.row();
        table.add(scoreButton).padBottom(30);
        table.row();
        table.add(quitButton);


        System.out.println(Gdx.files.internal("backgroung").file().getAbsolutePath());
        Image image = new Image(new Texture(Gdx.files.internal("background.png")));
        image.setHeight(stage.getHeight());
        image.setWidth(stage.getWidth());

        stage.addActor(image);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta);
    }
}
