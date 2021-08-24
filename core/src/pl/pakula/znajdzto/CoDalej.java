package pl.pakula.znajdzto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/***
 * Klasa ekranu pytającego gracza o dalszym przebiegu gry
 * @author Oskar Pakuła 175958
 */
public class CoDalej extends ScreenAdapter implements InputProcessor {

    /**
     * Pole odpowidające za tryb gry
     */
    private final String tryb;
    /**
     * Pole odpowidające za poziom gry
     */
    private final int poziom;
    /**
     * Pole odpowidające za płeć gracza
     */
    private final char plec;
    /**
     * Pole klasy ZnajdzTo
     */
    ZnajdzTo gra;

    /***
     * Konstruktor klasy CoDalej
     * @param gra instancja klasy ZnajdzTo
     * @param tryb w jakim trybie działa gra
     * @param poziom który jest poziom gry
     * @param plec jaka jest płeć gracza
     */
    public CoDalej(ZnajdzTo gra, String tryb, int poziom, char plec) {

        this.gra = gra;
        this.tryb = tryb;
        this.poziom = poziom;
        this.plec = plec;
    }

    /**
     * Metoda tworząca
     */
    @Override
    public void show() {

        gra.font.getData().setScale(1);
        gra.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        gra.tada.play();

        Gdx.input.setInputProcessor(this);
    }

    /***
     * Metoda renderująca planszę
     */
    @Override
    public void render(float delta) {
        //Początek rysowania obiektów SpriteBatch
        gra.batch.begin();
        /*
         * Sprawdzanie płci gracza
         */
        if (plec == 'K') {

            if ((6 - poziom) == 5) {
                gra.batch.draw(new Texture("cDK5.png"), 220, 290);
            } else if ((6 - poziom) < 5 && (6 - poziom) > 1) {
                gra.batch.draw(new Texture("cDK4-2.png"), 220, 290);
            } else if ((6 - poziom) == 1) {
                gra.batch.draw(new Texture("cDK1.png"), 220, 290);
            }
        } else if (plec == 'M') {

            if ((6 - poziom) == 5) {
                gra.batch.draw(new Texture("cDM5.png"), 220, 290);
            } else if ((6 - poziom) < 5 && (6 - poziom) > 1) {
                gra.batch.draw(new Texture("cDM4-2.png"), 220, 290);
            } else if ((6 - poziom) == 1) {
                gra.batch.draw(new Texture("cDM1.png"), 220, 290);
            }
        }
        //Ustawienie koloru czczcionki
        gra.font.setColor(Color.BLACK);
        //Przesuwanie liczby poziomów, by zgadzała się z obrazami generującymi wyżej
        if ((6 - poziom) == 5) {
            gra.font.draw(gra.batch, Integer.toString(6 - poziom), 545, 380);
        } else if ((6 - poziom) < 5 && (6 - poziom) > 1) {
            gra.font.draw(gra.batch, Integer.toString(6 - poziom), 555, 380);
        } else if ((6 - poziom) == 1) {
            gra.font.draw(gra.batch, Integer.toString(6 - poziom), 557, 382);
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
         * Sprawdza jaki klawisz został wciśniętu
         */
        switch (keycode) {
            case Input.Keys.SPACE:
                int nastepny = poziom + 1;
                gra.setScreen(new Game(gra, nastepny, tryb, plec));
                break;

            case Input.Keys.E:
                gra.setScreen(new MainMenuScreen(gra, 0, plec));
                break;

            case Input.Keys.R:
                gra.setScreen(new Game(gra, poziom, tryb, plec));
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
