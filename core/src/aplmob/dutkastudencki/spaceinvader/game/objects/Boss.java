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
     */
    public Boss (float x, float y,int lvl){
        super(x, y,lvl);
        width = 108;
        height = 78;
       // texture.dispose();
       // texture = setTexture("kurczakWitkaB.png");


        boundingBox = new Rectangle(x,y,width,height);

        isBoss=true;
        healthPoints = 10+ 2*lvl;
        movingVector = new Vector2(RandomGenerator.random(-2, 2),RandomGenerator.random(-2, 2) );
    }

    /**
     * zmiana kierunku poruszania się w przypadku osiągniecia granicy
     * @param where kierunek zmiany, true = os X, false = os Y
     */
    public void limitReached(boolean where)
    {

        if(where)
        movingVector.x=-movingVector.x;
        else
        movingVector.y=-movingVector.y;


    }
    /**
     * Nadpisana metoda rzucania, zwiększona częstotliwość
     */
    public Egg throwEgg(int lvl){
        if (RandomGenerator.random(0,100-lvl)<1) {
            lastEggTime = System.currentTimeMillis();
            Egg egg = new Egg(0, getBoundingBox().getY() - getHeight());
            egg.setMiddle(getXMiddle(),getYMiddle());
            egg.EggUpgrade();
            return egg;

        }
        return null;
    }
}