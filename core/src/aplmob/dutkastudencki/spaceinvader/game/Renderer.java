package aplmob.dutkastudencki.spaceinvader.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import aplmob.dutkastudencki.spaceinvader.game.objects.Chicken;
import aplmob.dutkastudencki.spaceinvader.game.objects.Egg;
import aplmob.dutkastudencki.spaceinvader.game.objects.GameObject;
import aplmob.dutkastudencki.spaceinvader.game.objects.Ship;
import aplmob.dutkastudencki.spaceinvader.game.objects.SimpleAttack;

import java.util.Iterator;

/**
 * Klasa renderowania gry.
 *
 * @since 2016-03-09
 * @author Łukasz Dutka
 */
public class Renderer {
    /**
     * Świat.
     */
    private World world;

    /**
     * Statek.
     */
    private Ship ship;

    /**
     * Kamera.
     */
    private OrthographicCamera camera;

    /**
     * Obiekt do rysowania.
     */
    private SpriteBatch batch;

    /**
     * Obiekt z opisem wyglądu UI.
     */
    private Skin skin;

    /**
     * Etykieta z wynikiem.
     */
    private Label score;

    /**
     * Etykieta z życiami.
     */
    private Label lives;

    /**
     * Symbol żyć.
     */
    private Image hearth;

    /**
     * Tabela u góry ekranu z wynikiem i życiami.
     */
    private Table infoHeader;

    /**
     * Konstruktor renderera.
     * @param world  świat
     * @param batch  obiekt do rysowania
     * @param skin  obiekt z opisem wyglądu UI
     */
    public Renderer(final World world, final SpriteBatch batch, final Skin skin) {
        this.world = world;
        this.ship = world.getShip();
        this.skin = skin;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.batch = batch;
        batch.setProjectionMatrix(camera.combined);

        lives = new Label(" 3 x",skin);
        score = new Label("0",skin);
        hearth = new Image( new Texture(Gdx.files.internal("hearth1.png")));
        hearth.setWidth(15);
        hearth.setHeight(15);

        infoHeader = prepareInfoHeader();
    }

    /**
     * Tworzy belkę z informacjami o grze na górze ekranu.
     * @return
     */
    private Table prepareInfoHeader(){
        Table table = new Table(skin){
        };
        table.setWidth(Config.SCREEN_WIDTH);
        table.setPosition(0, Config.SCREEN_HEIGHT - 20);
        table.add("Score: ").left();
        table.add(score).expand().left();
        table.add(hearth).expand().right();
        table.add(lives).right();
        return table;
    }

    /**
     * Renderowanie świata gry.
     * @param delta  czas od poprzedniej klatki sceny
     */
    public void render(float delta){
        if(world.isPaused()){
            return;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //aktualizacja belki
        score.setText(world.getScore() + "");
        lives.setText(" " + world.getLives()+" x");

        batch.begin();

        //rysowanie statku
        batch.draw(ship.getTexture(), ship.getX(), ship.getY());

        // pętla po wszystkich pociskach - rysuje i porusza
        Iterator<GameObject> bulletIterator = world.getBullets().iterator();
        while(bulletIterator.hasNext()){
            GameObject bullet = bulletIterator.next();
            bullet.move();
            bullet.draw(batch);
            if(bullet.outOfMap()){
                bullet.dispose();
                bulletIterator.remove();
            }
        }

        // pętla po wszystkich kurczakach - rysuje i sprawdza kolizje z wszystkimi pociskami
        Iterator<GameObject> chickenIterator = world.getChickens().iterator();
        while(chickenIterator.hasNext()){
            Chicken chicken = (Chicken)chickenIterator.next();
            chicken.animate(batch,delta);
            Egg egg = chicken.throwEgg();
            if (egg != null){
                world.getEggs().add(egg);
            }

            // pętla po wszystkich pociskach
            bulletIterator = world.getBullets().iterator();
            while(bulletIterator.hasNext()) {
                SimpleAttack bullet = (SimpleAttack)bulletIterator.next();
                if(bullet.getBoundingBox().overlaps(chicken.getBoundingBox())){
                    if(chicken.applyDamage(bullet.getDamagePoints()) <= 0){
                        chicken.dispose();
                        chickenIterator.remove();
                        world.killedChicken();
                    }
                    bullet.dispose();
                    bulletIterator.remove();
                    break;
                }
            }
        }

        // pętla po wszystkich jajkach - rysuje, porusza, sprawdza kolizje z graczem
        Iterator<GameObject> eggsIterator = world.getEggs().iterator();
        while(eggsIterator.hasNext()){
            GameObject egg = eggsIterator.next();
            egg.move();
            egg.draw(batch);
            if(egg.getBoundingBox().overlaps(ship.getBoundingBox())){
                world.killedPlayer();
                eggsIterator.remove();
                continue;
            }
            if(egg.outOfMap()){
                egg.dispose();
                eggsIterator.remove();
            }
        }


        if(batch.isDrawing()) {
            batch.end();
        }

        // rysowanie belki z informacjami
        batch.begin();
        infoHeader.draw(batch, 1f);
        batch.end();

        // restart etapu
        if(world.getChickens().size == 0){
            world.getBullets().clear();
            world.spawnChickens();
        }


    }
}