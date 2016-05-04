package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
<<<<<<< HEAD
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
=======
import com.badlogic.gdx.math.Vector3;
>>>>>>> origin/podzial
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.game.Consts;
import com.mygdx.game.game.Renderer;
import com.mygdx.game.game.World;
import com.mygdx.game.game.helpers.InputHandler;

public class GameAreaScreen extends AbstractScreen{
    private World world;
    private Renderer renderer;

    private Vector3 touchPos; // stare do ruchu
    float cameraRatioX,cameraRatioY; // stare do ruchu

    public GameAreaScreen(SpaceInvaders game){
        super(game);
        world = new World();
        renderer = new Renderer(world);
//        Gdx.input.setInputProcessor(new InputHandler(world.getShip()).get());

        touchPos = new Vector3(); // stare do ruchu
        renderer.getCamera().unproject(touchPos); // stare do ruchu
        cameraRatioX = (Gdx.graphics.getWidth()*1.0f)/(Consts.SCREEN_HEIGHT*1.0f); // stare do ruchu
        cameraRatioY = (Gdx.graphics.getHeight()*1.0f)/(Consts.SCREEN_HEIGHT*1.0f); // stare do ruchu

    }

    @Override
    public void show() {
        Image image = new Image(new Texture(Gdx.files.internal("background.png")));
        stage.addActor(image);

    }


    @Override
    public void render(float delta) {
<<<<<<< HEAD
        super.render(delta);
=======
        renderer.render();

        /* stare do ruchu poczatek */
        for(int i=0;i<2;i++) {
            if(Gdx.input.isTouched(i)) {
                touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);

                if(Gdx.input.getY(i)> Consts.SCREEN_HEIGHT*cameraRatioY/2) {
                    if (Math.abs(Gdx.input.getX(i) - world.getShip().getX()) > 1) {


                        touchPos.set(touchPos.x - 32, 0, 0);
                        //camera.unproject(touchPos);
                        world.getShip().setX( world.getShip().getX() + (5 * Gdx.graphics.getDeltaTime() * (touchPos.x - world.getShip().getX())));
                        //bucket.x += (5 * Gdx.graphics.getDeltaTime() * (Gdx.input.getX(i) - bucket.x * cameraRatioX));
                    }
                }
                if(Gdx.input.getY(i)<Consts.SCREEN_HEIGHT*cameraRatioY/2){
//                    if(TimeUtils.nanoTime() - lastAutoAttack > 500000000){
//                        spawned = true;
//                        attack();
//                    }

                } else{
//                    spawned = false;
                }
            }
            //Gdx.app.log("diff", Math.abs(Gdx.input.getX()- bucket.x) + "" );
            //Gdx.app.log("diff", Gdx.graphics.getDeltaTime() + "");
            //touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //camera.unproject(touchPos);
            //bucket.x = touchPos.x - 64 / 2;
        }
        if(Gdx.input.justTouched()){
//            attack();


        }
        if(world.getShip().getX() < 0) world.getShip().setX(0);
        if(world.getShip().getX() > Consts.SCREEN_WIDTH - Consts.SHIP_WIDTH) world.getShip().setX(Consts.SCREEN_WIDTH - Consts.SHIP_WIDTH);
        /* stare do ruchu koniec */


>>>>>>> origin/podzial
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
