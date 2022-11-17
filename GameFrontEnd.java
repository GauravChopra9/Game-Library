// --== CS400 Project Two File Header ==--
// Name: Yanbin Chen
// CSL Username: yanbinc
// Email: ychen877@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>

import java.util.Scanner;

public class GameFrontEnd implements IGameFrontEnd {

	private Scanner userInputScanner;
	private IGameBackend backend;
	private String indent = "          ";

	/**
	 * Constructor of front-end Game library class, assign value to each private
	 * field
	 * 
	 * @param userInputScanner read user input
	 * @param backend          read the back-end game library
	 */
	public GameFrontEnd(Scanner userInputScanner, IGameBackend backend) {
		this.userInputScanner = userInputScanner;
		this.backend = backend;
	}

	/**
	 * This method starts the command loop for the front-end, and will terminate
	 * when the user exits the application.
	 */
	@Override
	public void runCommandLoop() {
		// Printing the beginning statements
		System.out.println("Welcome to the Game Library Application!");
		System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
		boolean valid_user = true;
		int input = 0;
		while (valid_user) {
			displayMainMenu();
			// read user's input and check if it's valid
			try {
				input = Integer.parseInt(userInputScanner.nextLine());
				if (input < 1 || input > 5) {
					System.out.println("Invalid input!");
					continue;
				}
			} catch (Exception e) { 
				System.out.println("Invalid input!");
			}

			if (input == 1) {
				// enter the search by name menu if input is 1
				nameSearch();
			} else if (input == 2) {
				// add a game to the library if input is 3

				// request for name of the new game
				System.out.println("You are in the Add New Game Menu:");
				System.out.println(indent + "Please enter the name of this new game: ");
				String name = null;
				try {
					name = userInputScanner.nextLine();
				} catch (Exception e) {
					System.out.println("Unexpected Error: read user input game name");
				}

				// request for publisher of the new game
				System.out.println(indent + "Please enter the publisher of this new game: ");
				String publisher = null;
				try {
					publisher = userInputScanner.nextLine();
				} catch (Exception e) {
					System.out.println("Unexpected Error: read user input game publisher");
				}

				// request for publish year of the new game
				System.out.println(indent + "Please enter the publish year of this new game: ");
				String year = null;
				try {
					year = userInputScanner.nextLine();
				} catch (Exception e) {
					System.out.println("Unexpected Error: read user input game publish year");
				}

				// request for genre of the new game
				System.out.println(indent + "Please enter the genre of this new game: ");
				String genre = null;
				try {
					genre = userInputScanner.nextLine();
				} catch (Exception e) {
					System.out.println("Unexpected Error: read user input game genre");				
				}

				// call the back-end add game method
				if (backend.addGame(name, publisher, year, genre)) {
					System.out.println("You have added a new game: \"" + name + "\"" + " published by " 
				            + publisher + " in " + year + ", Genre: " + genre);
				} else {
					System.out.println("Invalid Input!");
					continue;
				}

			} else if (input == 3) {
				// request for name of the game to remove
				System.out.println("You are in the Delete Game Menu:");
				System.out.print(indent + "Please enter the name of game to delete: ");
				String name = "";
				try {
					name = userInputScanner.nextLine();
				} catch (Exception e) {
					System.out.println("Unexpected Error: read user input game name");
					continue;
				}
				System.out.println();
				// call the back-end remove game method
				if (backend.removeGame(name)) {
					System.out.println("Deleted successfully!");
				} else {
					System.out.println("Invalid Input: the game cannot be found!");
					continue;
				}

			} else if (input == 4) {
				// Exiting the application and printing Goodbye! if the input is 5
				System.out.println("Goodbye!");
				valid_user = false;
			}
			
			input = 0;
		}
	}

	/**
	 * Prints command options to the screen (System.out)
	 */
	@Override
	public void displayMainMenu() {
		System.out.println();
		System.out.println("You are in the Main Menu:");
		System.out.println(indent + "1) Search by Name\n" + indent + "2) Add Game\n" + indent + 
				"3) Delete Game\n" + indent + "4) Exit Application");
	}

	/**
	 * Display a list of games
	 * 
	 * @param games the game list
	 */
	@Override
	public void displayGame(IGame game) {
		// get the name, publisher, publish year and genre for all the games in the list
		String name = game.getName();
		String publisher = game.getPublisher();
		String publishYear = game.getYear();
		String genre = game.getGenre();

		System.out.println(
				"\"" + name + "\"" + " published by " + publisher + " in " + publishYear + ", Genre: " + genre);
	}

	/**
	 * Reads game names from user input (System.in), displays searching results
	 */
	@Override
	public void nameSearch() {
		System.out.println("You are in the Search by Name Menu:");
		System.out.println(indent + "Enter an exact word to search for in game name: ");
		String gameName = "";
		try {
			gameName = userInputScanner.nextLine();
		} catch (Exception e) {
			System.out.println("Unexpected Error: read user input game name");
		}

		IGame game = backend.searchByName(gameName);
		if (game == null) {
			System.out.println("Invalid Input: the game cannot be found!");
		} else {
			// display the game data
			displayGame(game);
		}
	}

}
