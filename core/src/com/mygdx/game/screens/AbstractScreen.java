package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SpaceInvaders;


/**
 * The base class for all game screens.
 */
public abstract class AbstractScreen implements Screen {

//    public static final float VIEWPORT_WIDTH = 480f, VIEWPORT_HEIGHT = 800f;
//    public static final int MENU_VIEWPORT_WIDTH = 800, MENU_VIEWPORT_HEIGHT = 480;

//    private static final boolean debug = true;

    protected final SpaceInvaders game;
    protected final Stage stage;

    private BitmapFont font;
    private SpriteBatch batch;
    private Skin skin;
    private TextureAtlas atlas;
    private Table table;

    public AbstractScreen(SpaceInvaders game)
    {
        this.game = game;
        ScreenViewport screenViewport = new ScreenViewport();
        float scale = 1f / Gdx.graphics.getDensity();
        screenViewport.setUnitsPerPixel(scale);
        this.stage = new Stage(screenViewport);
//        this.stage = new Stage();
    }

    protected String getName()
    {
        return getClass().getSimpleName();
    }

    protected boolean isGameScreen()
    {
        return false;
    }

    // Lazily loaded collaborators

    public BitmapFont getFont()
    {
        if( font == null ) {
            font = new BitmapFont();
        }
        return font;
    }

    public SpriteBatch getBatch()
    {
        if( batch == null ) {
            batch = new SpriteBatch();
        }
        return batch;
    }

    public TextureAtlas getAtlas()
    {
        if( atlas == null ) {
            atlas = new TextureAtlas( Gdx.files.internal( "image-atlases/pages.atlas" ) );
        }
        return atlas;
    }

    protected Skin getSkin()
    {
        if( skin == null ) {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        }
        return skin;
    }

    protected Table getTable()
    {
        if( table == null ) {
            table = new Table( getSkin() );
            table.setFillParent( true );

            stage.addActor( table );
        }
        return table;
    }

    // Screen implementation

    @Override
    public void show()
    {
        Gdx.app.log( "log", "Showing screen: " + getName() );

        // set the stage as the input processor
        Gdx.input.setInputProcessor( stage );
    }

    @Override
    public void resize(
            int width,
            int height )
    {
        Gdx.app.log( "log", "Resizing screen: " + getName() + " to: " + width + " x " + height );
    }

    @Override
    public void render(float delta )    {
        getBatch().enableBlending();
        getBatch().setColor(1,1,1,1);
        stage.act( delta );
        stage.draw();
    }

    @Override
    public void hide()
    {
        Gdx.app.log( "log", "Hiding screen: " + getName() );
        dispose();
    }

    @Override
    public void pause()
    {
        Gdx.app.log( "log", "Pausing screen: " + getName() );
    }

    @Override
    public void resume()
    {
        Gdx.app.log( "log", "Resuming screen: " + getName() );
    }

    @Override
    public void dispose()
    {
        Gdx.app.log( "log", "Disposing screen: " + getName() );
        if( font != null ) font.dispose();
        if( batch != null ) batch.dispose();
        if( skin != null ) skin.dispose();
        if( atlas != null ) atlas.dispose();
    }
}

