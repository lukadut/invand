package aplmob.dutkastudencki.spaceinvader.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import aplmob.dutkastudencki.spaceinvader.SpaceInvaders;
import aplmob.dutkastudencki.spaceinvader.game.Config;
import aplmob.dutkastudencki.spaceinvader.game.Renderer;
import aplmob.dutkastudencki.spaceinvader.game.World;
import aplmob.dutkastudencki.spaceinvader.game.handlers.InputHandler;

/**
 * Screen z grą.
 */
public class GameAreaScreen extends AbstractScreen{
    /**
     * Świat.
     */
    private World world;

    /**
     * Renderer.
     */
    private Renderer renderer;

    /**
     * Obsługa dotyku.
     */
    private InputMultiplexer gameInputHandler;

    /**
     * Okno z pauzą.
     */
    private Dialog menuWindow;

    /**
     * Okno z końcem gry.
     */
    private Dialog endGameWindow;

    /**
     * Komunikat na koniec gry.
     */
    private Label endGameScore;

    /**
     * Flaga czy menu jest włączone.
     */
    private boolean menu = false;

    /**
     * Współczynnik rozdzielczości ekranu do rozdzielczości renderowania.
     * @see Config#SCREEN_WIDTH
     * @see Config#SCREEN_HEIGHT
     */
    private float cameraRatioX,cameraRatioY;

    private TextField inputField;
    private String imie;

    /**
     * Konstruktor screena z grą.<br>
     * Dołącza metody {@link GameAreaScreen#showMenu()} i {@link GameAreaScreen#hideMenu()} do pauzowania świata {@link World#setPaused(boolean)}
     * @param game
     */
    public GameAreaScreen(SpaceInvaders game){
        super(game);
        menuWindow = prepareMenuDialog();
        endGameWindow = prepareEndGameDialog();
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

            @Override
            public void endGame() {
                super.endGame();
                endGameWindow.show(stage);
                menu = true;
            }

            @Override
            public void killedPlayer() {
                super.killedPlayer();
                if(getLives()<0){
                    this.endGame();
                    renderer.lastScoreUpdate();
                    endGameScore.setText("You have scored " + world.getScore() + " points.");
                }
            }
        };
        renderer = new Renderer(world,getBatch(),getSkin());

        cameraRatioX = (Gdx.graphics.getWidth()*1.0f)/(Config.SCREEN_WIDTH*1.0f);
        cameraRatioY = (Gdx.graphics.getHeight()*1.0f)/(Config.SCREEN_HEIGHT*1.0f);
        Config.screenRatioX = cameraRatioX;
        Config.screenRatioY = cameraRatioY;
        Config.screenHeight = Gdx.graphics.getHeight();
        Config.screenWidth  = Gdx.graphics.getWidth();

        gameInputHandler = new InputHandler(world).getInputMultiplexer();
        gameInputHandler.addProcessor(stage);

        Gdx.input.setInputProcessor(gameInputHandler);

    }

    /**
     * Tworzy okno dialogowe do pauzy.
     * @return  stworzone okno dialogowe
     */
    private Dialog prepareMenuDialog(){
        Dialog dialog = new Dialog("", getSkin()){
            @Override
            public float getPrefWidth() {
                return Config.SCREEN_WIDTH / 2;
            }

        };

        // anuluje okno, żeby nie działało pod renderem gry
        dialog.cancel();

        Button returnToGame = new TextButton("Hide Menu",getSkin()){
            @Override
            public float getPrefWidth() {
                return Config.SCREEN_WIDTH / 2 - 10;
            }

            @Override
            public float getPrefHeight() {
                return 3*super.getPrefHeight();
            }
        };
        Button quitToMainMenu = new TextButton("Exit to Main Menu",getSkin()){
            @Override
            public float getPrefWidth() {
                return Config.SCREEN_WIDTH / 2 - 10;
            }
            @Override
            public float getPrefHeight() {
                return 3*super.getPrefHeight();
            }
        };

        // odpauzowanie gry przy powrocie
        returnToGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                world.setPaused(false);
            }
        });

        // wyjście z gry
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

    /**
     * Tworzy okno dialogowe do pauzy.
     * @return  stworzone okno dialogowe
     */
    private Dialog prepareEndGameDialog(){
        Dialog dialog = new Dialog("", getSkin()){
            @Override
            public float getPrefWidth() {
                return Config.SCREEN_WIDTH / 2;
            }

        };

        // anuluje okno, żeby nie działało pod renderem gry
        dialog.cancel();

        Button quitToScores = new TextButton("OK",getSkin()){
            @Override
            public float getPrefWidth() {
                return Config.SCREEN_WIDTH / 2 - 10;
            }
            @Override
            public float getPrefHeight() {
                return 3*super.getPrefHeight();
            }
        };

        // wyjście z gry do okna z wynikami
        quitToScores.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (menu) {

                    imie=inputField.getText();
                    //scoreMenager.UpdateScore(imie,world.getScore());
                    game.setScreen(new ScoreScreen(game,imie,world.getScore()));

                    //System.out.println("to jest moment, kiedy powinnobylo sie zaladowac okno z wynikami");
                    dispose();
                }
            }
        });
        endGameScore = new Label("",getSkin());
        inputField = new TextField("anonymous",getSkin());

        dialog.getTitleTable().setSkin(getSkin());
        dialog.getTitleTable().getCells().removeIndex(0);
        dialog.getTitleTable().row();
        dialog.getTitleTable().add("You are dead");
        dialog.getContentTable().padTop(20).row();
        dialog.getContentTable().add(endGameScore);
        dialog.getContentTable().padTop(20).row();
        dialog.getContentTable().add(inputField);
        dialog.getButtonTable().padTop(20).row();

        dialog.getButtonTable().add(quitToScores).padBottom(20).row();


        return dialog;
    }

    @Override
    public void show() {

    }

    /**
     * Wyświetla menu pauzy.
     */
    private void showMenu(){
        menuWindow.show(stage);
        menu = true;
    }

    /**
     * Wyłącza menu pauzy.
     */
    private void hideMenu(){
        menuWindow.cancel();
        menu = false;
    }

    /**
     * Renderowanie sceny.
     * @param delta  czas od poprzednio wyrenderowanej klatki
     */
    @Override
    public void render(float delta) {
        renderer.render(delta);

        // jeśli gra jest spauzowana, a menu nie jest wyświetlone, to je wyświetla
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
