package aplmob.dutkastudencki.spaceinvader.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import aplmob.dutkastudencki.spaceinvader.utils.RandomGenerator;

/**
 * @since 2016-06-14
 * @author  Witold Studencki
 */
public class Boss extends Chicken {


    /**
     * Konstruktor bossa
     * @param x położenie na osi X
     * @param y położenie na osi Y
     * @param lvl poziom na ktorym zostal przywolany
     */
    public Boss (float x, float y,int lvl){
        super(x, y,lvl);
        width = 108;
        height = 78;
        level = lvl;
       // texture.dispose();
       // texture = setTexture("kurczakWitkaB.png");


        boundingBox = new Rectangle(x,y,width,height);

        isBoss=true;
        healthPoints = 10+ 2*lvl;
        movingVector = new Vector2(RandomGenerator.random(-2, 2),RandomGenerator.random(-2, 2) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move()
    {
        super.move();
        if(boundingBox.overlaps(borderLeft) || boundingBox.overlaps(borderRight))
        movingVector.x=-movingVector.x + (RandomGenerator.random(-0.2f,0.2f));
        if(boundingBox.overlaps(borderTop) || boundingBox.overlaps(borderBottom))
        movingVector.y=-movingVector.y + (RandomGenerator.random(-0.2f,0.2f));


    }
    /**
     * Nadpisana metoda rzucania, zwiększona częstotliwość
     */
    public Egg throwEgg(){
        if (RandomGenerator.random(0,100-level)<1) {
            lastEggTime = System.currentTimeMillis();
            Egg egg = new Egg(0, getBoundingBox().getY() - getHeight());
            egg.setMiddle(getXMiddle(),getYMiddle());
            egg.EggUpgrade();
            return egg;

        }
        return null;
    }
}
