// --== CS400 Project Two File Header ==--
// Name: Dylan Coe
// CSL Username: dcoe
// Email: dtcoe@wisc.edu
// Lecture #: 002 @2:30
// Notes to Grader:

/**
 * This interface is for a Red-Black Tree ADT that has functions of remove,
 * insert, and contains. It should contain objects that can be compared
 */

public interface IRemovingRedBlackTreeADT <T extends Comparable<T>> {

        /**
         * Removes a node in the tree
         *
         * @param name the game name to be removed
         * @return true if the node was deleted, false if it couldn't be found
         *        
        */
        public boolean remove(String name);
         
        /**
         * Inserts a node into the tree 
         * 
         * @param data the game data to be added
         * @return true if data was successfully inserted, false otherwise
        */
        public boolean insert(T data);
         
        /**
         * Checks whether there is a node with the provided data
         * 
         * @param data the game data to find
         * @return true if data was found, false otherwise
        */
        public boolean contains(T data); 
        
        public T find(String name);
}
