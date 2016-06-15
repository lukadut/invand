package aplmob.dutkastudencki.spaceinvader.utils;

import java.util.Random;

/**
 * Created by admin on 2016-04-30.
 */
public class RandomGenerator {
    static private Random r;
    static {
        r = new Random();
    }
    public static float random(float min, float max){
        return r.nextFloat()*(max-min) + min;
    }

}
