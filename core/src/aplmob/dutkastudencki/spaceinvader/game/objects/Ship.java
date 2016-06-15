package aplmob.dutkastudencki.spaceinvader.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import aplmob.dutkastudencki.spaceinvader.game.Config;

/**
 * Klasa odpowiadająca za zachowanie statku.
 * @see aplmob.dutkastudencki.spaceinvader.game.objects.GameObject
 *
 * @since 2016-04-30
 * @author Łukasz Dutka
 */
public class Ship extends  GameObject{
    /**
     * Pół szerokości statku.
     */
    private int halfWidth;

    /**
     * Pół wysokości statku.
     */
    private int halfHeight;

    /**
     * Konstruktor statku.
     * @param x położenie na osi X
     * @param y położenie na osi Y
     */
    public Ship(float x, float y){
        texture = setTexture("ship.png");
        width = Config.SHIP_WIDTH;
        height = Config.SHIP_HEIGHT;
        halfHeight = height/2;
        halfWidth = width/2;
        boundingBox = new Rectangle(x,y,width,height);
    }

    /**
     * Porusza statek z prędkością proporcjonalną do odległości miejsca docelowego.
     * @param screenX  docelowe miejsce X
     * @param screenY  <s>docelowe miejsce Y</s> nie używane
     */
    public void smoothMove(int screenX, int screenY){
        float xx = getX();
//        float yy = getY();
        float delta = Gdx.graphics.getDeltaTime();
        float middleX = xx + 5 *  delta * (screenX/ Config.screenRatioX - (xx+ halfWidth)) + halfWidth;
//        float middleY = ( yy + 5 *  delta * ((Config.screenHeight -  screenY)/ Config.screenRatioY - (yy+ halfHeight)) + halfHeight);
        setMiddle(middleX, getYMiddle());
    }

}
