// --== CS400 Project Two File Header ==--
// Name: Yanbin Chen
// CSL Username: yanbinc
// Email: ychen877@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>

// PLACEHOLDER	
/**
 * Placeholder class that implements IGame
 */
public class FDGame implements IGame {
	
	private String name;
	private String publisher;
	private String publishYear;
	private String genre;
	
	/**
	 * Constructor of FDGame class, assign value to each private field
	 *
	 * @param name   the name of this game
	 * @param publisher   the publisher of this game
	 * @param publishYear   the year this game published
	 * @param genre   the genre of this game
	 */
	public FDGame(String name, String publisher, String publishYear, String genre) {
		this.name = name;
		this.publisher = publisher;
		this.publishYear = publishYear;
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
     * as a single string
     * @return publisher names as single string
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
		return this.publishYear;
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

