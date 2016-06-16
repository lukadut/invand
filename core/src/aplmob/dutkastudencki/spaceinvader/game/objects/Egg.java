package aplmob.dutkastudencki.spaceinvader.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import aplmob.dutkastudencki.spaceinvader.utils.RandomGenerator;

/**
 * Klasa odpowiadająca za zachowanie jajka.
 * @see aplmob.dutkastudencki.spaceinvader.game.objects.GameObject
 *
 * @since 2016-04-30
 * @author Łukasz Dutka
 */
public class Egg extends GameObject{
    /**
     * Konstruktor jajka.
     * @param x położenie na osi X
     * @param y położenie na osi Y
     */
    public Egg(float x, float y){
        width = 15;
        height = 19;
        texture = setTexture("egg.png");
        boundingBox = new Rectangle(x,y,width,height);
        movingVector = new Vector2(0, RandomGenerator.random(1.5f,2));
    }
    /**
     * funkcja ulepszajaca jajko, zwieksza predkosc i dodaje ruch po osi x
     */
    public void EggUpgrade()
    {
        movingVector.x=RandomGenerator.random(-2,2);
        movingVector.y=RandomGenerator.random(1.5f,4);

    }
}
