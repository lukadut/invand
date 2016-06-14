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
    private long lastEggTime = 0;
    /**
     * Wartość życia kurczaka.
     */
    private float healthPoints = 1;
    /**
     * Obiekt odpowiadający za animację.
     */
    private Animation animation;
    /**
     * Tablica przechowująca klatki animacji.
     */
    private TextureRegion[] animationFrames;
    /**
     * Czas do określenia aktualnie wyświetlanej klatki.
     */
    private float stateTime;

    /**
     * Konstruktor kurczaka.
     * @param x położenie na osi X
     * @param y położenie na osi Y
     */
    public Chicken(float x, float y){
        width = 54;
        height = 39;
        texture = setTexture("chicken.png");
        boundingBox = new Rectangle(x,y,width,height);
        makeAnimation(1,8,8);
    }

    /**
     * Stworzenie animacji na podstawie załadowanej tekstury, która zawiera kolejne klatki animacji.
     * @param row liczba wierszy klatek w pliku
     * @param column liczba kolumn klatek w pliku
     * @param framesPerSecond liczba klatek animacji na sekundę
     */
    private void makeAnimation(int row, int column, float framesPerSecond){
        TextureRegion[][] tmp = TextureRegion.split(texture,texture.getWidth()/8,texture.getHeight()/1);
        int index = 0;
        animationFrames = new TextureRegion[row*column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
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
    private TextureRegion getFrame(float deltaTime){
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
        if (System.currentTimeMillis() - lastEggTime > 3000 && RandomGenerator.random(0,1000)<1) {
            lastEggTime = System.currentTimeMillis();
            Egg egg = new Egg(0, getBoundingBox().getY() - getHeight());
            egg.setMiddle(getXMiddle(),getYMiddle());
            return egg;

        }
        return null;
    }
}
