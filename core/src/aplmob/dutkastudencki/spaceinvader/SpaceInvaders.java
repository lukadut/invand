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

        public void startGame() {setScreen(new GameAreaScreen(this));}

        public void showScore() { setScreen(new ScoreScreen(this));  }

        public void showMenu() {setScreen(new MainMenuScreen(this));}
     //   public void ScoreUpdate(String name,int score)
     //       {
      //          scoreMenager.UpdateScore(name,score);
       //     }

    }
