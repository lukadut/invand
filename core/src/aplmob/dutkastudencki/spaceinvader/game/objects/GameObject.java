package aplmob.dutkastudencki.spaceinvader.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import aplmob.dutkastudencki.spaceinvader.game.Config;


/**
 * Klasa odpowiadająca za podstawowe zachowania obiektów w grze.
 *
 * @since 2016-05-28
 * @author Łukasz Dutka
 */
public class GameObject {
    /**
     * Szerokość obiektu.<br>
     * Do ustawienia w konstruktorze danego obiektu.
     */
    protected int width     = 0;

    /**
     * Wysokość obiektu.<br>
     * Do ustawienia w konstruktorze danego obiektu.
     */
    protected int height    = 0;

    /**
     * Prostokątny kształt tworzący obiekt.<br>
     * Do ustawienia w konstruktorze danego obiektu.
     */
    protected Rectangle boundingBox;

    /**
     * Tekstura, jaką zostanie wyświetlony obiekt.<br>
     * Do ustawienia w konstruktorze danego obiektu.
     */
    protected Texture texture;

    /**
     * Wektor 2D, o jaki zostanie obiekt przesunięty po użyciu {@link GameObject#move()}.
     */
    protected Vector2 movingVector = new Vector2(0,0);

    /**
     * Kształt powyżej górnej krawędzi ekranu.
     */
    protected static Rectangle borderTop = new Rectangle(0, Config.SCREEN_HEIGHT,Config.SCREEN_WIDTH,10000);
    /**
     * Kształt poniżej dolnej krawędzi ekranu.
     */
    protected static Rectangle borderBottom = new Rectangle(0, -10000,Config.SCREEN_WIDTH,10000);
    /**
     * Kształt za lewą krawędzią ekranu.
     */
    protected static Rectangle borderLeft = new Rectangle(-10000, 0,10000,Config.SCREEN_HEIGHT);
    /**
     * Kształt za prawą krawędzią ekranu.
     */
    protected static Rectangle borderRight = new Rectangle(Config.SCREEN_WIDTH, 0, 10000,Config.SCREEN_HEIGHT);

    /**
     * Wczytanie tekstury z pliku o zadanej nazwie.<br>
     *     Plik powinien być w katalogu {<i>katalog z projektem</i>}/android/assets.
     * @param fileName  nazwa pliku
     * @return  tekstura
     */
    protected Texture setTexture(String fileName){
        return new Texture(Gdx.files.internal(fileName));
    }

    /**
     *
     * @return  szerokość obiektu
     */
    public float getWidth() {
        return width;
    }

    /**
     *
     * @return  wysokość obiektu
     */
    public float getHeight() {
        return height;
    }

    /**
     *
     * @return  prostokątny kształt obiektu
     */
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    /**
     *
     * @return  tekstura obiektu
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Porusza obiekt o wektor {@link GameObject#movingVector}.
     */
    public void move(){
        boundingBox.setY(boundingBox.getY() - movingVector.y);
        boundingBox.setX(boundingBox.getX() - movingVector.x);
    }

    /**
     *
     * @return  <i>true</i> - jeśli obiekt wyszedł poza ekran<br>
     *     <i>false</i> - jeśli obiekt nie wyszedł poza ekran
     */
    public boolean outOfMap(){
        return (borderBottom.contains(boundingBox) || borderTop.contains(boundingBox) || borderLeft.contains(boundingBox) || borderRight.contains(boundingBox) );
    }
    public boolean outOfX(){
        return ( borderLeft.contains(boundingBox) || borderRight.contains(boundingBox) );
    }
    public boolean outOfY(){
        return (borderBottom.contains(boundingBox) || borderTop.contains(boundingBox)  );
    }
    /**
     *
     * @return  położenie X
     */
    public float getX() {
        return boundingBox.getX();
    }

    /**
     *
     * @return  położenie X środka obiektu
     */
    public float getXMiddle() {
        return getX() + width/2;
    }

    /**
     *
     * @return  położenie Y
     */
    public float getY() {
        return boundingBox.getY();
    }

    /**
     *
     * @return  położenie Y środka obiektu
     */
    public float getYMiddle() {
        return getY() + height/2;
    }

    /**
     * Ustawia obiekt względem środka w punkcie (x, y).
     * @param x  położenie X
     * @param y  położenie Y
     */
    public void setMiddle(float x, float y) {
        boundingBox.setCenter(x, y);
    }

    /**
     * Wywołanie rysowania obiektu.
     * @param batch obiekt {@link SpriteBatch} do rysowania na scenie
     */
    public void draw(SpriteBatch batch){
        if(!batch.isDrawing()){
            batch.begin();
        }
        batch.draw(texture,boundingBox.getX(),boundingBox.getY(),width,height);
        batch.end();
    }

    /**
     * Funkcja przygotowująca obiekt do zniszczenia.
     */
    public void dispose(){
        texture.dispose();
    }

}
