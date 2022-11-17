import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GameLibrary {

	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
	IGameLoader gameLoader = new GameLoader();
        // load the books from the data file
        List<IGame> gameList = gameLoader.loadGames("vgsales2.xml");
        // instantiate the backend
        IGameBackend backend = new GameLibraryBackend();
        // add all the books to the backend
        for (IGame game : gameList) backend.addGame(game.getName(), game.getPublisher(), game.getYear(), game.getGenre());
        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
        IGameFrontEnd frontend = new GameFrontEnd(userInputScanner, backend);
        // start the input loop of the front end
        frontend.runCommandLoop();
	}

}
