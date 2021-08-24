package pl.pakula.znajdzto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * Klasa opisują Menu
 *
 * @author Oskar Pakuła 175958
 */
public class MainMenuScreen extends ScreenAdapter implements InputProcessor {

    /**
     * easter egg
     */
    private final int eegg;
    /**
     * Pole odpowidające za płeć gracza
     */
    private final char plec;
    /**
     * Pole klasy ZnajdzTo
     */
    ZnajdzTo gra;
    /**
     * Lista obiektów klasy ClickObject
     */
    ArrayList<ClickObject> clickObjects = new ArrayList<>();

    /**
     * easter egg
     */
    float r = 0, g = 0, b = 0, a = 0;

    /**
     * Konstruktor klasy MainMenuScreen
     *
     * @param gra  instancja klasy ZnajdzTo
     * @param eegg   easter egg
     * @param plec płeć gracza
     */
    public MainMenuScreen(ZnajdzTo gra, int eegg, char plec) {
        this.gra = gra;
        this.eegg = eegg;
        this.plec = plec;
    }

    /**
     * Metoda tworząca
     */
    @Override
    public void show() {

        /*
         * Zakończenie odtwarzania muzyki
         */
        gra.tada.dispose();
        gra.koniec.dispose();
        /*
         * Dodanie elementów do klikania i trybów
         */
        clickObjects.add(new ClickObject(Gdx.graphics.getWidth() / 2f - 260, 600, new Sprite(new Texture("ZnajdzTO.png")), 124, 592) {
            @Override
            public void click() {

                if (eegg == 1) {
                    System.out.println("tryb DISCO został WYŁĄCZONY");
                    gra.setScreen(new MainMenuScreen(gra, 0, plec));
                    gra.easter.pause();

                }

                if (eegg == 0) {
                    System.out.println("tryb DISCO został WŁĄCZONY");
                    gra.setScreen(new MainMenuScreen(gra, 1, plec));
                    gra.easter.play();

                }
            }
        });

        clickObjects.add(new ClickObject(Gdx.graphics.getWidth() / 2f - 500, 420, new Sprite(new Texture("trybPodstawowy.png")), 169, 588) {
            @Override
            public void click() {
                gra.setScreen(new Game(gra, "Podstawowy", plec));
                System.out.println("tryb Podstawowy");
            }
        });

        clickObjects.add(new ClickObject(Gdx.graphics.getWidth() / 2f - 110, 260, new Sprite(new Texture("trybBraciDaltonow.png")), 165, 612) {
            @Override
            public void click() {
                gra.setScreen(new Game(gra, "Dla Daltonistów", plec));
                System.out.println("tryb Braci Daltonow");
            }
        });

        clickObjects.add(new ClickObject(Gdx.graphics.getWidth() / 2f - 450, 150, new Sprite(new Texture("trybDisco.png")), 138, 446) {
            @Override
            public void click() {
                gra.setScreen(new Warning(gra, plec));
                System.out.println("UWAGA!!! EPILEPSJA");
            }
        });

        clickObjects.add(new ClickObject(Gdx.graphics.getWidth() / 2f, 70, new Sprite(new Texture("trybKolor.png")), 143, 451) {
            @Override
            public void click() {
                gra.setScreen(new Game(gra, "Kolorowy", plec));
                System.out.println("tryb Kolorowy");
            }
        });

        clickObjects.add(new ClickObject(0, -5, new Sprite(new Texture("info.png")), 70, 70) {
            @Override
            public void click() {
                gra.setScreen(new Info(gra, plec));
                System.out.println("INFO");
            }
        });

        clickObjects.add(new ClickObject(80, -5, new Sprite(new Texture("zmianaPlci.png")), 70, 140) {
            @Override
            public void click() {
                gra.setScreen(new PodajPlec(gra));
                System.out.println("Zmiana płci");
            }
        });

        clickObjects.add(new ClickObject(230, -5, new Sprite(new Texture("wyjdz.png")), 70, 90) {
            @Override
            public void click() {
                Gdx.app.exit();
            }
        });

        Gdx.input.setInputProcessor(this);
    }

    /***
     * Metoda renderująca planszę
     */
    @Override
    public void render(float delta) {
        /*
         * Easter egg
         */
        if (eegg == 0) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        if (eegg == 1) {
            r = 1 - MathUtils.random(1f);
            g = 1 - MathUtils.random(1f);
            b = 1 - MathUtils.random(1f);
            a = 1 - MathUtils.random(1f);

            Gdx.gl.glClearColor(r, g, b, a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }

        gra.batch.begin();

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
