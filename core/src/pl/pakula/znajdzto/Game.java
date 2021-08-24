package pl.pakula.znajdzto;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * Klasa tworząca planszę gry
 *
 * @author Oskar Pakuła 175958
 */
public class Game extends ScreenAdapter implements InputProcessor {

    /**
     * Pole odpowidające za tryb gry
     */
    private final String tryb;
    /**
     * zmienna odpowiedająca za płeć gracza
     */
    private final char plec;
    /**
     * Pole klasy Color
     */
    Color kolor;
    /**
     * Pole klasy ZnajdzTo
     */
    ZnajdzTo gra;
    /**
     * Pole odpowidające za współrzędną poziomą
     */
    float x = 0;
    /**
     * Pole odpowidające za współrzędną poionową
     */
    float y = 0;
    /**
     * Pole odpowidające za zatrzymanie zegara
     */
    boolean zegarStop = false;
    /**
     * Pole odpawiadjące za komunikat o złym trafieniu
     */
    boolean zlyClick = false;
    /**
     * Lista obiektów GameFigure
     */
    ArrayList<GameFigure> gameFigures = new ArrayList<>();
    /**
     * Pole odpawiadające za drugi zegar (komunikat o błędnym trafieniu)
     */
    private float blednaOdpCzas = 0;
    /**
     * Pole odpowidające za poziom gry
     */
    private int poziom = 1;
    /**
     * Pole pomocnicze; do oblicznia kolejnego poziomu
     */
    private int nastepnyPoziom = 0;
    /**
     * Zmienna odpowiadająca za sekundy zegara
     */
    private float sek = 0;
    /**
     * Zmienna odpowiadająca za minuty zegara
     */
    private int min = 0;

    /**
     * Metoda klasy Game
     *
     * @param gra  instancja klasy ZnajdzTo
     * @param tryb tryb gry
     * @param plec płeć gracza
     */
    public Game(ZnajdzTo gra, String tryb, char plec) {
        this.tryb = tryb;
        this.gra = gra;
        this.plec = plec;
    }

    /**
     * Metoda klasy Game
     *
     * @param gra    instancja klasy ZnajdzTo
     * @param tryb   tryb gry
     * @param plec   płeć gracza
     * @param poziom poziom gry
     */
    public Game(ZnajdzTo gra, int poziom, String tryb, char plec) {
        this.gra = gra;
        this.poziom = poziom;
        this.tryb = tryb;
        this.plec = plec;
    }

    /**
     * Metoda tworząca
     */
    @Override
    public void show() {
        //Koniec odtwarzania muzyki
        gra.tada.dispose();
        //Losowanie figury
        int figura = MathUtils.random(1, 7);
        //Sprawdzanie trybu gry
        if ("Dla Daltonistów".equals(tryb)) {
            figura = 1;
            //Losowanie kolorów
            kolor = new Color(MathUtils.random(0.9f), MathUtils.random(0.9f), MathUtils.random(0.9f), 1);
        } else {
            //Losowanie koloru
            float jedenKolor = MathUtils.random(0.5f, 0.6f);
            kolor = new Color(jedenKolor, jedenKolor, jedenKolor, 1);
        }

        /*
         * Dodadwanie figur do tabilcy
         */
        for (int i = 0; i < 18 * poziom; i++) {
            for (int j = 2 * poziom; j < 12 * poziom; j++) {

                /*
                 * Pole odpowiadające za przerwy pomiędzy figurami
                 */
                float spacing = 8;
                x = spacing + 60f / poziom * i;
                y = spacing + 60f / poziom * j;

                //wybieranie figur
                gameFigures.add(new GameFigure(x, y, figura, poziom, kolor, tryb));
            }
        }
        /*
         * Losowanie niepasującego elementu
         */
        gameFigures.get(MathUtils.random(gameFigures.size())).select();

        gra.font.getData().setScale(1);
        gra.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Gdx.input.setInputProcessor(this);
    }

    /***
     * Metoda renderująca planszę
     */
    @Override
    public void render(float delta) {
        /*
         * Czysczenie ekranu oraz malowanie tła
         */
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        nastepnyPoziom = poziom + 1;

        /*
         * Początek rysowania obiektów ShapeRenderer
         */
        gra.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        /*
         * Rysowanie figur
         */
        for (GameFigure gameFigure : gameFigures) {
            gameFigure.render(gra.shapeRenderer);
        }


        int zegarPolozenieY = 65;
        int zegarPolozenieX = 60 * 15 + 25;
        /*
         * Koniec rysowania obiektów ShapeRenderer
         */
        gra.shapeRenderer.end();

        /*
         * Początek rysowanai obiektów SpriteBatch
         */
        gra.batch.begin();
        /*
         * Pasek dolny
         */
        gra.batch.draw(new Texture("bar.png"), 0, 0);

        gra.font.setColor(Color.BLACK);


        /*
         * Zegar i ustawianie cyfr
         */
        if (min < 10) {
            gra.font.draw(gra.batch, "0", zegarPolozenieX + 18, zegarPolozenieY);
            gra.font.draw(gra.batch, Integer.toString(min), zegarPolozenieX + 35, zegarPolozenieY);
        } else {
            gra.font.draw(gra.batch, Integer.toString(min), zegarPolozenieX + 20, zegarPolozenieY);
        }

        gra.font.draw(gra.batch, ":", zegarPolozenieX + 55, zegarPolozenieY);

        if (sek < 10) {
            gra.font.draw(gra.batch, "0", zegarPolozenieX + 65, zegarPolozenieY);
            gra.font.draw(gra.batch, Float.toString(sek), zegarPolozenieX + 83, zegarPolozenieY);
        } else {
            gra.font.draw(gra.batch, Float.toString(sek), zegarPolozenieX + 65, zegarPolozenieY);
        }
        /*
         * Wyświetlanie trybu oraz poziomu
         */
        gra.font.setColor(0.026f, 0.29f, 0.57f, 1);
        gra.font.draw(gra.batch, Integer.toString(poziom), 140, 105);
        gra.font.draw(gra.batch, tryb, 20, 35);

        //licznik
        if (!zegarStop) {
            sek += Gdx.graphics.getRawDeltaTime();
        }

        if (sek >= 60) {
            min++;
            sek = 0;
        }
        /*
         * Koniec Zegara
         */
        //Komunikat o błędnej odpowiedzi
        if (zlyClick) {
            blednaOdpCzas++;
            if (blednaOdpCzas >= 25) {
                blednaOdpCzas = 0;
                zlyClick = false;
            }
            if (blednaOdpCzas >= 5) {
                gra.error.play();
            }

            gra.batch.draw(new Texture("blednaOdp.png"), 0, 0);
        }
        /*
         * Koniec rysowania obiektów SpriteBatch
         */
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
        //sterowanie
        switch (keycode) {

            case Input.Keys.R:
                gra.setScreen(new Game(gra, poziom, tryb, plec));
                break;

            case Input.Keys.E:
                gra.setScreen(new MainMenuScreen(gra, 0, plec));
                break;

//		case Input.Keys.N:   // do zabawy dla programisty
//			gra.setScreen(new Game(gra, nastepnyPoziom, tryb, plec));
//		break;

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
        /*
         * Przeszukiwanie elementów i sprawdzanie czy kursor się znajdował w danym miejscu
         */
        boolean czyClick = false;

        for (GameFigure gameFigure : gameFigures) {
            if (gameFigure.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                if (gameFigure.click()) {

                    czyClick = true;
                    zegarStop = true;

                    if (nastepnyPoziom == 7) {
                        gra.setScreen(new EndScreen(gra, plec, tryb));
                        break;
                    }

                    gra.setScreen(new CoDalej(gra, tryb, poziom, plec));
                }
            }
        }
        zlyClick = !czyClick;
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
