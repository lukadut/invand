package aplmob.dutkastudencki.spaceinvader.screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import aplmob.dutkastudencki.spaceinvader.SpaceInvaders;
import aplmob.dutkastudencki.spaceinvader.game.Config;
import aplmob.dutkastudencki.spaceinvader.game.ScoreMenager;
import java.util.Vector;

/**
 * @since 2016-04-06
 * @author  Witold Studencki
 */

public class ScoreScreen extends AbstractScreen{

    private Table table;
    /**
     * domyślny skin elementów
     */
    private Skin skin;
    /**
     * przycisk powrotu do menu
     */
    private TextButton returnButton;
    /**
     * przycisk testowania dodawania wynikow
     */
    private TextButton testButton;
    /**
     * vector etykiet z punktacja
     */
    private Vector<Label> scoreLabel;
    /**
     * vector etykiet z imionami
     */
    private Vector<Label> nameLabel;

    /**
     * menager punktacji
     */
    private ScoreMenager scoreMenager;

    //  private SpriteBatch batcher;
    //   private OrthographicCamera cam;
    // private BitmapFont font;

    /**
     * konstruktor z parametremi do nowego wyniku na liscie
     */
    public ScoreScreen(SpaceInvaders game,String name, int score){
        super(game);
        scoreMenager = new ScoreMenager();
        scoreMenager.initialize();
        scoreMenager.UpdateScore(name,score);
    }
    /**
     * domyslny konstruktor
     */
    public ScoreScreen(SpaceInvaders game){

        super(game);
        scoreMenager = new ScoreMenager();
    }

    /**
     * definicja wygladu ekranu po wyswietleniu
     */
    @Override
    public void show() {

        defaultBackground(); // inicjalizacja tła
        scoreMenager.initialize();
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top );


        scoreLabel=new Vector<Label>();
        nameLabel= new Vector<Label>();
        Gdx.app.log("density", "" + Gdx.graphics.getDensity());
        Gdx.app.log("stage width", "" + stage.getWidth());
        Gdx.app.log("stage height", "" + stage.getHeight());
        Gdx.app.log("screen width", "" + Gdx.graphics.getWidth());
        Gdx.app.log("screen height", "" + Gdx.graphics.getHeight());

        table.setPosition(0, stage.getHeight()- 2 * Config.PADDING);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        returnButton = new TextButton("      Menu      ", skin);
        testButton = new TextButton("      test      ", skin);


        returnButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

                game.showMenu();

            }
        });



        for(int i=0;i<Config.HSCORE_QUANTITY;i++)
        {
            String Name;
            int Score;
            Name=scoreMenager.getName(i);
            Score=scoreMenager.getPoint(i);

            // scoreLabel.setText(Integer.toString(Score));
            //  scoreLabel
            scoreLabel.add(new Label(Integer.toString(Score),skin));
            // elementAt(i)= new Label(Integer.toString(Score),skin);
            nameLabel.add(new Label (Name,skin));
            //elementAt(i)= new Label (Name,skin);

            table.add(nameLabel.elementAt(i)).padBottom(Config.SCORE_PADDING).padRight(Config.PADDING);
            table.add(scoreLabel.elementAt(i)).padBottom(Config.SCORE_PADDING);
            table.row();
        }
        //  table.add(name).padBottom(Consts.PADDING).padRight(Consts.PADDING);
        //   table.add(score1).padBottom(Consts.PADDING);
        //  table.row();
        table.add(returnButton).padBottom(Config.PADDING);
        table.row();
        //  table.add(testButton).padBottom(Consts.PADDING);

        stage.addActor(background);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
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

        stage.dispose();
        skin.dispose();
     //   super.dispose();

    }
}
