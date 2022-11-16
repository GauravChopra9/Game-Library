// --== CS400 RedBlackTree Activity File Header ==--
// Name: Dylan Coe
// CSL Username: dcoe
// Email: dtcoe@wisc.edu
// Lecture #: 002 @2:30
// Notes to Grader: 

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
//    protected static class Node<T> {
//        public T data;
//        public Node<T> parent; // null for root node
//        public Node<T> leftChild;
//        public Node<T> rightChild;
//        public int blackHeight=0; // red = 0, black = 1, double black = 2. nodes start as red
//        public Node(T data) { this.data = data; }
//        /**
//         * @return true when this node has a parent and is the left child of
//         * that parent, otherwise return false
//         */
//        public boolean isLeftChild() {
//            return parent != null && parent.leftChild == this;
//        }
//        /**
//         * Helper method to get the aunt/uncle of a node
//         * 
//         * @param child the child node to get the aunt/uncle of
//         * @return the parent's parent's other child
//         */
//        private Node<T> getAunt() {
//          Node<T> aunt = null;
//          if (this.parent.parent != null) {
//            if (this.parent.isLeftChild())
//              aunt = this.parent.parent.rightChild;
//            else aunt = this.parent.parent.leftChild;      
//          }
//          
//          if (aunt == null) {
//            aunt = new Node<T>(null);
//            aunt.blackHeight = 1;
//          }
//          return aunt;
//        }
//    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree
    protected static  RedBlackTree<Integer> _instance;

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
      // null references cannot be stored within this tree
      if(data == null) throw new NullPointerException(
              "This RedBlackTree cannot store null references.");

      Node<T> newNode = new Node<>(data);
      if(root == null) { 
        root = newNode; 
        size++; 
        this.root.blackHeight = 1;
        return true; 
      } // add first node to an empty tree
      else{
          boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
          if (returnValue) size++;
          else throw new IllegalArgumentException(
                  "This RedBlackTree already contains that value.");
          this.root.blackHeight = 1;
          return returnValue;
      }
  }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
      int compare = newNode.data.compareTo(subtree.data);
      // do not allow duplicate values to be stored within this tree
      if(compare == 0) return false;

          // store newNode within left subtree of subtree
      else if(compare < 0) {
          if(subtree.leftChild == null) { // left subtree empty, add here
              subtree.leftChild = newNode;
              newNode.parent = subtree;
              enforceRBTreePropertiesAfterInsert(newNode);
              return true;
              // otherwise continue recursive search for location to insert
          } else return insertHelper(newNode, subtree.leftChild);
      }

      // store newNode within the right subtree of subtree
      else {
          if(subtree.rightChild == null) { // right subtree empty, add here
              subtree.rightChild = newNode;
              newNode.parent = subtree;
              enforceRBTreePropertiesAfterInsert(newNode);
              return true;
              // otherwise continue recursive search for location to insert
          } else return insertHelper(newNode, subtree.rightChild);
      }
  }
    
    protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {       
      // if node is root, we're done
      if (newNode.parent == null || newNode.parent.blackHeight == 1 ) {
        return;
      }
      Node<T> parent = newNode.parent;
      Node<T> grandparent = newNode.parent.parent;
      Node<T> aunt = newNode.getAunt();
    
      // if parent is red, fix violation
      if (parent.blackHeight == 0) {
        // case 1 - aunt is black and opposite sides
        if (aunt.blackHeight == 1 && isOppositeSides(newNode)) {
          //rotate parent and grandparent
          rotate(parent, grandparent);
          //swap colors of parent and grandparent
          parent.blackHeight = 1;
          grandparent.blackHeight = 0;
        }
        // case 2 - aunt is black and same side
        else if (aunt.blackHeight == 1 && !isOppositeSides(newNode)) {
         // rotate the two red nodes - newNode and parent
         rotate(newNode, newNode.parent);
         // then it's case 1 for the parent
         enforceRBTreePropertiesAfterInsert(parent);
        }
        // case 3 - aunt is red
        else if (aunt.blackHeight == 0) {
          // change color of parent and sibling from red to black
          parent.blackHeight = 1;
          aunt.blackHeight = 1;
          // change parent's parent from black to red
          grandparent.blackHeight = 0;
          //if grandparent's parent is red, fix it
          enforceRBTreePropertiesAfterInsert(grandparent);         
        }
      }
    }
    
    /**
     * Checks if a child and it's aunt are on opposite sides - determining whether it's Case 1 or Case 2
     * 
     * @param child to check if it's on the opposite or same side as it's aunt
     * @return true if opposite sides, false if same side
     */
    private boolean isOppositeSides(Node<T> child) {
      if (child.getAunt().data == null) { //handle null so we don't get NullPointerExceptions
        return (child.parent.parent.leftChild == null && !child.isLeftChild()) || (child.parent.parent.rightChild == null && child.isLeftChild());
      }
      else return (child.getAunt().isLeftChild() && !child.isLeftChild())||(!child.getAunt().isLeftChild() && child.isLeftChild());
    }
   
    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    protected void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
      // if child and parent unrelated, throw IllegalArgumentException
      if (!child.parent.equals(parent)) {
        throw new IllegalArgumentException();
      }
      if (child.isLeftChild()) {//if child is left child,
      // do right rotation
        // move left subtree to child's right subtree
        parent.leftChild = child.rightChild;
        // if child has a right child,
        if (child.rightChild != null) {
          // then parent becomes it's parent
          child.rightChild.parent = parent;
        }
        // set child's parent to parent's parent 
        child.parent = parent.parent;
        // if parent is root, child becomes new root
        if (this.root == parent && parent.parent == null) {
          this.root = child;
          child.parent = null;
        }
        // else if it's a left child, 
        else if (parent.isLeftChild()) {
          // child becomes the left child of parent's parent
          parent.parent.leftChild = child;  
        }
        else { // it is a right child
          // child becomes the right child of parent's parent
          parent.parent.rightChild = child;
        }
        // switch parent and child
        child.rightChild = parent;
        parent.parent = child;
   
      }
      else { // it is a right child
       // do left rotation
        // move right subtree to child's left subtree
        parent.rightChild = child.leftChild;
        // if child has a left child,
        if (child.leftChild != null) {
          // then parent becomes it's parent
          child.leftChild.parent = parent;
        }
        // set child's parent to parent's parent
        child.parent = parent.parent;
        // if parent is root, child becomes new root
        if (this.root == parent && parent.parent == null) {
          this.root = child;
          child.parent = null;
        }
        // else if it's a left child,
        else if (parent.isLeftChild()) {
          // child becomes the left child of parent's parent
          parent.parent.leftChild = child;
        }
        else { // it is a right child
          // child becomes the right child of parent's parent
          parent.parent.rightChild = child;
        }
        // switch parent and child
        child.leftChild = parent;
        parent.parent = child;
      }        
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node){
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }
    public String toLevelOrderStringWithColors() {
      String output = "[ ";
      if (this.root != null) {
          LinkedList<Node<T>> q = new LinkedList<>();
          q.add(this.root);
          while(!q.isEmpty()) {
              Node<T> next = q.removeFirst();
              if(next.leftChild != null) q.add(next.leftChild);
              if(next.rightChild != null) q.add(next.rightChild);
              output += next.data.toString()+ "(" + next.blackHeight +")";
              if(!q.isEmpty()) output += ", ";
          }
      }
      return output + " ]";
  }
    /**
     * Gets the Level Order String and In-Order String
     * 
     * @return the RBT in string format
     */
    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }
    
    /**
     * Checks that the blackHeight's of all paths from root to leaf is the same
     * 
     * @return true if all the blackheights are the same
     */
    protected boolean isBlackHeightConsistent() {
      // create an arraylist and fill it with the black height of each possible path from root to leaf
      ArrayList<Integer> leafBlackHeights = new ArrayList<Integer>();
      int blackHeight = 0;
      getBlackHeights(this.root, blackHeight, leafBlackHeights);
      
      // iterate through arraylist and make sure each blackheight is the same
      for (int i = 0; i < leafBlackHeights.size(); ++i) {
        // if not all the blackheights are the same, it is not a valid RBT
        if (leafBlackHeights.get(0) != leafBlackHeights.get(i)) {
          return false;
        }
      }
      return true;
    }
    
    /**
     * Recursively traverses the tree until it hits a leaf node, then adds the blackHeight of it to an ArrayList
     * 
     * @param root of the tree/subtree
     * @param blackHeight blackHeight of the 
     * @param leafBlackHeights ArrayList to store the blackHeights in
     */
    private void getBlackHeights(Node<T> root, int blackHeight, ArrayList<Integer> leafBlackHeights){
      // if node is null, do nothing
      if (root == null) {
        return;
      }
      // base case - if node is leaf, add it's black height to the arraylist    
      if (root.leftChild == null && root.rightChild == null){
        blackHeight = getBlackHeight(root,0);
        leafBlackHeights.add(blackHeight);
        return;
      }
        // recursive case - if node has left child, visit it
        if (root.leftChild != null) {
          // add left child's black height to total black height
          // recurse
          getBlackHeights(root.leftChild, blackHeight, leafBlackHeights);
        }
                 
        // recursive case - if node has right child, visit it
        if (root.rightChild != null) {
          // add left child's black height to total black height
          // recurse
          getBlackHeights(root.rightChild, blackHeight, leafBlackHeights);
        }
    }
    
    /**
     * @param node to get the blackHeight of
     * @param blackHeight of the Node. 0 to start
     * @return blackHeight of the node
     */
    private int getBlackHeight(Node<T> node,int blackHeight) {
      blackHeight+= node.blackHeight;
      if (node.parent != null) {
        blackHeight = getBlackHeight(node.parent, blackHeight);
      }
      return blackHeight;
     
    }
}
