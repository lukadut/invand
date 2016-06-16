package aplmob.dutkastudencki.spaceinvader.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import aplmob.dutkastudencki.spaceinvader.utils.RandomGenerator;

/**
 * Klasa odpowiadająca za zachowanie kurczaka.
 * @see aplmob.dutkastudencki.spaceinvader.game.objects.GameObject
 *
 * @since 2016-04-30
 * @author Łukasz Dutka
 */
public class Chicken extends GameObject{
    /**
     * Czas w milisekundach czasu uniksowego od rzucenia ostatniego jajka.
     */
    protected long lastEggTime = 0;
    /**
     * Wartość życia kurczaka.
     */
    protected float healthPoints = 1;
    /**
     * Obiekt odpowiadający za animację.
     */
    protected Animation animation;
    /**
     * Tablica przechowująca klatki animacji.
     */
    protected TextureRegion[] animationFrames;
    /**
     * Czas do określenia aktualnie wyświetlanej klatki.
     */
    protected float stateTime;

    /**
     *poziom na którym został zespawnowany kurczak
     */
    protected int level;

    /**
     * bool informujacy o jakości kurczaka
     */
    public Boolean isBoss=false;

    /**
     * Konstruktor kurczaka.
     * @param x położenie na osi X
     * @param y położenie na osi Y
     */
    public Chicken(float x, float y, int lvl){
        width = 54;
        height = 39;
        texture = setTexture("kurczakWitka.png");
        boundingBox = new Rectangle(x,y,width,height);
        healthPoints += (int)(lvl*0.2);
        level= lvl;
        makeAnimation(1,8,8);
    }

    /**
     * Stworzenie animacji na podstawie załadowanej tekstury, która zawiera kolejne klatki animacji.
     * @param row liczba wierszy klatek w pliku
     * @param column liczba kolumn klatek w pliku
     * @param framesPerSecond liczba klatek animacji na sekundę
     */
    protected void makeAnimation(int row, int column, float framesPerSecond){
        TextureRegion[][] tmp = TextureRegion.split(texture,texture.getWidth()/8,texture.getHeight()/1);
        int index = 0;
        animationFrames = new TextureRegion[row*column];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 8; j++) {
                animationFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(1.0f/framesPerSecond, animationFrames);
        stateTime = RandomGenerator.random(0,1);
    }

    /**
     * Funkcja zwracająca aktualną klatkę animacji.
     * @param deltaTime czas od ostatnio wygenerowanej klatki sceny
     * @return Tekstura w postaci {@link TextureRegion}
     */
    protected TextureRegion getFrame(float deltaTime){
        stateTime += deltaTime;
        return animation.getKeyFrame(stateTime,true);
    }

    /**
     * Wywołanie rysowania animacji.
     * @param batch obiekt {@link SpriteBatch} do rysowania na scenie
     * @param deltaTime czas od poprzedniej klatki sceny
     */
    public void animate(SpriteBatch batch, float deltaTime){
        if(!batch.isDrawing()){
            batch.begin();
        }
        batch.draw(getFrame(deltaTime),boundingBox.getX(),boundingBox.getY(),width,height);
        batch.end();
    }

//    public float getHealthPoints(){
//        return healthPoints;
//    }

    /**
     * Zadanie obrażeń kurczakowi.
     * @param damage wartość obrażeń
     * @return pozostała wartość życia
     */
    public float applyDamage(float damage){
        this.healthPoints -= damage;
        return this.healthPoints;
    }

    /**
     * Rzucenie jajkiem.<br>
     * Wyrzucenie jajka odbywa się z odstępem minimum 3 sekund, następnie co wywołanie ma 0.1% szans na powodzenie.
     * @return przy powodzeniu wyrzuca nowe jajko w postaci {@link Egg}, w przeciwnym razie zwraca <i>null</i>
     */
    public Egg throwEgg(){
        if (System.currentTimeMillis() - lastEggTime > 3000-10*level && RandomGenerator.random(0,1000-10*level)<1) {
            lastEggTime = System.currentTimeMillis();
            Egg egg = new Egg(0, getBoundingBox().getY() - getHeight());
            egg.setMiddle(getXMiddle(),getYMiddle());
            return egg;

        }
        return null;
    }
    /**
     * funkcja analogiczna do rzucajacej jajkiem, 25$ na rzucenie udkiem po śmierci
     * @return przy powodzeniu wyrzuca nowe udko, w przeciwnym wypadku zwraca <i>null</i>
     */
    public ChickenLeg throwLeg(){
        if (RandomGenerator.random(0,5)<1) {

            ChickenLeg leg = new ChickenLeg(0, getBoundingBox().getY() - getHeight());
            leg.setMiddle(getXMiddle(),getYMiddle());
            return leg;

        }
        return null;
    }

}
