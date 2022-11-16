// --== CS400 Project One File Header ==--
// Name: Gaurav Chopra
// CSL Username: chopra
// Email: gmchopra@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Took 10 hours

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * This class tests the functionality of the methods in the GameLoader class
 */
public class DataWranglerTests {
    List<IGame> gameList;


    /**
     * This method runs before each @Test annotation and initializes the GameLoader obj and fills
     * the gameList with the game objects
     */
    @BeforeEach
    void initialize(){
        // Creating a GameLoader object
        GameLoader obj = new GameLoader();
        // Creating a gameList instance
        gameList =  new ArrayList<>();
        try {
            // Initializing gameList by filling it with Game objects loaded from a valid xml file path
            gameList = obj.loadGames("vgsales2.xml");
        }

        // Checking if a FileNotFoundException is thrown and caught
        catch (FileNotFoundException f) {
            // FileNotFoundException is not expected in this case and returns false if
            // the exception is caught
            assertEquals("True", "False");
        }
    }


    /**
     * This tester tests if a FileNotFoundException is thrown and caught when an invalid xml path is provided.
     * It also checks if the size of the gameList is valid when it is empty and when completely full
     */
    @Test
    void test1() {
        // Testing if the size of the gameList is as expected
        assertEquals(gameList.size(), 16598);

        // Creating a new GameLoader() object
        GameLoader obj2 = new GameLoader();
        // Making a new List<IGame> variable
        List<IGame> gameList2 = new ArrayList<>();;

        try {
            // Trying to fill gameList2 when passing an invalid xml path to loadGames
            gameList2 = obj2.loadGames("doesnotexist.xml");
            // Returning false as a FileNotFoundException should be thrown
            assertEquals(true, false );
        }

        // Checking if a FileNotFoundException is thrown and caught
        catch (FileNotFoundException f) {
            // Returning true as a FileNotFoundException was thrown as expected
            assertEquals(true, true );
            // Checking if the size is 0 as FileNotFoundException was thrown
            assertEquals(gameList2.size(), 0 );
        }
    }

    /**
     * This tester checks if the data within gameList is as expected when using getName() at
     * the 1st, 10th, 100th, 1000th, 10000th and Last game object in the gameList
     */
    @Test
    void test2() {
        // Testing if the Name of the first Game object in the list is as expected
        assertEquals(gameList.get(0).getName(), "Wii Sports");
        // Testing if the Name of the 10th Game object in the list is as expected
        assertEquals(gameList.get(9).getName(), "Duck Hunt");
        // Testing if the Name of the 100th Game object in the list is as expected
        assertEquals(gameList.get(99).getName(), "Battlefield 3");
        // Testing if the Name of the 1000th Game object in the list is as expected
        assertEquals(gameList.get(999).getName(), "Call of Duty: Black Ops 3");
        // Testing if the Name of the 10000th Game object in the list is as expected;
        assertEquals(gameList.get(9999).getName(), "DOA 2: Dead or Alive 2 Hardcore");
        // Testing if the Name of the last Game object in the list is as expected
        assertEquals(gameList.get(gameList.size() - 1).getName(), "Spirits & Spells");
    }

    /**
     * This tester checks if the data within gameList is as expected when using getGenre() at
     * the 1st, 10th, 100th, 1000th, 10000th and Last game object in the gameList
     */
    @Test
    void test3() {
        // Testing if the Genre of the first Game object in the list is as expected
        assertEquals(gameList.get(0).getGenre(), "Sports");
        // Testing if the Genre of the 10th Game object in the list is as expected
        assertEquals(gameList.get(9).getGenre(), "Shooter");
        // Testing if the Genre of the 100th Game object in the list is as expected
        assertEquals(gameList.get(99).getGenre(), "Shooter");
        // Testing if the Genre of the 1000th Game object in the list is as expected
        assertEquals(gameList.get(999).getGenre(), "Shooter");
        // Testing if the Genre of the 10000th Game object in the list is as expected
        assertEquals(gameList.get(9999).getGenre(), "Fighting");
        // Testing if the Genre of the last Game object in the list is as expected
        assertEquals(gameList.get(gameList.size() - 1).getGenre(), "Platform");
    }

    /**
     * This tester checks if the data within gameList is as expected when using getPublisher() at
     * the 1st, 10th, 100th, 1000th, 10000th and Last game object in the gameList
     */
    @Test
    void test4() {
        // Testing if the Publisher of the first Game object in the list is as expected
        assertEquals(gameList.get(0).getPublisher(), "Nintendo");
        // Testing if the Publisher of the 10th Game object in the list is as expected
        assertEquals(gameList.get(9).getPublisher(), "Nintendo");
        // Testing if the Publisher of the 100th Game object in the list is as expected
        assertEquals(gameList.get(99).getPublisher(), "Electronic Arts");
        // Testing if the Publisher of the 1000th Game object in the list is as expected
        assertEquals(gameList.get(999).getPublisher(), "Activision");
        // Testing if the Publisher of the 10000th Game object in the list is as expected
        assertEquals(gameList.get(9999).getPublisher(), "Tecmo Koei");
        // Testing if the Publisher of the last Game object in the list is as expected
        assertEquals(gameList.get(gameList.size() - 1).getPublisher(), "Wanadoo");
    }

    /**
     * This tester checks if the data within gameList is as expected when using getYear() at
     * the 1st, 10th, 100th, 1000th, 10000th and Last game object in the gameList.
     */
    @Test
    void test5() {
        // Testing if the Year of the first Game object in the list is as expected
        assertEquals(gameList.get(0).getYear(), "2006");
        // Testing if the Year of the 10th Game object in the list is as expected
        assertEquals(gameList.get(9).getYear(), "1984");
        // Testing if the Year of the 100th Game object in the list is as expected
        assertEquals(gameList.get(99).getYear(), "2011");
        // Testing if the Year of the 1000th Game object in the list is as expected
        assertEquals(gameList.get(999).getYear(), "2015");
        // Testing if the Year of the 10000th Game object in the list is as expected
        assertEquals(gameList.get(9999).getYear(), "2000");
        // Testing if the Year of the last Game object in the list is as expected
        assertEquals(gameList.get(gameList.size() - 1).getYear(), "2003");
    }

    /**
     * This tester checks if all the attributes of Game for all the Game objects in
     * gameList are not blank by looping through the entire gameList
     */
    @Test
    void test6() {
        // Looping through the entire gameList
        for (int i = 0; i < gameList.size(); i++) {
            // Checks if the name of all the Game objets in gameList are not blank
            assertEquals(gameList.get(i).getName().isBlank(), false);
            // Checks if the name of all the Genre objets in gameList are not blank
            assertEquals(gameList.get(i).getGenre().isBlank(), false);
            // Checks if the name of all the Publisher objets in gameList are not blank
            assertEquals(gameList.get(i).getPublisher().isBlank(), false);
            // Checks if the name of all the Year objets in gameList are not blank
            assertEquals(gameList.get(i).getYear().isBlank(), false);
        }
    }

}

