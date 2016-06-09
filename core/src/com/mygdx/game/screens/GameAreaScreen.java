package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.game.Config;
import com.mygdx.game.game.Consts;
import com.mygdx.game.game.Renderer;
import com.mygdx.game.game.World;
import com.mygdx.game.game.handlers.InputHandler;

import java.io.File;

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
    private InputMultiplexer inputMultiplexer;

    private Vector3 touchPos; // stare do ruchu
    float cameraRatioX,cameraRatioY; // stare do ruchu

    public GameAreaScreen(SpaceInvaders game){
        super(game);
        Gdx.input.setCatchBackKey(true);
        world = new World();
        renderer = new Renderer(world,getBatch());
        cameraRatioX = (Gdx.graphics.getWidth()*1.0f)/(Consts.SCREEN_WIDTH*1.0f);   // stare do ruchu
        cameraRatioY = (Gdx.graphics.getHeight()*1.0f)/(Consts.SCREEN_HEIGHT*1.0f); // stare do ruchu
        Config.screenRatioX = cameraRatioX;
        Config.screenRatioY = cameraRatioY;
        inputMultiplexer = new InputHandler(cameraRatioX,cameraRatioY,world).get();


        touchPos = new Vector3(); // stare do ruchu
        renderer.getCamera().unproject(touchPos); // stare do ruchu

        System.out.println("screen height " + Consts.SCREEN_WIDTH);
        System.out.println("camera ratio " + cameraRatioX);

//        spriteBatch = new SpriteBatch();



//        skin = new Skin(Gdx.files.internal("uiskin.json"));
//        table = new Table();
//        table.setWidth(stage.getWidth());
//        table.align(Align.center | Align.top);
//        table.setPosition(0, stage.getHeight() / 2);
//        startButton = new TextButton("     New Game     ", skin);
//        quitButton = new TextButton("     Quit Game     ", skin);
//        scoreButton = new TextButton("     High Score     ", skin);
//        startButton.addListener(new ChangeListener() {
//            public void changed(ChangeEvent event, Actor actor) {
//            }
//        });
//        quitButton.addListener(new ChangeListener() {
//            public void changed(ChangeEvent event, Actor actor) {
//            }
//        });
//        table.add(startButton).padBottom(30);
//        table.row();
//        table.add(scoreButton).padBottom(30);
//        table.row();
//        table.add(quitButton);
//        stage.addActor(table);

//        batch = new SpriteBatch();
//        batch.setProjectionMatrix(renderer.getCamera().combined);
//
//        inputMultiplexer.addProcessor(stage);
        gameMenuScreen = new GameMenuScreen(game);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void show() {

    }
    private TextureRegion tr;
    private Image im;
    private Dialog d = new Dialog("meniu",getSkin()){
        @Override
        public float getPrefHeight() {
            return Consts.SCREEN_HEIGHT / 2;
        }

        @Override
        public float getPrefWidth() {
            return Consts.SCREEN_WIDTH / 2;
        }
    };
    private void showMenu(){
        d.align(Align.center);
//        tr = ScreenUtils.getFrameBufferTexture();
//        byte[] bity = ScreenUtils.getFrameBufferPixels(0,0,Gdx.graphics.getBackBufferWidth(),Gdx.graphics.getBackBufferHeight(),true);
//
//        FileHandle to = Gdx.files.local("test.png");
//        to.writeBytes(bity,false);
//                File f = new File("/sdcard/screenshot.png");
//        FileHandle fh = new FileHandle(f);
//        fh.writeBytes(bity,false);
//        System.out.println("rozmiar bitow " + bity.length);
//        for(int i=0;i<bity.length;i=i+4){
//            System.out.println(String.format("r %d g %d b %d a %d", bity[i],bity[i+1],bity[i+2],bity[i+3]));
//        }
//        TextureData td = tr.getTexture().getTextureData();
////        td.prepare();
//        Pixmap px = tr.getTexture().getTextureData().consumePixmap();
//        File f = new File("/sdcard/screenshot.png");
//        FileHandle fh = new FileHandle(f);
//                PixmapIO.writePNG(fh,px);
//        im = new Image(tr);
//        stage.addActor(im);
        Button b = new TextButton("opcja ", getSkin()){
//            @Override
//            public float getPrefHeight() {
//                return Consts.SCREEN_HEIGHT / 2 - 10;
//            }

            @Override
            public float getPrefWidth() {
                return Consts.SCREEN_WIDTH / 2 - 10;
            }
        };
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hideMenu();
            }
        });
        Gdx.input.setInputProcessor(stage);
        d.button(b);
        d.show(stage);

        menu = true;
        System.out.println("daje meniu width:" + d.getWidth() + " height:" + d.getHeight() );
    }

    private void hideMenu(){
        d.cancel();
        menu = false;
        System.out.println("bez menu");
        world.setPaused(false);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
    private boolean menu = false;

    @Override
    public void render(float delta) {
        renderer.render(delta);
        if(world.isPaused()){
//            Gdx.input.setInputProcessor(gameMenuScreen.stage);
//            gameMenuScreen.render(delta);
            if(!menu){
                showMenu();
            }
//            else{
//                hideMenu();
//            }
            Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            super.render(delta);
//            SpriteBatch batch =  getBatch();
//            batch.begin();
//            batch.draw(tr, 200, 200, 400, 400);
//            batch.end();
//            Gdx.input.setInputProcessor(stage);
//            gameMenuScreen.render(delta);
////            stage.draw();
//            batch.begin();
////            Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
////            batch.enableBlending();
////            batch.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_DST_ALPHA);
//            table.draw(batch, 0.5f);
////            batch.disableBlending();
//            batch.end();
        }
        else{

//            hideMenu();
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
