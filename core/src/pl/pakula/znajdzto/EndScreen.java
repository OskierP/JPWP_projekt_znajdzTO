package pl.pakula.znajdzto;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;

/***
 * Klasa końcowego ekranu
 * @author Oskar Pakuła 175958
 */
public class EndScreen extends ScreenAdapter implements InputProcessor {
    /**
     * Pole odpowidające za płeć gracza
     */
    private final char plec;
    /**
     * Pole odpowiadające za tryb gry
     */
    private final String tryb;
    /**
     * Pole klasy ZnajdzTo
     */
    ZnajdzTo gra;

    /**
     * Konstruktor klasy EndScreen
     *
     * @param gra  instancja typu ZnajdzTo
     * @param plec płeć gracza
     * @param tryb tryb gry
     */
    public EndScreen(ZnajdzTo gra, char plec, String tryb) {
        this.gra = gra;
        this.plec = plec;
        this.tryb = tryb;
    }

    /**
     * Metoda tworząca
     */
    @Override
    public void show() {
        gra.koniec.play();
        Gdx.input.setInputProcessor(this);
    }

    /***
     * Metoda renderująca planszę
     */
    @Override
    public void render(float delta) {
        //Początek rysowania obiektów SpriteBatch
        gra.batch.begin();
        //Sprawdzenie płci gracza
        if (plec == 'K') {
            gra.batch.draw(new Texture("endK.png"), 250, 300);
        } else if (plec == 'M') {
            gra.batch.draw(new Texture("endM.png"), 250, 300);
        }
        //sprawdzanie trybu gry
        if (tryb.equals("Dla Daltonistów")) {
            if (plec == 'K') {
                gra.batch.draw(new Texture("endKD.png"), 250, 300);
            } else if (plec == 'M') {
                gra.batch.draw(new Texture("endMD.png"), 250, 300);
            }
        }
        //Koniec rysowania obiektów SpriteBatch
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
         * Sprawdzanie jaki klawisz został wciśnięty
         */
        if (keycode == Input.Keys.E) {
            gra.setScreen(new MainMenuScreen(gra, 0, plec));
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
