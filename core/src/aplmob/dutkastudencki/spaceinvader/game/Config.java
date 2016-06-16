package aplmob.dutkastudencki.spaceinvader.game;

/**
 * Klasa odpowiadająca za zachowanie pocisku.
 * @see aplmob.dutkastudencki.spaceinvader.game.objects.GameObject
 *
 * @since 2016-05-25
 * @author Łukasz Dutka & Witold Studencki
 */
public class Config {
    /**
     * Proporcje szerokości ekranu do {@link Config#SCREEN_WIDTH}
     */
    public static float screenRatioX = 1f;

    /**
     * Proporcje wysokość ekranu do {@link Config#SCREEN_HEIGHT}
     */
    public static float screenRatioY = 1f;

    /**
     * Rzeczywista szerokość ekranu.
     */
    public static float screenWidth = 1f;

    /**
     * Rzeczywista wysokość ekranu.
     */
    public static float screenHeight = 1f;

    /**
     * Wirtualna szerokość ekranu.
     */
    public static final int SCREEN_WIDTH = 480;

    /**
     * Wirtualna wysokość ekranu.
     */
    public static final int SCREEN_HEIGHT = 800;

    /**
     * Szerokość statku.
     */
    public static final int SHIP_WIDTH = 64;

    /**
     * Wysokość statku.
     */
    public static final int SHIP_HEIGHT = 64;

    /**
     * Odstęp od dolnej krawędzi ekranu.
     */
    public static final int EDGE_DISTANCE = 120;

    /**
     * Odstęp w tabelach.
     */
    public static final int PADDING = 30;
    /**
     * Odstęp na liście najlepszych wyników.
     */
    public static final int SCORE_PADDING = 20;

    /**
     * Odstęp od dolnej krawędzi do ruchu bossa.
     */
    public static final int BOSS_BOUNDRY = 250;

    /**
     * Liczba wyświetlanych wyników.
     */
    public static final int HSCORE_QUANTITY = 10;
}
