// --== CS400 Project Two File Header ==--
// Name: Yanbin Chen
// CSL Username: yanbinc
// Email: ychen877@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>

// PLACEHOLDER	
/**
 * Placeholder class that implements IGameBackend
 */
public class FDGameBackend implements IGameBackend {

	/**
	 * Adds a new game to the Backend's database and is stored in the Red-Black
	 * tree.
	 * 
	 * @param game the game to add
	 */
	@Override
	public boolean addGame(String name, String publisher, String year, String genre) {
		// check if the data are empty and if year has 4 digits
		if (name != null && publisher != null && year != null && genre != null 
				&& year.length() == 4) {
			return true;
		}
		return false;
	}

	/**
	 * Deletes a game from the database of the Red-Black tree.
	 * 
	 * @param game to remove
	 */
	@Override
	public boolean removeGame(String name) {
		if (name != null && (name.equals("Mario Kart DS") 
				|| name.equals("Call of Duty 4: Modern Warfare") || name.equals("Pac-Man") 
				|| name.equals("Calling"))) {
				return true;
		}
		return false;
	}

	/**
	 * Search through all the games in the database and returns the games whose
	 * 
	 * @param name game name
	 * @return game
	 */
	@Override
	public IGame searchByName(String name) {
		IGame game1 = new FDGame("Mario Kart DS", "Nintendo", "2005", "Racing");
		IGame game2 = new FDGame("Call of Duty 4: Modern Warfare", "Activision", "2007", "Shooter");
		IGame game3 = new FDGame("Pac-Man", "Atari", "1982", "Puzzle");
		IGame game4 = new FDGame("Calling", "Konami Digital Entertainment", "2009", "Adventure");
		
		if (name != null) {
			if (name.equals("Mario Kart DS")) {
				return game1;
			} else if (name.equals("Call of Duty 4: Modern Warfare")) {
				return game2;
			} else if (name.equals("Pac-Man")) {
				return game3;
			} else if (name.equals("Calling")) {
				return game4;
			}
		}
		return null;
	}

}
