// --== CS400 Project Two File Header ==--
// Name: Dylan Coe
// CSL Username: dcoe
// Email: dtcoe@wisc.edu
// Lecture #: 002 @2:30
// Notes to Grader:

import java.util.NoSuchElementException;

/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * Currently, this implements a Binary Search Tree that we will turn into a red black tree by
 * modifying the insert functionality. In this activity, we will start with implementing rotations
 * for the binary search tree insert algorithm. You can use this class' insert method to build a
 * regular binary search tree, and its toString method to display a level-order traversal of the
 * tree.
 */
public class RemovingRedBlackTree<T extends Comparable<T>> extends RedBlackTree<T> implements IRemovingRedBlackTreeADT<T>  {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always maintained.
   */
  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree
  protected static RemovingRedBlackTree<Integer> _instance;

  public RemovingRedBlackTree() {
    super();
  }
  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references
   */
  @Override
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    return super.insert(data);
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation. When the
   * provided child is a rightChild of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will throw
   * an IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  @Override
  protected void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
   super.rotate(child,parent);
  }

  /**
   * Get the size of the tree (its number of nodes).
   * 
   * @return the number of nodes in the tree
   */
  @Override
  public int size() {
    return super.size();
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   * 
   * @return true of this.size() return 0, false if this.size() > 0
   */
  @Override
  public boolean isEmpty() {
    return super.isEmpty();
  }

  /**
   * Checks whether the tree contains the value *data*.
   * 
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  @Override
  public boolean contains(T data) {
    return super.contains(data);
  }

  /**
   * Finds a node containing the given data
   * 
   * @param data to find
   * @return the node with the data
   */
  //@Override
  public Node<T> find(T data) {
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    return findHelper(data, super.root);
  }
  /**
   * Recursive helper for find
   * 
   * @param data to find within the subtree
   * @param subtree to search
   * @return the node containing the data
   */
  private Node<T> findHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return null;
    } else {
      int compare = data.compareTo(subtree.data);
      if (compare < 0) {
        // go left in the tree
        return findHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return findHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return subtree;
      }
    }
  }
  
  /**
   * Finds a node containing the given data
   * 
   * @param data to find
   * @return the node with the data
   */

  public T find(String gameName) {
    if (gameName == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    return findHelper(gameName, super.root).data;
  }
  /**
   * Recursive helper for find
   * 
   * @param data to find within the subtree
   * @param subtree to search
   * @return the node containing the data
   */
  private Node<T> findHelper(String gameName, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return null;
    } else {
      int compare = gameName.compareTo(((IGame)subtree.data).getName());
      if (compare < 0) {
        // go left in the tree
        return findHelper(gameName, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return findHelper(gameName, subtree.rightChild);
      } else {
        // we found it :)
        return subtree;
      }
    }
  }

  /**
   * Performs a naive BST remove on a node
   * 
   * @param data to remove return true if data was removed, false otherwise
   */
  public boolean remove(T data) {
    
    // if the tree has only one node, remove root
    try {
      removeHelper(data, super.root);
    }
    catch (NoSuchElementException e) {
      return false;
    }  
      // if we found it, reduce size and return true
      super.size--;
      return true;
  }
  /**
   * Recursive helper for remove
   * 
   * @param data to remove
   * @param root of the subtree to remove from
   * @return the root of the subtree
   */
  private Node<T> removeHelper(T data, Node<T> root) {
//     if we can't find it, we can't remove it
    if (root == null) {
      throw new NoSuchElementException();
    }
      
    // recursively search for node- if it's in the left, remove from the left
    if (data.compareTo(root.data) < 0) {
      root.leftChild = removeHelper(data, root.leftChild);
    }
    // else if it's in the right, remove from the right
    else if (data.compareTo(root.data) > 0) {
      root.rightChild = removeHelper(data, root.rightChild);
    }
    // else remove what we find
    else {
      // case 0 - we're removing the last node
      if (super.size == 1) {
        super.root = null;
      }
      // case 1 - node has 0 children
      else if (root.leftChild == null && root.rightChild == null) {
        // if it's red, just remove it
        if (root.blackHeight == 0) {
          root = null;
        } else { // it's black. handle the resulting double black
          root.data = null; // use a null node as double black
          root.blackHeight = 2;
          // handle double black
          resolveDoubleBlack(root);
          // remove null node;
          root = null;
        }
      }
      // case 2 - node has 1 child
      // the one child is left
      else if (root.leftChild != null && root.rightChild == null) {
        // replace parent's data with child's data
        root.data = root.leftChild.data;
        // change the color of the child from red to black
        // root.blackHeight++;
        // remove the duplicate node
        root.leftChild = removeHelper(root.leftChild.data, root.leftChild);
      }
      // the one child is right
      else if (root.rightChild != null && root.leftChild == null) {
        root.data = root.rightChild.data;

        // change the color of the child from red to black
        // root.blackHeight++;
        // remove the duplicate node
        root.rightChild = removeHelper(root.rightChild.data, root.rightChild);
      }
      // case 3 - node has 2 children
      else {
   
        // replace node with predecessor
        Node<T> predecessor = getPredecessor(root);
        // replace root's data with predecessor's - this way we don't have to mess with all links
        // between nodes
        // (Believe me I tried and it didn't go well). Has the added benefit of keeping the original
        // node's color,
        // which preserves the black height property
        root.data = predecessor.data;
        // remove the actual predecessor
        root.leftChild = removeHelper(predecessor.data, root.leftChild);
      }
    }
    return root;
  }
  
  /**
   * Remove a game with the given name
   * 
   * @param gameName of the game
   * @return true if the game was removed, false otherwise
   */
  @Override
  public boolean remove(String gameName) {
    // if the tree has only one node, remove root
    System.out.println("Remove " +gameName);
    try {
      removeHelper(gameName, super.root);
    }
    catch (NoSuchElementException e) {
      System.out.println("remove fail");
      return false;
    }  
      // if we found it, reduce size and return true
      super.size--;
      return true;
  }
  /**
   * Recursive helper for remove
   * 
   * @param data to remove
   * @param root of the subtree to remove from
   * @return the root of the subtree
   */
  private Node<T> removeHelper(String gameName, Node<T> root) {
//     if we can't find it, we can't remove it
    if (root == null) {
      throw new NoSuchElementException();
    }
      
    // recursively search for node- if it's in the left, remove from the left
    if (gameName.compareTo(((IGame)(root.data)).getName()) < 0) {
      root.leftChild = removeHelper(gameName, root.leftChild);
    }
    // else if it's in the right, remove from the right
    else if (gameName.compareTo(((IGame)(root.data)).getName()) > 0) {
      root.rightChild = removeHelper(gameName, root.rightChild);
    }
    // else remove what we find
    else {
      // case 0 - we're removing the last node
      if (super.size == 1) {
        super.root = null;
      }
      // case 1 - node has 0 children
      else if (root.leftChild == null && root.rightChild == null) {
        // if it's red, just remove it
        if (root.blackHeight == 0) {
          root = null;
        } else { // it's black. handle the resulting double black
          root.data = null; // use a null node as double black
          root.blackHeight = 2;
          // handle double black
          resolveDoubleBlack(root);
          // remove null node;
          root = null;
        }
      }
      // case 2 - node has 1 child
      // the one child is left
      else if (root.leftChild != null && root.rightChild == null) {
        // replace parent's data with child's data
        root.data = root.leftChild.data;
        // change the color of the child from red to black
        // root.blackHeight++;
        // remove the duplicate node
        root.leftChild = removeHelper(root.leftChild.data, root.leftChild);
      }
      // the one child is right
      else if (root.rightChild != null && root.leftChild == null) {
        root.data = root.rightChild.data;

        // change the color of the child from red to black
        // root.blackHeight++;
        // remove the duplicate node
        root.rightChild = removeHelper(root.rightChild.data, root.rightChild);
      }
      // case 3 - node has 2 children
      else {
        System.out.println("Regular BST remove: 2 child");
        // replace node with predecessor
        Node<T> predecessor = getPredecessor(root);
        // replace root's data with predecessor's - this way we don't have to mess with all links
        // between nodes
        // (Believe me I tried and it didn't go well). Has the added benefit of keeping the original
        // node's color,
        // which preserves the black height property
        root.data = predecessor.data;
        // remove the actual predecessor
        root.leftChild = removeHelper(predecessor.data, root.leftChild);
      }
    }
    return root;
  } 
  
  /**
   * Get the predecessor (rightmost node of the left subtree)
   * 
   * @param root of the subtree to get the predecessor of
   * @return the predecessor
   */
  private Node<T> getPredecessor(Node<T> root) {
    // if it's null, there is no predecessor
    if (root == null) 
      return null;
    root = root.leftChild;
    // while there is a right subtree
    while (root.rightChild != null) {
      // go right
      root = root.rightChild;
    }
    return root;
  }
  /**
   * Resolves doubleBlack nodes. Note - I am switching around the case numbers from lecture. Since
   * Case 1 and Case 3 require a black sibling, it makes more sense to me to have those two grouped
   * together
   * Case 1: double black's sibling is black with no red children 
   * Case 2: double black's sibling is black with a red child on opposite side 
   * Case 2.5: double black's sibling is black with a red child on same side 
   * Case 3: double black's sibling is red
   * 
   * @param doubleBlack
   */
  protected void resolveDoubleBlack(Node<T> doubleBlack) {
    Node<T> sibling = getSibling(doubleBlack);
    //case 0 - double black has no sibling (this is basically case 1)
    if (sibling == null) {
      // add 1 to parent blackHeight
      doubleBlack.parent.blackHeight++;
      // subtract 1 from double black black height
      doubleBlack.blackHeight = 1;
      //resolve parent if necessary
      if (doubleBlack.parent.blackHeight > 1) {
        resolveDoubleBlack(doubleBlack.parent);
      }
      return;
    }
    // get sibling's children
    Node<T> siblingLeft, siblingRight;
    if (sibling.leftChild == null)
      siblingLeft = new Node<T>(null); // use a null node as a placeholder for blacks
    else
      siblingLeft = sibling.leftChild;
    if (sibling.rightChild == null)
      siblingRight = new Node<T>(null); // use a null node as a placeholder for blacks
    else
      siblingRight = sibling.rightChild;

    // resolve double black
    if (sibling.blackHeight == 1) { // if it has a black sibling, it's case 1 or 2
      // case 1
      // double black's sibling is black with no red children
      if (siblingLeft.blackHeight == 1 && siblingRight.blackHeight == 1) { // if no red children
        // subtract 1 from black height:
        // double black becomes normal black
        doubleBlack.blackHeight = 1;
        // sibling becomes red
        sibling.blackHeight = 0;

        // "base case" - if we hit the root we don't need to add the black height there
        if (doubleBlack.parent.parent == null) {
          return;
        }
        // add 1 black to parent
        doubleBlack.parent.blackHeight++;
        // if parent is double black
        if (doubleBlack.parent.blackHeight == 2)
          // fix parent
          resolveDoubleBlack(doubleBlack.parent);
      } else { // the sibling has red children. Case 2 or case 2.5
        // get the opposite/same side nieces
        Node<T> opposite, same;
        if (doubleBlack.isLeftChild()) {
          opposite = siblingRight;
          same = siblingLeft;
        } else {
          opposite = siblingLeft;
          same = siblingRight;
        }
        // case 2
        // if doubleBlack has black sibling with red child on opposite side
        if (opposite.blackHeight == 0) {// if the opposite is red
          
          // rotate + color swap parent and sibling
          colorSwap(sibling, sibling.parent);
          // double black becomes normal black     
          rotate(sibling, sibling.parent);
          doubleBlack.blackHeight = 1;
          // red becomes normal black
          opposite.blackHeight = 1;
          
        } else { // case 2.5 - if doubleBlack has black sibling with a red child on same side
          // rotate and color swap the red and it's parent (sib and it's child)
          colorSwap(same, same.parent);
          rotate(same, same.parent);
          // then case 2
          resolveDoubleBlack(doubleBlack);
        }
      }
    } else { // it has a red sibling. case 3.
      // rotate and color swap sibling (red) and it's parent
      colorSwap(sibling, sibling.parent);
      rotate(sibling, sibling.parent);
      // double black becomes case 1 or 3 - resolveDoubleBlack
      resolveDoubleBlack(doubleBlack);
    }
  }

  /**
   * Get the sibling (parent's other child) of the node
   * 
   * @param child to get the sibling of
   * @return the sibling
   */
  private Node<T> getSibling(Node<T> child) {
    if (child.parent == null) {
      return null;
    }

    else if (child.isLeftChild()) {
      return child.parent.rightChild;
    } else
      return child.parent.leftChild;
  }

  /**
   * Swap the colors of two nodes
   * 
   * @param one first node to color swap
   * @param two second node to color swap
   */
  void colorSwap(Node<T> one, Node<T> two) {
    int placeholder = one.blackHeight;
    one.blackHeight = two.blackHeight;
    two.blackHeight = placeholder;
  }

  /**
   * This method performs an inorder traversal of the tree. The string representations of each data
   * value within this tree are assembled into a comma separated string within brackets (similar to
   * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note
   * that this RedBlackTree class implementation of toString generates an inorder traversal. The
   * toString of the Node class class above produces a level order traversal of the nodes / values
   * of the tree.
   * 
   * @return string containing the ordered values of this tree (in-order traversal)
   */
  public String toInOrderString() {
    return super.toInOrderString();
  }
  /**
   * This method performs a level order traversal of the tree rooted at the current node. The string
   * representations of each data value within this tree are assembled into a comma separated string
   * within brackets (similar to many implementations of java.util.Collection). Note that the Node's
   * implementation of toString generates a level order traversal. The toString of the RedBlackTree
   * class below produces an inorder traversal of the nodes / values of the tree. This method will
   * be helpful as a helper for the debugging and testing of your rotation implementation.
   * 
   * @return string containing the values of this tree in level order
   */
  public String toLevelOrderString() {
    return super.toLevelOrderString();
  }

  /**
   * Does the same as the above but also displays the black heights of each node
   * 
   * @return string containing the values and black heights of this tree in level order
   */
  public String toLevelOrderStringWithColors() {
    return super.toLevelOrderStringWithColors();
  }

  /**
   * Gets the Level Order String and In-Order String
   * 
   * @return the RBT in string format
   */
  public String toString() {
    return super.toString();
  }

  /**
   * Checks that the blackHeight's of all paths from root to leaf is the same
   * 
   * @return true if all the blackheights are the same
   */
  protected boolean isBlackHeightConsistent() {
   return super.isBlackHeightConsistent();
  }

}

