package pl.pakula.znajdzto;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Klasa rysująca figury
 *
 * @author Oskar Pakuła 175958
 */
public class GameFigure extends Rectangle {

    /**
     * Pole odpowidające za tryb gry
     */
    private final String tryb;
    /**
     * Pole odpowidające za poziom gry
     */
    private final float poziom;
    /**
     * Pole odpowidające za losowani figury niepasującego elementu
     */
    int wylosowanaFigura = 1;
    /**
     * Pole odpowidające za oznaczenie elementu niepasującego
     */
    boolean czyWylosowane = false;
    /**
     * Pole odpowidające za rozmiary pola wokół figury
     */
    float rozmiaryPola = 50;
    /**
     * Pole odpowidające za losowanie kolorów
     */
    float r = 1 - MathUtils.random(0.3f, 0.9f), g = 1 - MathUtils.random(0.3f, 0.9f), b = 1 - MathUtils.random(0.3f, 0.9f);
    /**
     * Pole odpowidające za wylosowanie figuty
     */
    private int figura;
    /**
     * Obiekt klasy Color
     */
    private Color kolor;

    /**
     * Konstruktor klasy GameObject
     *
     * @param x      współrzędna pozioma
     * @param y      współrzędna pionowa
     * @param figura rodzaj figury
     * @param poziom rodzaj poziomu
     * @param kolor  kolor figury
     * @param tryb   tryb gry
     */
    public GameFigure(float x, float y, int figura, float poziom, Color kolor, String tryb) {

        super();

        this.tryb = tryb;
        this.poziom = poziom;
        this.figura = figura;

        this.x = x;
        this.y = y;

        this.kolor = kolor;

        width = rozmiaryPola / poziom;
        height = rozmiaryPola / poziom;
        rozmiaryPola = rozmiaryPola / poziom;
    }

    /***
     * Metoda renderująca obiekty ShapeRenderer
     * @param shape obiekty klasy ShapeRenderer
     */
    public void render(ShapeRenderer shape) {
        /*
         * Sprawdzanie trybu gry
         */

        if (tryb.equals("Disco")) {
            r = MathUtils.random(0.1f, 0.8f);
            g = MathUtils.random(0.1f, 0.8f);
            b = MathUtils.random(0.1f, 0.8f);
            kolor = new Color(r, g, b, 1);
        }
        if (tryb.equals("Kolorowy")) {
            kolor = new Color(r, g, b, 1);
        }
        /*
         * Ustawianie koloru figury
         */
        shape.setColor(kolor);


        /*
         * Sprawdzanie jaka figura ma być narysowana
         */
        switch (figura) {

            case 1: //koło
                shape.circle(x + rozmiaryPola / 2, y + rozmiaryPola / 2, rozmiaryPola / 2);
                break;

            case 2: //prostokąt
                shape.rect(x, y, width, height);
                break;

            case 3: //trójkąt
                shape.triangle(x, y + 11 / poziom, x + width, y + 11 / poziom, x + width / 2, y + height);
                break;

            case 4: //klepsydra
                shape.triangle(x + width, y + height, x, y + height, x + width / 2, y);
                shape.triangle(x, y, x + width, y, x + width / 2, y + height);
                break;

            case 5: //odwrócony trójką
                shape.triangle(x + width, y + height - 11 / poziom, x, y + height - 11 / poziom, x + width / 2, y);
                break;

            case 6:  //pierścień
                shape.circle(x + rozmiaryPola / 2, y + rozmiaryPola / 2, rozmiaryPola / 2);
                shape.setColor( 0, 0, 0, 1);
                shape.circle(x + rozmiaryPola / 2, y + rozmiaryPola / 2, rozmiaryPola / (50f / 15));
                break;

            case 7: //gwiazda dawida
                shape.triangle(x, y + 11 / poziom, x + width, y + 11 / poziom, x + width / 2, y + height);
                shape.triangle(x + width, y + height - 11 / poziom, x, y + height - 11 / poziom, x + width / 2, y);
                break;

        }
    }


    /**
     * Metoda click wyświetlająca w konsoli słowa
     */
    public boolean click() {

        if (czyWylosowane) {
            System.out.println("WOW, udało się");
            return true;
        }
        return false;
    }


    /**
     * Metoda opisująca element niepasujący
     */
    public void select() {

        /*
         * Losowanie figury elementu niepasującego
         */
        while (true) {
            wylosowanaFigura = MathUtils.random(1, 7);

            if (wylosowanaFigura != figura) {
                figura = wylosowanaFigura;
                break;
            }
        }

        /*
         * Nadawanie kolory elementowi niepasującemu
         */
        kolor = kolor.cpy().add(0, 0, 0, 1);

        /*
         * Sprawdzanie trybu gry
         */
        if (tryb.equals("Dla Daltonistów")) {
            figura = 1;
            kolor = kolor.cpy().add(0, 0.15f, 0, 1);
        }
        czyWylosowane = true;
    }
}
