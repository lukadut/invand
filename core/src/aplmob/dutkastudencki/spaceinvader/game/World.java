package aplmob.dutkastudencki.spaceinvader.game;

import com.badlogic.gdx.utils.Array;

import aplmob.dutkastudencki.spaceinvader.game.objects.Boss;
import aplmob.dutkastudencki.spaceinvader.game.objects.Chicken;
import aplmob.dutkastudencki.spaceinvader.game.objects.GameObject;
import aplmob.dutkastudencki.spaceinvader.game.objects.Ship;
import aplmob.dutkastudencki.spaceinvader.game.objects.SimpleAttack;

/**
 * Klasa świata gry.
 *
 * @since 2016-04-30
 * @author Łukasz Dutka (main), Witold Studencki (improvments)
 */
public class World {
    /**
     * Statek.
     */
    private Ship ship;

    /**
     * Lista istniejących kurczaków.
     */
    private Array<GameObject> chickens;

    /**
     * Lista istniejących jajek.
     */
    private Array<GameObject> eggs;

    /**
     * Lista istniejących pocisków.
     */
    private Array<GameObject> bullets;

    private Array<GameObject> legs;

//    private Array<GameObject> powerUps;

    /**
     * Liczba żyć.
     */
    private int lives = 3;

    /**
     * Wynik gry.
     */
    private int score = 0;

    /**
     * Flaga do sprawdzania czy gra jest spauzowana.
     */
    private boolean paused = false;

    private int level=0;

    /**
     * Konstruktor.
     */
    public World() {
        float halfWidth = (Config.SCREEN_WIDTH - Config.SHIP_WIDTH) / 2;
        ship = new Ship(halfWidth,Config.EDGE_DISTANCE);
        chickens = new Array<GameObject>();
        eggs = new Array<GameObject>();
        bullets = new Array<GameObject>();
        legs = new Array<GameObject>();
//        powerUps = new Array<GameObject>();
        prepareGame();
    }

    /**
     *
     * @return  {@link World#ship} statek
     */
    public Ship getShip() {
        return ship;
    }

    /**
     *
     * @return  {@link World#chickens} lista kurczaków
     */
    public Array<GameObject> getChickens(){
        return chickens;
    }

    /**
     *
     * @return  {@link World#eggs} lista jajek
     */
    public Array<GameObject> getEggs(){
        return eggs;
    }

    /**
     *
     * @return  {@link World#bullets} lista pocisków
     */
    public Array<GameObject> getBullets(){
        return bullets;
    }

    public Array<GameObject> getLegs(){
        return legs;
    }

//    public Array<GameObject> getPowerUps(){
//        return powerUps;
//    }

    /**
     *
     * @return  liczba żyć
     */
    public int getLives(){return lives;}

    /**
     *
     * @return  wynik gry
     */
    public int getScore() {return score;}

    /**
     *
     * @return  level gry
     */
    public int getLevel() {return level;}

    public void levelUp() {level++;}
    /**
     * Przygotowanie planszy z grą.
     */
    protected void prepareGame(){

        spawnBoss();
     //   spawnChickens();
    }

    /**
     * Tworzenie kurczaków na planszy.
     */
    public void spawnChickens() {
        for (int y = 70; y < 350; y=y+70) {
            for (int x = 0; x < Config.SCREEN_WIDTH; x = x + 61) {
                Chicken chicken = new Chicken(x,Config.SCREEN_HEIGHT-y,level);
                chickens.add(chicken);
            }
        }
    }

    public void spawnBoss()
    {
        Boss Boss = new Boss(Config.SCREEN_WIDTH/2,Config.SCREEN_HEIGHT-200,level);
        chickens.add(Boss);
    }
    /**
     * Funkcja wywoływana, kiedy zostanie zabity kurczak.
     */
    public void killedChicken(boolean isBoss){
        if(isBoss)
            score+=100;
        else
            score++;

    }

    public void killedLeg()
    {
        score+=10;
    }
    /**
     * Funkcja wywoływana, kiedy gracz zostanie zabity.
     */
    public void killedPlayer(){
        //score -= 100;
        lives -= 1;
    }

    /**
     *
     * @return  flaga czy gra jest spauzowana
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Pauzowanie gry.
     * @param paused  ustawia flagę pauzy
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }


    /**
     * Wywoływane na koniec gry
     */
    public void endGame(){
        paused = true;
    }

    /**
     * Rusza statek do pozycji (x,y), jeśli gra nie jest spauzowana.
     * @param screenX  pozycja X
     * @param screenY  pozycja Y
     */
    public void moveShip(int screenX, int screenY){
        if(paused){
            return;
        }
        ship.smoothMove(screenX,screenY);
    }

    /**
     * Wywołanie ataku.
     */
    public void attack(){
        if(paused){
            return;
        }
        GameObject bullet = new SimpleAttack(0,Config.EDGE_DISTANCE + Config.SHIP_HEIGHT);
        try {
            float x = ship.getXMiddle();
            float y = ship.getYMiddle();
            bullet.setMiddle(x, y);
            bullets.add(bullet);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Funkcja przygotowująca obiekt do zniszczenia.
     */
    public void dispose() {
        ship.dispose();
        for (GameObject gameObject : chickens) {
            gameObject.dispose();
        }
        for (GameObject gameObject : eggs) {
            gameObject.dispose();
        }
        for (GameObject gameObject : legs) {
                gameObject.dispose();
        }
        for (GameObject gameObject : bullets) {
            gameObject.dispose();
        }
        chickens.clear();
        eggs.clear();
        bullets.clear();
    }
}
