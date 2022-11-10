/**
 * This interface is for a Red-Black Tree ADT that has functions of remove,
 * insert, and contains. It should contain objects that can be compared
 */
public interface RemovingRedBlackTreeADT<T extends Comparable> {

	/**
	 * Removes a node in the tree
	 *
	 * @param data the game data to be removed
	 * @return true if the node was deleted, false if it couldn't be found
	 *
	 */
	public boolean remove(T data);

	/**
	 * Inserts a node into the tree
	 *
	 * @param data the game data to be added
	 * @return true if data was successfully inserted, false otherwise
	 */
	public boolean insert(T data);

	public T find(String name);

	public boolean contains(T data);
}