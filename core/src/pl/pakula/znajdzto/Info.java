package pl.pakula.znajdzto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Klasa opisująca ekran informacyjny
 *
 * @author Oskar Pakuła 175958
 */
public class Info extends ScreenAdapter implements InputProcessor {

    /**
     * Pole odpowidające za płeć gracza
     */
    private final char plec;
    /**
     * Pole klasy ZnajdzTo
     */
    ZnajdzTo gra;
    /**
     * Lista obiektów ClickObject
     */
    ArrayList<ClickObject> clickObjects = new ArrayList<>();

    /**
     * Konstruktor klasy Info
     *
     * @param gra  instancja klasy ZnajdzTo
     * @param plec płeć gracza
     */
    public Info(ZnajdzTo gra, char plec) {
        this.gra = gra;
        this.plec = plec;
    }

    /**
     * Metoda tworząca
     */
    @Override
    public void show() {

        Gdx.input.setInputProcessor(this);
        /*
         * Dodawanie obiektów klikających
         */
        clickObjects.add(new ClickObject(0, 0, new Sprite(new Texture("wroc.png")), 70, 140) {
            @Override
            public void click() {
                gra.setScreen(new MainMenuScreen(gra, 0, plec));
            }
        });

    }

    /***
     * Metoda renderująca planszę
     */
    @Override
    public void render(float delta) {


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gra.batch.begin();

        gra.batch.draw(new Texture("help.png"), 0, 0);

        /*
         * Rysowanie obiektów do klikania
         */
        for (ClickObject clickObject : clickObjects) {
            clickObject.render(gra.batch);
        }

        gra.batch.end();

    }

    @Override
    public void hide() {
    }

    @Override
    public boolean keyDown(int keycode) {
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
        /*
         * Przeszukiwanie elementów i sprawdzanie czy kursor się znajdował w danym miejscu
         */
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
