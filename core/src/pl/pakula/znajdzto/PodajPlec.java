package pl.pakula.znajdzto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Klasa ekranu pytającego o płeć
 *
 * @author Oskar Pakuła 175958
 */
public class PodajPlec extends ScreenAdapter implements InputProcessor {

    /**
     * Pole klasy ZnajdzTo
     */
    ZnajdzTo gra;

    /**
     * Lista obiektów klasy ClickObject
     */
    ArrayList<ClickObject> clickObjects = new ArrayList<>();

    /**
     * Konstruktor klasy PodajPlec
     *
     * @param gra Instancja klasy ZnajdzTo
     */
    public PodajPlec(ZnajdzTo gra) {

        this.gra = gra;
    }

    /**
     * Metoda tworząca
     */
    @Override
    public void show() {

        clickObjects.add(new ClickObject(505, 430, new Sprite(new Texture("tlo.png")), 50, 40) {
            @Override
            public void click() {
                gra.setScreen(new MainMenuScreen(gra, 0, 'K'));
            }
        });

        clickObjects.add(new ClickObject(560, 430, new Sprite(new Texture("tlo.png")), 50, 50) {
            @Override
            public void click() {
                gra.setScreen(new MainMenuScreen(gra, 0, 'M'));
            }
        });

        Gdx.input.setInputProcessor(this);
    }

    /***
     * Metoda renderująca planszę
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gra.batch.begin();
        gra.batch.draw(new Texture("podajPlec.png"), 255, 300);

        for (ClickObject clickObject : clickObjects) {
            clickObject.render(gra.batch);
        }

        gra.batch.end();

    }

    @Override
    public void hide() {
    }

    /**
     * Metoda pozwalająca na używanie klawiszy
     *
     * @param keycode kod klawisza
     */
    @Override
    public boolean keyDown(int keycode) {

        /*
         * Sprawdza jaki klawisz został wciśniętu
         */
        switch (keycode) {

            case Input.Keys.K:
                gra.setScreen(new MainMenuScreen(gra, 0, 'K'));
                break;

            case Input.Keys.M:
                gra.setScreen(new MainMenuScreen(gra, 0, 'M'));
                break;

            default:
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Metoda obsługująca kliknięcie myszką
     *
     * @param screenX współrzędne poziomy myszki
     * @param screenY współrzędne pionowe odwrócone
     * @param button  LPM, SPM czy PPM?
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (ClickObject g : clickObjects) {
            g.ifClick(screenX, Gdx.graphics.getHeight() - screenY);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
