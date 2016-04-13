package com.mygdx.game;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector3;
//
//public class MyGdxGame extends ApplicationAdapter {
//	SpriteBatch batch;
//	Texture img;
//
//
//	private OrthographicCamera camera;
//	private Texture dropImage;
//	private Texture bucketImage;
//	private Rectangle bucket;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//
//		dropImage = new Texture(Gdx.files.internal("droplet.png"));
//		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
//
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, 800, 480);
//
//		bucket = new Rectangle();
//		bucket.x = 800 / 2 - 64 / 2;
//		bucket.y = 20;
//		bucket.width = 64;
//		bucket.height = 64;
//
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.draw(dropImage, 400, 240);
//		batch.draw(bucketImage, 240, 400);
//		batch.end();
//
//		if(Gdx.input.isTouched()) {
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			bucket.x = touchPos.x - 64 / 2;
//		}
//	}
//}

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MyGdxGame extends ApplicationAdapter {
	private Texture dropImage;
	private Texture shipImage;
	private Texture attackImage;
	private Texture chickenImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Rectangle bucket;
	private Array<Rectangle> attacks;
	private Array<Rectangle> chickens;
	private long lastAutoAttack;

	private Double cameraRatioX = 1.0;
	private Double cameraRatioY = 1.0;

	boolean spawned = false;

	private Integer height = 800;
	private Integer width = 480;
	Vector3 touchPos;
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if(this.height != null){
			return;
		}
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if(this.width != null){
			return;
		}
		this.width = width;
	}

	@Override
	public void create() {
		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		shipImage = new Texture(Gdx.files.internal("ship.png"));
		attackImage = new Texture(Gdx.files.internal("atak.png"));
		chickenImage = new Texture(Gdx.files.internal("chicken.png"));
		cameraRatioX = (Gdx.graphics.getWidth()*1.0)/(width*1.0);
		cameraRatioY = (Gdx.graphics.getHeight()*1.0)/(height*1.0);
//		height = Gdx.graphics.getHeight();
//		width = Gdx.graphics.getWidth();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		batch = new SpriteBatch();

		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.x = width / 2 - 43 / 2; // center the bucket horizontally
		bucket.y = 120; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
		bucket.width = 64;
		bucket.height = 64;


		// create the raindrops array and spawn the first raindrop
		attacks = new Array<Rectangle>();
		spawnRaindrop();
		chickens = new Array<Rectangle>();
		spawnChickens();

		touchPos = new Vector3();
		camera.unproject(touchPos);
	}

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, width-64);
		raindrop.y = height-64;
		raindrop.width = 64;
		raindrop.height = 64;
		lastAutoAttack = TimeUtils.nanoTime();
		Gdx.app.log("res", "height: " + Gdx.graphics.getHeight());
		Gdx.app.log("res", "width: " + Gdx.graphics.getWidth());
	}

	private void spawnChickens() {
		for (int y = 70; y < 350; y=y+70) {
			for (int x = 0; x < width; x = x + 61) {
				Rectangle chicken = new Rectangle();
				chicken.x = x;
				chicken.y = height - y;
				chicken.width = 54;
				chicken.height = 39;
				chickens.add(chicken);
			}
		}
	}
//
//	Iterator<Rectangle> iter = attacks.iterator();
////		Gdx.app.log("ilosc atakow", "" + attacks.size);
//	while(iter.hasNext()) {
//		Rectangle attack = iter.next();
//		attack.y += 200 * Gdx.graphics.getDeltaTime();
//		if(attack.y > height + 64 ) iter.remove();

	private void detectColision(){
		Iterator<Rectangle> chickensIter = chickens.iterator();
		Iterator<Rectangle> attacksIter = attacks.iterator();
		while (attacksIter.hasNext()){
			Gdx.app.log("attacksIter", "hasNext");

			Rectangle attack = attacksIter.next();
			while(chickensIter.hasNext()){
				Rectangle chicken = chickensIter.next();
				Gdx.app.log("chickensIter", "hasNext");

				if(attack.x > chicken.x-6 && attack.x < chicken.x+60){
					if(attack.y > chicken.y-39){
						chickensIter.remove();
						attacksIter.remove();
					}

				}

			}
		}
		for(Rectangle chicken:chickens){
			for(Rectangle attack:attacks){
				if(attack.x+16 >= chicken.x && attack.x+16 < chicken.x+39){
					if(attack.y > chicken.y-39){

					}
				}
			}
		}
	}




	private void attack() {
//		Gdx.app.log("attack", "atak");
		if(TimeUtils.nanoTime() - lastAutoAttack < 100000000){
			return;
		}
		Rectangle raindrop = new Rectangle();
		raindrop.x = bucket.x +29;
		raindrop.y = 16 + bucket.y;
		raindrop.width = 6;
		raindrop.height = 16;
		attacks.add(raindrop);
		lastAutoAttack = TimeUtils.nanoTime();
	}


	@Override
	public void render() {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
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
		batch.draw(shipImage, bucket.x, bucket.y);

		for(Rectangle attack: attacks) {
			batch.draw(attackImage, attack.x, attack.y);
		}

		for(Rectangle chicken: chickens) {
			batch.draw(chickenImage, chicken.x, chicken.y);
		}
		batch.end();

		// process user input
		for(int i=0;i<2;i++) {
		if(Gdx.input.isTouched(i)) {
			touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);

				if(Gdx.input.getY(i)>height*cameraRatioY/2) {
					if (Math.abs(Gdx.input.getX(i) - bucket.x) > 1) {
//						Gdx.app.log("move", "input.x: " + Gdx.input.getX(i)/cameraRatioX);
//						Gdx.app.log("move", "bucket.x: " + bucket.x);
//						Gdx.app.log("move", "ratio: " + cameraRatioX);


						touchPos.set(touchPos.x - 32, 0, 0);
						//camera.unproject(touchPos);
						bucket.x += (5 * Gdx.graphics.getDeltaTime() * (touchPos.x - bucket.x));
						//bucket.x += (5 * Gdx.graphics.getDeltaTime() * (Gdx.input.getX(i) - bucket.x * cameraRatioX));
					}
				}
				if(Gdx.input.getY(i)<height*cameraRatioY/2){
					if(TimeUtils.nanoTime() - lastAutoAttack > 500000000){
						spawned = true;
						attack();
					}

				} else{
					spawned = false;
				}
			}
			//Gdx.app.log("diff", Math.abs(Gdx.input.getX()- bucket.x) + "" );
			//Gdx.app.log("diff", Gdx.graphics.getDeltaTime() + "");
			//touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			//camera.unproject(touchPos);
			//bucket.x = touchPos.x - 64 / 2;
		}
		if(Gdx.input.justTouched()){
//			if(!spawned) {
				attack();
//			}

		}

		detectColision();

		if(Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

		// make sure the bucket stays within the screen bounds
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > width - 64) bucket.x = width - 64;

		// check if we need to create a new raindrop
		//if(TimeUtils.nanoTime() - lastAutoAttack > 1000000000) spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.

		Iterator<Rectangle> iter = attacks.iterator();
//		Gdx.app.log("ilosc atakow", "" + attacks.size);
		while(iter.hasNext()) {
			Rectangle attack = iter.next();
			attack.y += 200 * Gdx.graphics.getDeltaTime();
			if(attack.y > height + 64 ) iter.remove();
//			if(attack.overlaps(bucket)) {
//				iter.remove();
//			}
		}
	}

	public void log(String text){
		BitmapFont font;
		font = new BitmapFont();
		font.setColor(Color.RED);
		batch.begin();
		font.draw(batch, text, 200, 200);
		batch.end();
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		dropImage.dispose();
		shipImage.dispose();
		batch.dispose();
	}
}
