package aplmob.dutkastudencki.spaceinvader.game.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import aplmob.dutkastudencki.spaceinvader.game.World;

/**
 * Klasa odpowiadająca za obsługę gestów.
 *
 * @since 2016-05-04
 * @author Łukasz Dutka
 */
public class InputHandler implements GestureDetector.GestureListener, InputProcessor{
    /**
     * Świat.
     */
    private World world;

    /**
     * Przekazuje zdarzenia do kolejnych {@link InputProcessor}
     */
    private InputMultiplexer inputMultiplexer;

    /**
     * Detektor gestów.
     */
    private GestureDetector gestureDetector;

    /**
     * Timer do wywołwania zdarzeń.
     */
    private Timer timer;

    /**
     * Zadanie auto ataku wywoływane co 0.5 sekundy.
     */
    private Timer.Task autoAttackTask;

    /**
     * Wskaźnik do auto ataku.
     */
    private int autoAttackPointer = -1;

    /**
     * Konstruktor do klasy obsługującej gesty.
     * @param world
     */
    public InputHandler(final World world) {
        inputMultiplexer = new InputMultiplexer();
        gestureDetector = new GestureDetector(this);
        inputMultiplexer.addProcessor(gestureDetector);
        inputMultiplexer.addProcessor(this);
        this.world = world;


        timer = Timer.instance();
        autoAttackTask = new Timer.Task() {
            @Override
            public void run() {
                world.attack();
            }
        };
        timer.scheduleTask(autoAttackTask,1,0.5f);
        timer.stop();
    }

    /**
     *
     * @return zwraca multiplexer do przekazywania zdarzeń
     */
    public InputMultiplexer getInputMultiplexer(){

        return inputMultiplexer;
    }

    /**
     * Funkcja do sprawdzania nazw wywoływanych gestów.
     * @param gesture  gest
     */
    private void gestureName(String gesture) {
        //System.out.println(s);
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        gestureName("panStop");
        return false;
    }

    /**
     * Przypisanie autoataku do 1. wciśniętego wskaźnika.
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(autoAttackPointer == -1) {
            timer.start();
            autoAttackPointer = pointer;
        }
        gestureName("public boolean touchDown(float x, float y, int pointer, int button)");
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        gestureName("public boolean touchDown(int screenX, int screenY, int pointer, int button)");
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        gestureName("public boolean tap");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        gestureName("public boolean longPress");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        gestureName("public boolean fling");
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        gestureName("public boolean pan");
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        gestureName("public boolean zoom");
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        gestureName("public boolean pinch");
        return true;
    }

    /**
     * Włączenie i wyłączenie pauzy na klawiszu powrotu.
     */
    @Override
    public boolean keyDown(int keycode) {
        gestureName("public boolean keyDown");
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            world.setPaused(!world.isPaused());
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        gestureName("public boolean keyUp");
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        gestureName("public boolean keyTyped");
        return false;
    }

    /**
     * Wyłączenie autoataku po zaprzestaniu naciskania zapamiętanego wskaźnika.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        gestureName("public boolean touchUp");
        if(pointer == autoAttackPointer) {
            timer.stop();
            autoAttackPointer = -1;
        }
        else{
            world.attack();
        }
        return false;
    }

    /**
     * Poruszanie statkiem przez przytrzymanie wskaźnika.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        gestureName("public boolean touchDragged");
        world.moveShip(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        gestureName("public boolean mouseMoved");
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        gestureName("public boolean scrolled");
        return true;
    }
}
