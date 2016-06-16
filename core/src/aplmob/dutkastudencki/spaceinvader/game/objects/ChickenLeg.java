package aplmob.dutkastudencki.spaceinvader.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import aplmob.dutkastudencki.spaceinvader.utils.RandomGenerator;

/**
 * @since 2016-06-09
 * @author  Witold Studencki
 */
public class ChickenLeg  extends GameObject{

    /**
     * Konstruktor nozki
     * @param x położenie na osi X
     * @param y położenie na osi Y
     */
    public ChickenLeg(float x, float y){
        width = 28;
        height = 20;
        texture = setTexture("chickenLeg.png");
        boundingBox = new Rectangle(x,y,width,height);
        movingVector = new Vector2(RandomGenerator.random(-2,2), RandomGenerator.random(3, 6));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(){
        super.move();
        if(boundingBox.overlaps(borderLeft) || boundingBox.overlaps(borderRight))
            movingVector.x=-movingVector.x + (RandomGenerator.random(-0.2f,0.2f));
    }

}