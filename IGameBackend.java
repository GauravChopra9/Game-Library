public interface IGameBackend {
    /**
     * Adds a new game to the Backend's database and is stored in the Red-Black
     * tree.
     * 
     * @param game the game to add
     */
    public boolean addGame(String name, String publisher, String year, String genre);

    /**
     * Deletes a game from the database of the Red-Black tree.
     * 
     * @param game to remove
     */
    public boolean removeGame(String name);

    /**
     * Search through all the games in the database and returns the games whose
     * 
     * @param name game name
     * @return game
     */
    public IGame searchByName(String name);

}
