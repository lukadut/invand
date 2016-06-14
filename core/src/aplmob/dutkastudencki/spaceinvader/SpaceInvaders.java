package aplmob.dutkastudencki.spaceinvader;

import com.badlogic.gdx.Game;
import aplmob.dutkastudencki.spaceinvader.screens.MainMenuScreen;


public class SpaceInvaders extends Game {
    public SpaceInvaders(){

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
}