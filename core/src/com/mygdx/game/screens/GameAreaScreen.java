package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.game.Config;
import com.mygdx.game.game.Consts;
import com.mygdx.game.game.Renderer;
import com.mygdx.game.game.World;
import com.mygdx.game.game.handlers.InputHandler;

public class GameAreaScreen extends AbstractScreen{
    private World world;
    private Renderer renderer;

    private Vector3 touchPos; // stare do ruchu
    float cameraRatioX,cameraRatioY; // stare do ruchu

    public GameAreaScreen(SpaceInvaders game){
        super(game);
        Gdx.input.setCatchBackKey(true);
        world = new World();
        renderer = new Renderer(world);
        cameraRatioX = (Gdx.graphics.getWidth()*1.0f)/(Consts.SCREEN_WIDTH*1.0f);   // stare do ruchu
        cameraRatioY = (Gdx.graphics.getHeight()*1.0f)/(Consts.SCREEN_HEIGHT*1.0f); // stare do ruchu
        Config.screenRatioX = cameraRatioX;
        Config.screenRatioY = cameraRatioY;
        Gdx.input.setInputProcessor(new InputHandler(cameraRatioX,cameraRatioY,world).get());

        touchPos = new Vector3(); // stare do ruchu
        renderer.getCamera().unproject(touchPos); // stare do ruchu

        System.out.println("screen height " + Consts.SCREEN_WIDTH);
        System.out.println("camera ratio " + cameraRatioX);

        walkSheet = new Texture(Gdx.files.internal("chickenanim.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,5,6);
        int index = 0;
        walkFrames = new TextureRegion[5*6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.25f, walkFrames);      // #11         // #12
        stateTime = 0f;
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    Animation                       walkAnimation;          // #3
    Texture                         walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5
    SpriteBatch spriteBatch;            // #6
    TextureRegion                   currentFrame;           // #7
    float stateTime;


    @Override
    public void render(float delta) {
        renderer.render(delta);
//        stateTime += Gdx.graphics.getDeltaTime();           // #15
//        currentFrame = walkAnimation.getKeyFrame(stateTime, true);  // #16
//        spriteBatch.begin();
//        spriteBatch.draw(currentFrame, 200, 200,200,200);             // #17
//        spriteBatch.end();
        /* stare do ruchu poczatek
        for(int i=0;i<2;i++) {
            if(Gdx.input.isTouched(i)) {
                touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);

                if(Gdx.input.getY(i)> Consts.SCREEN_HEIGHT*cameraRatioY/2) {
                    if (Math.abs(Gdx.input.getX(i) - world.getShip().getX()) > 1) {


                        touchPos.set(touchPos.x - 32*cameraRatioX, 0, 0);
                        //camera.unproject(touchPos);
                        world.getShip().setX( world.getShip().getX() + (5 * Gdx.graphics.getDeltaTime() * (touchPos.x/cameraRatioX - world.getShip().getX())));
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
