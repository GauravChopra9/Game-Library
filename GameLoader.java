// --== CS400 Project One File Header ==--
// Name: Gaurav Chopra
// CSL Username: chopra
// Email: gmchopra@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Took 10 hours

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * This class has a method that parses and loads game data from a xml file into a list
 */
public class GameLoader implements IGameLoader {

    /**
     * Parses through the xml file and gets the data corresponding to the name, year, genre and publisher.
     * Uses this data to create multiple Game objects and adds them to a list
     */
    @Override
    public List<IGame> loadGames(String filepathToXML) throws FileNotFoundException {

        // Creating a file object that holds the path to the XML file
        File file = new File(filepathToXML);
        // Creating a Scanner object that will be used to parse the file
        Scanner sc = new Scanner(file);
        // Creating a list object that will hold all the Game objects
        List <IGame> toReturn = new ArrayList<>();

        // Creating an initializing name, year, genre and publisher variables
        String name = "";
        String year  = "";
        String genre = "";
        String  publisher= "";

        // While loop using Scanner to loop through the entire XML file until its end
        while (sc.hasNextLine()) {
            // Getting some data consisting of a line in the xml file
            String theData = sc.nextLine();

            // Checking if that data line contains the <Name> tag
            if (theData.contains("<Name>")) {
                // Getting the vale within the <Name> tag
                name = sc.nextLine().trim();
            }

            // Checking if that data line contains the <Year> tag
            if (theData.contains("<Year>")) {
                // Getting the vale within the <Year> tag
                year = sc.nextLine().trim();
            }

            // Checking if that data line contains the <Genre> tag
            if (theData.contains("<Genre>")) {
                // Getting the vale within the <Genre> tag
                genre = sc.nextLine().trim();
            }

            // Checking if that data line contains the <Publisher> tag
            if (theData.contains("<Publisher>")) {
                // Getting the value within the <Publisher> tag
                publisher = sc.nextLine().trim();
            }

            // Checking if that data line contains the </Publisher> tag
            if (theData.contains("</dataitem>")) {
                // Making a Game object
                DWGame obj = new DWGame (name, publisher, year, genre);
                // Appending the game object to the list
                toReturn.add(obj);
            }
        }
        // Returning the list holding all the Game objects
        return toReturn;
    }
}

