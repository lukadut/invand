package aplmob.dutkastudencki.spaceinvader.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import aplmob.dutkastudencki.spaceinvader.SpaceInvaders;


/**
 * Baza dla screen.<br>
 *     Źródło: <a href="https://github.com/sysoneapplication/steigert-libgdx/blob/master/tyrian-game/src/com/blogspot/steigert/tyrian/screens/AbstractScreen.java">github.com/sysoneapplication</a>
 *
 * @since 2016-04-06
 * @author Łukasz Dutka
 * @author github.com/sysoneapplication
 */
public abstract class AbstractScreen implements Screen {

    protected final SpaceInvaders game;
    protected final Stage stage;

    private BitmapFont font;
    private SpriteBatch batch;
    private Skin skin;
    private TextureAtlas atlas;
    private Table table;

    protected Image background;

    public AbstractScreen(SpaceInvaders game)
    {
        this.game = game;
        ScreenViewport screenViewport = new ScreenViewport();
        float scale = 1f / Gdx.graphics.getDensity();
        screenViewport.setUnitsPerPixel(scale);
        this.stage = new Stage(screenViewport);
    }

    protected String getName()
    {
        return getClass().getSimpleName();
    }

    protected boolean isGameScreen()
    {
        return false;
    }

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

    protected void defaultBackground()
    {
        background = new Image(new Texture(Gdx.files.internal("background.png")));
        background.setHeight(stage.getHeight());
        background.setWidth(stage.getWidth());
    }
    // Screen implementation
    @Override
    public void show()
    {
        Gdx.app.log( "log", "Showing screen: " + getName() );
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

