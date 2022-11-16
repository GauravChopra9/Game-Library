// --== CS400 Project One File Header ==--
// Name: Gaurav Chopra
// CSL Username: chopra
// Email: gmchopra@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Took 10 hours

/**
 * This class helps create game objects with name, publisher, year and genre attributes
 */
public class DWGame implements IGame{

    // Field attributes for the game object
    private String name;
    private String publisher;
    private String year;
    private String genre;

    /**
     * A constructor that creates a game object with name, publisher, year and genre attributes
     *
     * @param name - The name of the game
     * @param publisher - The publisher of the game
     * @param year - The release year of the game
     * @param genre - The genre of the game
     *
     */
    public DWGame (String name, String publisher, String year, String genre) {
        this.name = name;
        this.publisher = publisher;
        this.year = year;
        this.genre = genre;
    }

    /**
     * Returns the name of the game.
     * @return name of the game
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns a string that contains the publisher of the game
     * @return the publisher of the game
     */
    @Override
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * Returns the creation year of this game
     * @return creation year of game
     */
    @Override
    public String getYear() {
        return this.year;
    }

    /**
     * Returns the genre of this game
     * @return genre of game
     */
    @Override
    public String getGenre() {
        return this.genre;
    }
}

