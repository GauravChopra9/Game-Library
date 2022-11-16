
// --== CS400 Project Two File Header ==--
// Name: Yanbin Chen
// CSL Username: yanbinc
// Email: ychen877@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class FrontendDeveloperTest {

	protected Scanner scan;
	protected FDGameBackend backend;
	protected GameFrontEnd frontend;
	protected TextUITester test;
	protected String output;

	/**
	 * Check if the application main menu prints out correctly
	 */
	@Test
	void test1() {
		// print the main menu and store the output printed to System.out
		test = new TextUITester("");
		scan = new Scanner(System.in);
		backend = new FDGameBackend();
		frontend = new GameFrontEnd(scan, backend);

		frontend.displayMainMenu();
		output = test.checkOutput();

		// check contents of the main menu
		assertTrue(output.contains("You are in the Main Menu:"));
		assertTrue(output.contains("1) Search by Name"));
		assertTrue(output.contains("2) Add Game"));
		assertTrue(output.contains("3) Delete Game"));
		assertTrue(output.contains("4) Exit Application"));
	}

	/**
	 * Check if the functionality of the command loop and if it exits correctly
	 */
	@Test
	void test2() {
		// run the command loop and store the System.out output
		test = new TextUITester("5\ne239eu\n4\n");
		scan = new Scanner(System.in);
		backend = new FDGameBackend();
		frontend = new GameFrontEnd(scan, backend);

		frontend.runCommandLoop();
		output = test.checkOutput();

		// check the beginning message of command loop
		assertEquals(output.substring(0, 40), "Welcome to the Game Library Application!");
		assertTrue(output.contains("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x"));

		// check the output if enter a string instead of a number
		assertTrue(output.contains("Invalid input"));

		// check the exit message
		assertTrue(output.contains("Goodbye!"));
	}

	/**
	 * Check the functionality of name search method and function
	 */
	@Test
	void test3() {
		// run the command loop and store the System.out output
		test = new TextUITester("1\nCall of Duty 4: Modern Warfare\n1\nPac-Man\n" + "1\nSomething wrong\n"
				+ "1\nMario Kart DS\n1\nCalling\n4\n");
		scan = new Scanner(System.in);
		backend = new FDGameBackend();
		frontend = new GameFrontEnd(scan, backend);
		
		backend.addGame("Mario Kart DS", "Nintendo", "2005", "Racing");
		backend.addGame("Call of Duty 4: Modern Warfare", "Activision", "2007", "Shooter");
		backend.addGame("Pac-Man", "Atari", "1982", "Puzzle");
		backend.addGame("Calling", "Konami Digital Entertainment", "2009", "Adventure");

		frontend.runCommandLoop();
		output = test.checkOutput();

		// check the output of the correct name input
		assertTrue(output.contains("\"Mario Kart DS\" published by Nintendo in 2005, Genre: Racing"));
		assertTrue(output
				.contains("\"Call of Duty 4: Modern Warfare\" published by Activision in 2007, Genre: Shooter"));
		assertTrue(output.contains("\"Pac-Man\" published by Atari in 1982, Genre: Puzzle"));
		assertTrue(output
				.contains("\"Calling\" published by Konami Digital Entertainment in 2009, Genre: Adventure"));

		// check the error message of invalid input
		assertTrue(output.contains("Invalid Input: the game cannot be found!"));
		assertTrue(!output.contains("Something wrong"));
	}
	
	/**
	 * Check the functionality of add game function
	 */
	@Test
	void test4() {
		// run the command loop and store the System.out output
		test = new TextUITester("2\nWallace & Gromit in Project Zoo\nBAM! Entertainment\n2003\nPlatform\n"
				+ "2\nMario Kart DS\nNintendo\n200\nRacing\n"
				+ "2\nCall of Duty 4: Modern Warfare\nActivision\n2007\nShooter\n4\n");
		scan = new Scanner(System.in);
		backend = new FDGameBackend();
		frontend = new GameFrontEnd(scan, backend);
		
		frontend.runCommandLoop();
		output = test.checkOutput();
		
		// check the output of correct data input
		assertTrue(output.contains("You have added a new game: "
				+ "\"Wallace & Gromit in Project Zoo\" published by BAM! Entertainment in 2003,"
				+ " Genre: Platform"));
		assertTrue(output.contains("You have added a new game: "
				+ "\"Call of Duty 4: Modern Warfare\" published by Activision in 2007, Genre: Shooter"));
		
		// check the error message of invalid input
		assertTrue(output.contains("Invalid Input!"));
		assertTrue(!output.contains("Mario Kart DS") || !output.contains("Nintendo") ||
				!output.contains("200") || !output.contains("Racing"));
	}
	
	/**
	 * Check the output of delete game application
	 */
	@Test
	void test5() {
		// run the command loop and store the System.out output
		test = new TextUITester("3\nCall of Duty 4: Modern Warfare\n3\nPac-Man\n" + "3\nWrong name\n"
				+ "3\nMario Kart DS\n3\nCalling\n4\n");
		scan = new Scanner(System.in);
		backend = new FDGameBackend();
		frontend = new GameFrontEnd(scan, backend);
		
		backend.addGame("Mario Kart DS", "Nintendo", "2005", "Racing");
		backend.addGame("Call of Duty 4: Modern Warfare", "Activision", "2007", "Shooter");
		backend.addGame("Pac-Man", "Atari", "1982", "Puzzle");
		backend.addGame("Calling", "Konami Digital Entertainment", "2009", "Adventure");
		
		frontend.runCommandLoop();
		output = test.checkOutput();

		// check the output of the correct name input
		assertTrue(output.contains("You are in the Delete Game Menu:"));
		assertTrue(output.contains("Please enter the name of game to delete: "));
		assertTrue(output.contains("Deleted successfully!"));
		
		// check the error message of invalid input
		assertTrue(output.contains("Invalid Input: the game cannot be found!"));
		assertTrue(!output.contains("Wrong name"));
	}

}
