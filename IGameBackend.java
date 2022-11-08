public interface IGameLibraryBackend {
	/**
	 * Adds a new game to the Backend's database and is stored in the Red-Black
	 * tree.
	 *
	 * @param info the game information containing the name, year, genre, publisher
	 */
	public boolean addGame(String info);

	/**
	 * Deletes a game from the database of the Red-Black tree.
	 *
	 * @param name the name of the game to remove
	 */
	public boolean removeGame(String name);

	/**
	 * Search through all the games in the database and returns the games whose name
	 * matches the word inputted in.
	 *
	 * @param name the name of the game
	 * @return game the game object
	 */
	public IGame searchByName(String name);

}