    /* Tutaj znajduje się cały kod gry "Znajdź TO!" */
    package pl.pakula.znajdzto;

    import com.badlogic.gdx.Game;
    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.audio.Music;
    import com.badlogic.gdx.graphics.g2d.BitmapFont;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

    /**
     * Klasa tworząca, umożliwająca przełączanie na inne ekrany
     *
     * @author Oskar Pakuła 175958
     */
    public class ZnajdzTo extends Game {

        /**
         * Pole klasy SpriteBatch
         */
        SpriteBatch batch;
        /**
         * Pole klasy ShapeRenderer
         */
        ShapeRenderer shapeRenderer;
        /**
         * Pole klasy BitmapFont
         */
        BitmapFont font;
        /**
         * Pole klasy Music
         */
        Music easter;
        /**
         * Pole klasy Music
         */
        Music tada;
        /**
         * Pole klasy Music
         */
        Music koniec;
        /**
         * Pole klasy Music
         */
        Music error;

        /**
         * Metoda tworząca potrzebne obiekty poszczególnych klas
         */
        @Override
        public void create() {

            batch = new SpriteBatch();
            shapeRenderer = new ShapeRenderer();
            font = new BitmapFont(Gdx.files.internal("font/*.fnt"));

            /*
             * Tworzenie muzyki
             */
            easter = Gdx.audio.newMusic(Gdx.files.internal("dubstep.mp3"));
            easter.setLooping(true);
            easter.setVolume(0.8f);
            tada = Gdx.audio.newMusic(Gdx.files.internal("tada.mp3"));
            tada.setVolume(0.2f);
            koniec = Gdx.audio.newMusic(Gdx.files.internal("fanfare.mp3"));
            koniec.setVolume(0.5f);
            error = Gdx.audio.newMusic(Gdx.files.internal("error.mp3"));
            error.setVolume(0.15f);

            /*
             * Przejście do kolejnego ekrany
             */
            setScreen(new PodajPlec(this));
        }

        /**
         * "Pozbycie się" nie używanych obiektów
         */
        @Override
        public void dispose() {
            batch.dispose();
            shapeRenderer.dispose();
            font.dispose();
            easter.dispose();
            tada.dispose();
            koniec.dispose();
        }
    }