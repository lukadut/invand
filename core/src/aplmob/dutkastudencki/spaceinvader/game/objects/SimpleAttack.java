package aplmob.dutkastudencki.spaceinvader.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Klasa odpowiadająca za zachowanie pocisku.
 * @see aplmob.dutkastudencki.spaceinvader.game.objects.GameObject
 *
 * @since 2016-04-30
 * @author Łukasz Dutka
 */
public class SimpleAttack extends GameObject {
    /**
     * Obrażenia zadawane przez pocisk.
     */
    private float damagePoints = 1;

    /**
     * Konstruktor pocisku.
     * @param x położenie na osi X
     * @param y położenie na osi Y
     */
    public SimpleAttack(float x, float y){
        width = 6;
        height = 16;
        texture = setTexture("atak.png");
        boundingBox = new Rectangle(x,y,width,height);
        movingVector = new Vector2(0, -5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        super.move();
        movingVector.y -= 0.01f;
    }

    /**
     *
     * @return  obrażenia zadawane przez pocisk
     */
    public float getDamagePoints(){
        return damagePoints;
    }
}
