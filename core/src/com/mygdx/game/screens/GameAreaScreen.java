package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.game.Config;
import com.mygdx.game.game.Consts;
import com.mygdx.game.game.Renderer;
import com.mygdx.game.game.World;
import com.mygdx.game.game.handlers.InputHandler;

public class GameAreaScreen extends AbstractScreen{
    private World world;
    private Renderer renderer;
    private GameMenuScreen gameMenuScreen;

    Table table;
    private TextButton startButton;
    private TextButton quitButton;
    private TextButton scoreButton;
    private SpriteBatch batch;
    private Sprite sprite;
    private Skin skin;
    private SpaceInvaders g;

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
        InputMultiplexer inputMultiplexer = new InputHandler(cameraRatioX,cameraRatioY,world).get();


        touchPos = new Vector3(); // stare do ruchu
        renderer.getCamera().unproject(touchPos); // stare do ruchu

        System.out.println("screen height " + Consts.SCREEN_WIDTH);
        System.out.println("camera ratio " + cameraRatioX);

        spriteBatch = new SpriteBatch();



        skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, stage.getHeight() / 2);
        startButton = new TextButton("     New Game     ", skin);
        quitButton = new TextButton("     Quit Game     ", skin);
        scoreButton = new TextButton("     High Score     ", skin);
        startButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
        quitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
        table.add(startButton).padBottom(30);
        table.row();
        table.add(scoreButton).padBottom(30);
        table.row();
        table.add(quitButton);
        stage.addActor(table);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(renderer.getCamera().combined);

        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
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
        if(world.isPaused()){
//            stage.draw();
            batch.begin();
//            Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
//            batch.enableBlending();
//            batch.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_DST_ALPHA);
            table.draw(batch, 0.5f);
//            batch.disableBlending();
            batch.end();
        }
//        super.render(delta);
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
