// --== CS400 Project One File Header ==--
// Name: Ishan Bhutoria
// CSL Username: bhutoria
// Email: ibhutoria@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: none

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of our GameLibraryBackend() method. The
 * tests include the testing of the addGame(), removeGame(), and searchByName()
 * methods. The last two tests reviews the code of Data Wrangler.
 * 
 * @author Ishan Bhutoria
 */
public class BackendDeveloperTests {

	/**
	 * test1() tests the working of our addGame() method with valid game arguments.
	 */
	@Test
	public void test1() {
		GameLibraryBackend test = new GameLibraryBackend(new RemovingRedBlackTree());
		String name = "FIFA";
		String publisher = "Microsoft";
		String year = "2022";
		String genre = "Adventure";

		assertTrue(test.addGame(name, publisher, year, genre));
	}

	/**
	 * test2() tests the working of our addGame() method with various invalid and
	 * one valid year arguments.
	 */
	@Test
	public void test2() {
		GameLibraryBackend test = new GameLibraryBackend(new RemovingRedBlackTree());
		String name = "FIFA";
		String publisher = "Microsoft";
		// invalid year
		String year = "2i22";
		String genre = "Adventure";

		assertFalse(test.addGame(name, publisher, year, genre));

		// invalid year
		year = null;
		assertFalse(test.addGame(name, publisher, year, genre));

		// invalid year
		year = "hieui";
		assertFalse(test.addGame(name, publisher, year, genre));

		// invalid year
		year = "230656";
		assertFalse(test.addGame(name, publisher, year, genre));

		// invalid year
		year = "2025";
		assertTrue(test.addGame(name, publisher, year, genre));
	}

	/**
	 * test3() tests the functionality of our addGame() with checking for each name,
	 * publisher, and genre being null.
	 */
	@Test
	public void test3() {
		GameLibraryBackend test = new GameLibraryBackend(new RemovingRedBlackTree());
		String name = null;
		String publisher = "Microsoft";
		String year = "2022";
		String genre = "Adventure";

		assertFalse(test.addGame(name, publisher, year, genre));

		name = "FIFA";
		genre = null;

		assertFalse(test.addGame(name, publisher, year, genre));

		genre = "Adventure";
		publisher = null;

		assertFalse(test.addGame(name, publisher, year, genre));
	}

	/**
	 * test4() tests the functionality of our removeGame() method using our
	 * placeholder classes called from the removeGame() method.
	 */
	@Test
	public void test4() {
		GameLibraryBackend test = new GameLibraryBackend(new RemovingRedBlackTree());
		test.addGame("FIFA", "ISHAN", "2022", "Gaurav");

		String name = "FIFA";
		assertTrue(test.removeGame(name));

		name = null;
		assertFalse(test.removeGame(name));
	}

	/**
	 * test5() tests the functionality of our searchByName() method using the
	 * placeholder classes called from the searchByName() method.
	 */
	@Test
	public void test5() {
		GameLibraryBackend test = new GameLibraryBackend(new RemovingRedBlackTree());
		test.addGame("FIFA", "ISHAN", "2022", "Gaurav");

		String name = "FIFA";
		assertEquals(test.searchByName(name).getName(), "FIFA");

		name = null;
		assertEquals(test.searchByName(name), null);
	}

	/**
	 * integrationTest6() tests the functionality of our code in general and checks
	 * whether it returns the correct result.
	 */
	@Test
	public void integrationTest6() {
		GameLibraryBackend test = new GameLibraryBackend(new RemovingRedBlackTree());

		String name = null;
		String publisher = "Microsoft";
		String year = "2015";
		String genre = null;

		// Checking if the code works with invalid name and genre
		assertFalse(test.addGame(name, publisher, year, genre));

		name = "FIFA";
		genre = "Adventure";

		// Checking if the code works with valid name, publisher, year, and genre
		assertTrue(test.addGame(name, publisher, year, genre));

		// Checking if removing a game not present in the library returns false
		assertFalse(test.removeGame("Ishan"));

		// Checking if removing a game present in the library returns true
		assertTrue(test.removeGame("FIFA"));
	}

	/**
	 * integrationTest7() tests the functionality of our code in general and checks
	 * whether it returns the correct result.
	 */
	@Test
	public void integrationtest7() {
		GameLibraryBackend test = new GameLibraryBackend(new RemovingRedBlackTree());

		String name = "FIFA";
		String publisher = "Microsoft";
		String year = "2015";
		String genre = "Adventure";

		// Checking if addGame() works with valid name, publisher, year, and genre
		test.addGame(name, publisher, year, genre);

		// Checking if searchByName() works with a name of a game present in the library
		assertEquals(test.searchByName(name).getName(), "FIFA");

		// Checking if removeGame() works with a name of a game not present in the
		// library
		assertFalse(test.removeGame("Ishan"));

		// Checking if removeGame() works with a name of a game present in the library
		assertTrue(test.removeGame("FIFA"));
	}

	/**
	 * This tests reviews the code of the Data Wrangler.
	 */
	@Test
	public void codeReviewOfDataWrangler1() {
		List<IGame> gameList;
		// Creating a GameLoader object
		IGameLoader obj = new GameLoader();
		// Creating a gameList instance
		gameList = new ArrayList<>();
		try {
			// Initializing gameList by filling it with Game objects loaded from a valid xml
			// file path
			gameList = obj.loadGames("vgsales2.xml");
		}

		// Checking if a FileNotFoundException is thrown and caught
		catch (FileNotFoundException f) {
			// FileNotFoundException is not expected in this case and returns false if
			// the exception is caught
			assertEquals("True", "False");
		}

		// Looping through the entire gameList
		for (int i = 0; i < gameList.size(); i++) {
			// Checking if game publisher is not blank
			assertEquals(gameList.get(i).getPublisher().isBlank(), false);
			// Checking if game genre is not blank
			assertEquals(gameList.get(i).getGenre().isBlank(), false);
			// Checking if game name is not blank
			assertEquals(gameList.get(i).getName().isBlank(), false);
			// Checking if game year is not blank
			assertEquals(gameList.get(i).getYear().isBlank(), false);
		}
	}

	/**
	 * This test checks the functionality of the Data Wrangler's code.
	 */
	@Test
	void codeReviewOfDataWrangler2() {
		List<IGame> gameList;
		GameLoader obj = new GameLoader();
		gameList = new ArrayList<>();
		// Checking if a FileNotFoundException is thrown when the file name in invalid
		try {
			gameList = obj.loadGames("vgsales67.xml");
		}

		catch (FileNotFoundException f) {
			assertEquals("True", "True");
		}

		catch (Exception e) {
			assertEquals("True", "false");
		}

		// Testing if no Exception is thrown when file name put is valid
		try {
			gameList = obj.loadGames("vgsales2.xml");
		}

		catch (FileNotFoundException f) {
			assertEquals("True", "false");
		}

		catch (Exception e) {
			assertEquals("True", "false");
		}
		// Testing if the name of the Game object loaded is correct for the first 3 and
		// last 3 in GameList
		assertEquals(gameList.get(0).getName(), "Wii Sports");
		assertEquals(gameList.get(1).getName(), "Super Mario Bros.");
		assertEquals(gameList.get(2).getName(), "Mario Kart Wii");
		assertEquals(gameList.get(gameList.size() - 3).getName(), "SCORE International Baja 1000: The Official Game");
		assertEquals(gameList.get(gameList.size() - 2).getName(), "Know How 2");
		assertEquals(gameList.get(gameList.size() - 1).getName(), "Spirits & Spells");
	}

}
