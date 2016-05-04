package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.game.objects.Ship;

/**
 * Created by admin on 2016-04-30.
 */
public class Renderer {
    private World world;
    private Ship ship;

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private Vector3 touchPos;

    public Renderer(World world) {
        this.world = world;
        this.ship = world.getShip();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT);
//        shapeRenderer = new ShapeRenderer();
//        shapeRenderer.setProjectionMatrix(camera.combined);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        touchPos = new Vector3();
        camera.unproject(touchPos);
    }

    public void render(){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        batch.begin();
        batch.draw(ship.getTexture(), ship.getX(), ship.getY());
        batch.end();


    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
