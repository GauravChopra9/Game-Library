// --== CS400 Project One File Header ==--
// Name: Ishan Bhutoria
// CSL Username: bhutoria
// Email: ibhutoria@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: none

//placeholder class

public class GameBD implements Comparable, IGame {

	private String name;
	private String year;
	private String publisher;
	private String genre;

	public GameBD(String name, String year, String genre, String publisher) {
		this.name = name;
		this.year = year;
		this.genre = genre;
		this.publisher = publisher;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPublisher() {
		return this.publisher;
	}

	@Override
	public String getYear() {
		return this.year;
	}

	@Override
	public String getGenre() {
		return this.genre;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

}
