
// JUnit Imports 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Random;


public class AlgorithmEngineerTests {
  
  RemovingRedBlackTree<Integer> _instance;
  @BeforeEach
  public void createInstance() {
      _instance = new RemovingRedBlackTree<Integer>();
      _instance.insert(4);
      _instance.insert(2);
      _instance.insert(6);
      _instance.insert(1);
      _instance.insert(3);
      _instance.insert(5);
      _instance.insert(7);
  }
  

  /**
   * Test comparing different games
   */
  @Test
  public void test1(){
    AEGame test1 = new AEGame("Xenoblade Chronicles 3", "Monolith Soft", "2022", "RPG");
    AEGame test2 = new AEGame("Final Fantasy III", "Matrix Software", "2006", "RPG");
    AEGame test3 = new AEGame("Final Fantasy III", "Square Enix", "2006", "RPG");
    AEGame test4 = new AEGame("The Legend of Zelda, Majora's Mask", "Nintendo", "2000,", "Action-Adventure");
    AEGame test5 = new AEGame("The Legend of Zelda, Majora's Mask", "Nintendo", "2003,", "Action-Adventure");
    AEGame test6 = new AEGame("The Legend of Zelda, Majora's Mask", "Nintendo", "2003,", "Action-Adventure");
    
    //name less
    assertTrue(test2.compareTo(test1) < 0);
    // name more
    assertTrue(test4.compareTo(test2) > 0);
    // name equal, year different
    assertTrue(test4.compareTo(test5) < 0);
    // name equal, year equal, publisher different
    assertTrue(test2.compareTo(test3) < 0);
    // name, year, publisher all equal
    assertTrue(test5.compareTo(test6) == 0);
    
    
  }    
  /**
   * Test Finding some games
   */
  @Test
  public void test2() {
    RemovingRedBlackTree<AEGame> _instanceGames = new RemovingRedBlackTree<AEGame>();
    AEGame test1 = new AEGame("Xenoblade Chronicles 3", "Monolith Soft", "2022", "RPG");
    AEGame test2 = new AEGame("Final Fantasy III", "Matrix Software", "2006", "RPG");
    AEGame test3 = new AEGame("Final Fantasy III", "Square Enix", "2006", "RPG");
    AEGame test4 = new AEGame("The Legend of Zelda, Majora's Mask", "Nintendo", "2000,", "Action-Adventure");
    AEGame test5 = new AEGame("The Legend of Zelda, Majora's Mask", "Nintendo", "2003,", "Action-Adventure");
    AEGame test6 = new AEGame("Elden Ring", "FromSoftware", "2022", "RPG");
    
    // find in empty tree
    assertEquals(_instanceGames.find(test1), null);
    
    // add some games
    _instanceGames.insert(test1);
    _instanceGames.insert(test2);
    _instanceGames.insert(test3);
    _instanceGames.insert(test4);
    _instanceGames.insert(test5);
    
    // find some games
    assertEquals(_instanceGames.find(test1).data, test1);
    assertEquals(_instanceGames.find(test2).data, test2);
    assertEquals(_instanceGames.find(test3).data, test3);
    assertEquals(_instanceGames.find(test4).data, test4);
    assertEquals(_instanceGames.find(test5).data, test5);
    
    // find game that's not in the tree
    assertEquals(_instanceGames.find(test6), null);
  }
  /**
   * Test RBT remove cases that don't involve double blacks
   */
  @Test
  public void test3() {
    assertTrue(!_instance.remove(9)); // remove element that doesn't exist in tree
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 4(1), 2(1), 6(1), 1(0), 3(0), 5(0), 7(0) ]");
    assertTrue(_instance.remove(3)); // 0 children
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 4(1), 2(1), 6(1), 1(0), 5(0), 7(0) ]");
    assertTrue(_instance.remove(2)); // 1 child on left
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 4(1), 1(1), 6(1), 5(0), 7(0) ]");
    assertTrue(_instance.remove(6)); // 2 children
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 4(1), 1(1), 5(1), 7(0) ]");
    assertTrue(_instance.remove(5)); // 1 child on right
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 4(1), 1(1), 7(1) ]");
    assertTrue(_instance.remove(4)); // 2 children
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 1(1), 7(0) ]");
    assertTrue(_instance.remove(1)); // 1 child on right
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 7(1) ]");
    assertTrue(_instance.remove(7)); // remove root
    assertEquals(_instance.toLevelOrderStringWithColors(), "[  ]");
    assertTrue(!_instance.remove(1)); // remove from empty tree
    assertEquals(_instance.toLevelOrderStringWithColors(), "[  ]");
  }
  
  /**
   * Test RBT remove cases with double blackassertTrue(s
   */
  @Test
  public void test4() {
    // case 1 remove
    _instance.remove(1);
    _instance.remove(3);
    _instance.remove(5);
    _instance.remove(7);
    _instance.remove(2);
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 4(1), 6(0) ]");
    // case 2 remove
    createInstance(); // reset instance
    _instance.remove(1);
    _instance.remove(3);
    _instance.remove(5);
    _instance.remove(2);
    //System.out.println(_instance.toLevelOrderStringWithColors());
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 6(1), 4(1), 7(1) ]");
    // case 2.5 remove
    createInstance(); // reset instance
    _instance.remove(1);
    _instance.remove(5);
    _instance.remove(7);
    _instance.remove(6);
    // case 3 remove
    createInstance(); // reset instance
    _instance.insert(8); // this will change the RBT to something where we can test a case 3 remove
    _instance.remove(1);
    _instance.remove(3);
    _instance.remove(2);
    assertEquals(_instance.toLevelOrderStringWithColors(), "[ 6(1), 4(1), 7(1), 5(0), 8(0) ]");
  }

  /**
   * Test removing games from a tree
   */
  @Test
  public void test5() {
    RemovingRedBlackTree<AEGame> _instanceGames = new RemovingRedBlackTree<AEGame>();
    AEGame test1 = new AEGame("Xenoblade Chronicles 3", "Monolith Soft", "2022", "RPG");
    AEGame test2 = new AEGame("Final Fantasy III", "Matrix Software", "2006", "RPG");
    AEGame test3 = new AEGame("Final Fantasy III", "Square Enix", "2006", "RPG");
    AEGame test4 = new AEGame("The Legend of Zelda, Majora's Mask", "Nintendo", "2000,", "Action-Adventure");
    AEGame test5 = new AEGame("The Legend of Zelda, Majora's Mask", "Nintendo", "2003,", "Action-Adventure");
    AEGame test6 = new AEGame("Elden Ring", "FromSoftware", "2022", "RPG");
    
    // add some games
    _instanceGames.insert(test1);
    _instanceGames.insert(test2);
    _instanceGames.insert(test3);
    _instanceGames.insert(test4);
    _instanceGames.insert(test5);
    _instanceGames.insert(test6);
    
    
    // add some games
    _instanceGames.remove(test1);
    _instanceGames.remove(test3);
    _instanceGames.remove(test5);
    _instanceGames.remove(test4);
    _instanceGames.remove(test2);
    assertEquals(_instanceGames.toLevelOrderStringWithColors(), "[ Elden Ring(1) ]");
   
  }

  public static void main(String[] args) {
    
    

  }

}

