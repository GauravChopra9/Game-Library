public interface IGameBackend {
	/**
	 * Adds a new game to the Backend's database and is stored in the Red-Black
	 * tree.
	 *
	 * @param name      the name of the game to add
	 * @param publisher publisher of the game
	 * @param year      the year of the game
	 * @param genre     the game genre
	 */
	public boolean addGame(String name, String publisher, String year, String genre);
         /**
         * Deletes a game from the database of the Red-Black tree.
         * 
         * @param name the name of the game to remove
         */
         public boolean removeGame(String name);

         /**
	 * Search through all the games in the database and returns the game whose name
	 * matches the word inputted in.
	 *
	 * @param name the name of the game
	 * @return game the game object
	 */
	public IGame searchByName(String name);
}
