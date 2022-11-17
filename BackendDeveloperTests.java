// --== CS400 Project One File Header ==--
// Name: Ishan Bhutoria
// CSL Username: bhutoria
// Email: ibhutoria@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: none

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of our GameLibraryBackend() method. The
 * tests include the testing of the addGame(), removeGame(), and searchByName()
 * methods.
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
		assertEquals(test.searchByName(name).getName(),"FIFA");

		name = null;
		assertEquals(test.searchByName(name), null);
	}
}
