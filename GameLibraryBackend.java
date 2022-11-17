// --== CS400 Project One File Header ==--
// Name: Ishan Bhutoria
// CSL Username: bhutoria
// Email: ibhutoria@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: none

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class implements the search, insert and delete capabilities of the
 * application using the red black tree implementation developed by the
 * Algorithm Engineer.
 * 
 * @author Ishan Bhutoria
 */
public class GameLibraryBackend implements IGameBackend {

	private IRemovingRedBlackTreeADT<AEGame> tree;

	public GameLibraryBackend(IRemovingRedBlackTreeADT<AEGame> tree2) {
		this.tree = tree2;
	}

	/**
	 * Adds a new game to the Backend's database and is stored in the Red-Black
	 * tree.
	 *
	 * @param name      the name of the game to add
	 * @param publisher publisher of the game
	 * @param year      the year of the game
	 * @param genre     the game genre
	 * @return true if the game is added, false otherwise
	 */
	@Override
	public boolean addGame(String name, String publisher, String year, String genre) {
		// Checking if any of the game info is null or not
		if (name == null || publisher == null || year == null || genre == null) {
			return false;
		}
		// Checking for the year being valid or not
		if (year.length() == 4) {
			for (int i = 0; i < 4; i++) {
				if (!Character.isDigit(year.charAt(i))) {
					return false;
				}
			}
		} else {
			return false;
		}

		AEGame game = new AEGame(name, publisher, year, genre);
		// Comparable gameToAdd = (Comparable) game;
		// RemovingRedBlackTree requiredGame = new RemovingRedBlackTree();
		// RedBlackTreeADTBackend requiredGame = new RedBlackTreeADTBackend();
		return tree.insert(game);
	}

	/**
	 * Deletes a game from the backend database of the Red-Black tree.
	 * 
	 * @param name the name of the game to remove
	 */
	@Override
	public boolean removeGame(String name) {
		// Checking if the name we're searching is for is null or not
		if (name == null) {
			return false;
		}

		// RemovingRedBlackTree game = new RemovingRedBlackTree();
		// RedBlackTreeADTBackend game = new RedBlackTreeADTBackend();

		// Removing the game using the name of the game by calling the remove from the
		// placeholder class
		return tree.remove(name);
	}

	/**
	 * Search through all the games in the database and returns the game whose name
	 * matches the name inputted in.
	 *
	 * @param name the name of the game
	 * @return game the game object
	 */
	@Override
	public IGame searchByName(String name) {
		// Checking if the name we're searching is for is null or not
		if (name == null) {
			return null;
		}

		// RemovingRedBlackTree game = new RemovingRedBlackTree();
		// RedBlackTreeADTBackend game = new RedBlackTreeADTBackend();

		// Using the find() method from the placeholder class to find the name of the
		// game
		return (IGame) tree.find(name);
	}

}