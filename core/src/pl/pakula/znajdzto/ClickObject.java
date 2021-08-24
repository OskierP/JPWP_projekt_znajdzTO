package pl.pakula.znajdzto;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/***
 * Klasa pozwalająca tworzyć klikające obiekty
 * @author Oskar Pakuła 175958
 */
public abstract class ClickObject extends Rectangle {

    /**
     * Pole klasy Sprite
     */
    Sprite sprite;

    /**
     * Konstruktor klasy
     *
     * @param x      współrzędne poziome
     * @param y      współrzędne pionowe
     * @param sprite instancja typy Sprite
     * @param height wysokość obiektu
     * @param width  szerokość obiektu
     */
    public ClickObject(float x, float y, Sprite sprite, float height, float width) {
        super(x, y, width, height);

        this.sprite = sprite;
        this.sprite.setBounds(x, y, width, height);
    }

    /***
     * Metoda renderująca obiekt
     * @param batch instancja typu SpriteBatch
     */
    public void render(SpriteBatch batch) {

        sprite.draw(batch);
    }

    /***
     * Metoda wywołująca metodę click, jeżeli zawiera (w tym pzypadku) kursor myszki
     * @param x współrzędne poziome myszki
     * @param y współrzędne pionowe myszki
     */
    public void ifClick(float x, float y) {

        if (contains(x, y)) {
            click();
        }
    }

    /***
     * Metoda abstrakcyjna click
     */
    public abstract void click();


}

