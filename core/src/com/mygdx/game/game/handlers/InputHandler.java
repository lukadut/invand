package com.mygdx.game.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.game.Consts;
import com.mygdx.game.game.World;
import com.mygdx.game.game.objects.Ship;

/**
 * Created by admin on 2016-05-04.
 */
public class InputHandler implements GestureDetector.GestureListener, InputProcessor{
//    private Ship ship;
    private World world;

    private InputMultiplexer im ;
    private GestureDetector gd ;

    float scaleX, scaleY;

    private Timer timer;
    private Timer.Task task;
    private int autoAttackPointer = -1;

    public InputHandler(float scaleX, float scaleY, final World world) {
        im = new InputMultiplexer();
        gd = new GestureDetector(this);
        im.addProcessor(gd);
        im.addProcessor(this);
        this.world = world;
//        this.ship = world.getShip();
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        timer = Timer.instance();
        task = new Timer.Task() {
            @Override
            public void run() {
                world.attack();
//                System.out.println("atak z task schedulera " + System.currentTimeMillis());
            }
        };
        timer.scheduleTask(task,1,0.5f);
        timer.stop();
    }

    public InputMultiplexer get(){

        return im;
    }

    public void addInputProcessor(InputProcessor inputProcessor){
        im.addProcessor(inputProcessor);
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }


    private String message;

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(autoAttackPointer == -1) {
            timer.start();
            autoAttackPointer = pointer;
        }
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            System.out.println("back");
            world.setPaused(!world.isPaused());
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        message = "Touch up";
        if(pointer == autoAttackPointer) {
            timer.stop();
            autoAttackPointer = -1;
        }
        else{
            world.attack();
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        world.moveShip(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
