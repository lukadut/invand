package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.game.handlers.Screenshot;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.file.Path;

public class MainMenuScreen extends AbstractScreen{
    Table table;
    private TextButton startButton;
    private TextButton quitButton;
    private TextButton scoreButton;
    private SpriteBatch batch;
    private Sprite sprite;
    private Skin skin;
    private SpaceInvaders g;

    public MainMenuScreen(SpaceInvaders game){
        super(game);
        this.g = game;
    }

    @Override
    public void show() {
//        FileHandle fh = Gdx.files.internal("uiskin.json");
//
//        Gdx.app.log("fh.path()", "" + fh.path());
//        File f = fh.file();
//        Gdx.app.log("f", "" + f.getAbsolutePath());
//        Gdx.app.log("f", "" + f.getPath());
//        Gdx.app.log("f", "" + f.toURI());
//        Gdx.app.log("fh",fh.read().toString());
//
//        try {
//            final BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(fh.read()));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                        System.out.println(line);
//            }
//            reader.close();
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);

        Gdx.app.log("density", "" + Gdx.graphics.getDensity());
        Gdx.app.log("stage width", "" + stage.getWidth());
        Gdx.app.log("stage height", "" + stage.getHeight());
        Gdx.app.log("screen width", "" + Gdx.graphics.getWidth());
        Gdx.app.log("screen height", "" + Gdx.graphics.getHeight());

        table.setPosition(0, stage.getHeight() / 2);
        startButton = new TextButton("     New Game     ", skin);
        quitButton = new TextButton("     Quit Game     ", skin);
        scoreButton = new TextButton("     High Score     ", skin);

        startButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

//                startButton.setText("THE END IS COMING!!!");
                // initialize(new MyGdxGame(), config);

                g.setScreen(new GameAreaScreen(g));
//                 g.setScreen( new GameScreen());
            }
        });

        quitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                // quitButton.setText("THE END IS COMING!!!");

                Gdx.app.exit();
            }
        });

        // table.padTop(30);
        table.add(startButton).padBottom(30);
        table.row();
        table.add(scoreButton).padBottom(30);
        table.row();
        table.add(quitButton);


        System.out.println(Gdx.files.internal("backgroung").file().getAbsolutePath());
        Image image = new Image(new Texture(Gdx.files.internal("background.png")));
        image.setHeight(stage.getHeight());
        image.setWidth(stage.getWidth());

        stage.addActor(image);
        stage.addActor(table);

        Dialog d = new Dialog("abc",skin);
        d.setWidth(Gdx.graphics.getWidth()/2);
//        d.add("abcd");
        TextButton b = new TextButton("baton",skin);
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                FrameBuffer fb = new FrameBuffer(Pixmap.Format.RGBA8888,480,800,true);
//                fb.begin();

                saveScreenshot();
//                fb.end();
//                TextureRegion tr;
//                Image im;
//                tr = ScreenUtils.getFrameBufferTexture();
//                byte[] bity = ScreenUtils.getFrameBufferPixels(0,0,Gdx.graphics.getBackBufferWidth(),Gdx.graphics.getBackBufferHeight(),true);
//
//                FileHandle to = Gdx.files.local("test.png");
//                to.writeBytes(bity,false);
//                File f = new File("/sdcard/screenshot.png");
//                FileHandle fh = new FileHandle(f);
//                fh.writeBytes(bity,false);
//                System.out.println("rozmiar bitow " + bity.length);
//                for(int i=0;i<bity.length;i=i+4){
//                    System.out.println(String.format("r %d g %d b %d a %d", bity[i],bity[i+1],bity[i+2],bity[i+3]));
//                }
            }
        });
        d.button(b);
        d.button("abcd");
        d.show(stage);




//        batch = getBatch();
//        sprite = new Sprite(new Texture(Gdx.files.internal("background.png")));
//        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//        stage.addActor(sprite);
        Gdx.input.setInputProcessor(stage);

    }

    private static int counter = 1;
    public static void saveScreenshot(){
        try{
            FileHandle fh;
            do{
                fh = new FileHandle(Gdx.files.getExternalStoragePath() + "screenshot" + counter++ + ".png");
            }while (fh.exists());
            String path = fh.path();
            System.out.println(path);
            Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            PixmapIO.writePNG(fh, pixmap);
            pixmap.dispose();
        }catch (Exception e){
            Gdx.app.error("screen","cos sie popsulo i nie bylo mnie slychac");
        }
    }

    private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown){
        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);

//        if (yDown) {
//            // Flip the pixmap upside down
//            ByteBuffer pixels = pixmap.getPixels();
//            int numBytes = w * h * 4;
//            byte[] lines = new byte[numBytes];
//            int numBytesPerLine = w * 4;
//            for (int i = 0; i < h; i++) {
//                pixels.position((h - i - 1) * numBytesPerLine);
//                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
//            }
//            pixels.clear();
//            pixels.put(lines);
//        }

        return pixmap;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta);
    }
}
