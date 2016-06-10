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
//    @Override
//    public boolean panStop(float x, float y, int pointer, int button) {
//        d("public boolean panStop(float x, float y, int pointer, int button) {");
//        return false;
//    }
//
//
//    private String message;
//
//    @Override
//    public boolean touchDown(float x, float y, int pointer, int button) {
//        d("public boolean touchDown(float x, float y, int pointer, int button) {");
//        if(autoAttackPointer == -1) {
//            timer.start();
//            autoAttackPointer = pointer;
//        }
//        return world.isPaused();
//    }
//
////    @Override
////    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
////        d("public boolean touchDown(int screenX, int screenY, int pointer, int button) {");
////        return world.isPaused();
////    }
//        @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        d("public boolean touchDown(int screenX, int screenY, int pointer, int button)");
//        return false;
//    }
//
//    @Override
//    public boolean tap(float x, float y, int count, int button) {
//        d("public boolean tap(float x, float y, int count, int button) {");
//        return false;
//    }
//
//    @Override
//    public boolean longPress(float x, float y) {
//        d("public boolean longPress(float x, float y) {");
//        return world.isPaused();
//    }
//
//    @Override
//    public boolean fling(float velocityX, float velocityY, int button) {
//        d("public boolean fling(float velocityX, float velocityY, int button) {");
//        return false;
//    }
//
//    @Override
//    public boolean pan(float x, float y, float deltaX, float deltaY) {
//        d("public boolean pan(float x, float y, float deltaX, float deltaY) {");
//        return false;
//    }
//
////    @Override
////    public boolean zoom(float initialDistance, float distance) {
////        d("public boolean zoom(float initialDistance, float distance) {");
////        return true;
////    }
////
////    @Override
////    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
////        d("public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {");
////        return true;
////    }
//
//    @Override
//    public boolean keyDown(int keycode) {
//        d("public boolean keyDown(int keycode) {");
//        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
//            System.out.println("back");
//            world.setPaused(!world.isPaused());
//        }
//        return world.isPaused();
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        d("public boolean keyUp(int keycode) {");
//        return world.isPaused();
//    }
//
//    @Override
//    public boolean keyTyped(char character) {
//        d("public boolean keyTyped(char character) {");
//        return world.isPaused();
//    }
//
//
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        d("public boolean touchUp(int screenX, int screenY, int pointer, int button) {");
//        message = "Touch up";
//        if(pointer == autoAttackPointer) {
//            timer.stop();
//            autoAttackPointer = -1;
//        }
//        else{
//            world.attack();
//        }
//        return world.isPaused();
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        d(" public boolean touchDragged(int screenX, int screenY, int pointer) {");
//        world.moveShip(screenX, screenY);
//        return world.isPaused();
//    }
//
//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        d("public boolean mouseMoved(int screenX, int screenY) {");
//        return false;
//    }
//
//    @Override
//    public boolean scrolled(int amount) {
//        d("public boolean scrolled(int amount) {");
//        return false;
//    }

    private void d(String s) {
//        System.out.println(s);
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        d("panStop");
        return false;
    }


    private String message;

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(autoAttackPointer == -1) {
            timer.start();
            autoAttackPointer = pointer;
        }
        d("public boolean touchDown(float x, float y, int pointer, int button)");
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        d("public boolean touchDown(int screenX, int screenY, int pointer, int button)");
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        d("public boolean tap");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        d("public boolean longPress");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        d("public boolean fling");
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        d("public boolean pan");
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        d("public boolean zoom");
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        d("public boolean pinch");
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        d("public boolean keyDown");
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            System.out.println("back");
            world.setPaused(!world.isPaused());
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        d("public boolean keyUp");
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        d("public boolean keyTyped");
        return false;
    }

//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        d("public boolean touchDown(int screenX, int screenY, int pointer, int button)");
//
//
//        return false;
//    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        d("public boolean touchUp");
        message = "Touch up";
        if(pointer == autoAttackPointer) {
            timer.stop();
            autoAttackPointer = -1;
        }
        else{
            world.attack();
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        d("public boolean touchDragged");
        world.moveShip(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        d("public boolean mouseMoved");
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        d("public boolean scrolled");
        return true;
    }
}
