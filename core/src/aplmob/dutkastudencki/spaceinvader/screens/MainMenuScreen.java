package aplmob.dutkastudencki.spaceinvader.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;


import aplmob.dutkastudencki.spaceinvader.SpaceInvaders;
import aplmob.dutkastudencki.spaceinvader.game.Config;



public class MainMenuScreen extends AbstractScreen{
    /**
     * tabela z zawartościa menu
     */
    Table table;
    /**
     * przycisk rozpoczęcia gry
     */
    private TextButton startButton;
    /**
     * przycisk wyjścia z gry
     */
    private TextButton quitButton;
    /**
     *przycisk wyświetlenia wyników
     */
    private TextButton scoreButton;
    /**
     * Napis z tytułem gry
     */
    private Label nameLabel;

    /**
     * domyślny skin elementów
     */
    private Skin skin;

    public MainMenuScreen(SpaceInvaders game){
        super(game);
    }

    /**
     * definicja wyglądu okna przy wyświetlaniu
     */
    @Override
    public void show() {
//

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);

        Gdx.app.log("density", "" + Gdx.graphics.getDensity());
        Gdx.app.log("stage width", "" + stage.getWidth());
        Gdx.app.log("stage height", "" + stage.getHeight());
        Gdx.app.log("screen width", "" + Gdx.graphics.getWidth());
        Gdx.app.log("screen height", "" + Gdx.graphics.getHeight());

        table.setPosition(0, stage.getHeight() / 4 + stage.getHeight() / 2);
        startButton = new TextButton("     New Game     ", skin);
        quitButton = new TextButton("     Quit Game     ", skin);
        scoreButton = new TextButton("     High Score     ", skin);
        nameLabel = new Label("   Chicken Invaders    ", skin);

        startButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

                // odpalenie funkcji zmieniajacej screen
                game.startGame();


            }
        });

        scoreButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

                // odpalenie punktacji
                game.showScore();

            }
        });
        quitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

                // wylacza aplikacje, m
                Gdx.app.exit();
            }
        });

        // tabela menu z buttonami jako wierszami
        table.add(nameLabel).padBottom(Config.PADDING);
        table.row();
        table.add(startButton).padBottom(Config.PADDING);
        table.row();
        table.add(scoreButton).padBottom(Config.PADDING);
        table.row();
        table.add(quitButton);


        defaultBackground(); // inicjalizacja tla

        stage.addActor(background);
        stage.addActor(table);


        Gdx.input.setInputProcessor(stage);

    }
    /**
     * zwalnianie zasobow
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        super.dispose();
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
    public void render(float delta) {
        super.render(delta);
    }
}
