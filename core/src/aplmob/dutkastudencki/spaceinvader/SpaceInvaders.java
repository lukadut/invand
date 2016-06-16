package aplmob.dutkastudencki.spaceinvader;

import com.badlogic.gdx.Game;

import aplmob.dutkastudencki.spaceinvader.game.ScoreMenager;
import aplmob.dutkastudencki.spaceinvader.screens.MainMenuScreen;
import aplmob.dutkastudencki.spaceinvader.screens.GameAreaScreen;
import aplmob.dutkastudencki.spaceinvader.screens.ScoreScreen;

public class SpaceInvaders extends Game {
   // public ScoreMenager scoreMenager;
    public SpaceInvaders(){
   //       scoreMenager = new ScoreMenager();

    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        if( getScreen() == null ) {
                setScreen( new MainMenuScreen( this) );

        }
    }

    @Override
    public void create() {


    }

    @Override
    public void render() {
        super.render();
    }

    /**
     * ustawienie ekranu gry
     */
        public void startGame() {setScreen(new GameAreaScreen(this));}
    /**
     * ustawienie ekranu punktacji
     */
        public void showScore() { setScreen(new ScoreScreen(this));  }
    /**
     * ustawienie ekranu menu
     */
        public void showMenu() {setScreen(new MainMenuScreen(this));}


    }
