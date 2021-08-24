/* Tutaj znajduje się generacja okna */
package pl.pakula.znajdzto.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.pakula.znajdzto.ZnajdzTo;

/**
 * Klasa GŁÓWNA, która pozwala na utworzenie okna gry
 *
 * @author Oskar Pakuła 175958
 */
public class ZnajdzTO {
    /**
     * main
     */
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        /* Tytuł na oknie */
        config.title = "znajdźTO";
        /* Szerokość okna */
        config.width = 1090;
        /* Wysokość okna */
        config.height = 730;
        /* Czy okno może być rozciągane */
        config.resizable = false;

        new LwjglApplication(new ZnajdzTo(), config);
    }
}
