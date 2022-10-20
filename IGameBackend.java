
public interface IGameLibraryBackend {
	/**
	 * Adds a new game to the Backend's database and is stored in the Red-Black
	 * tree.
	 * 
	 * @param game the game to add
	 */
	public void addGame(IGame game);

	/**
	 * Deletes a game from the database of the Red-Black tree.
	 * 
	 * @param game the game to remove
	 */
	public void removeGame(String name);

	/**
	 * Search through all the games in the database and returns the games whose name
	 * matches the word inputted in.
	 * 
	 * @param word the name of the game
	 * @return the name of the game which matches the word inputted in
	 */
	public String searchByNameWord(String word);

	/**
	 * This method can be used to set a filter for the published year contained in
	 * the search results. A game is only returned as a result for a search by year.
	 * 
	 * @param year The year the game was published
	 */
	public void setPublisherYear(int year);

}

