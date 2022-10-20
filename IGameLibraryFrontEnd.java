import java.util.List;

public interface IGameLibraryFrontEnd {
	
    /**
     * Constructor of frontend gamelibrary class, assign value to each private field
     * 
     * @param userInputScanner read user input
     * @param backend read the backend game library
     */
     //GameLibraryFrontend(Scanner userInputScanner, IGameBackend backend);

    /**
     * This method starts the command loop for the frontend, and will
     * terminate when the user exits the app.
     */
    public void runCommandLoop();

    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():

    /**
     * Prints command options to the screen (System.out)
     */
    public void displayMainMenu();
    
    /**
     * Display a list of games
     * @param games the game list
     */
    public void displayGames(List<IGame> games);
    
    /**
     * Reads game names from user input (System.in), displays searching results
     */
    public void nameSearch();
}
