// --== CS400 Project Two File Header ==--
// Name: Dylan Coe
// CSL Username: dcoe
// Email: dtcoe@wisc.edu
// Lecture #: 002 @2:30
// Notes to Grader:


public class Node<T> {
  protected T data;
  protected Node<T> parent; // null for root node
  protected Node<T> leftChild;
  protected Node<T> rightChild;
  protected int blackHeight = 0; // red = 0, black = 1, double black = 2. nodes start as red

  protected Node(T data) {
    this.data = data;
    // null nodes have blackHeight of 1
    if (this.data == null) {
      this.blackHeight = 1;
    }
  }

  /**
   * @return true when this node has a parent and is the left child of that parent, otherwise return
   *         false
   */
  protected boolean isLeftChild() {
    return parent != null && parent.leftChild == this;
  }

  /**
   * Helper method to get the aunt/uncle of a node
   * 
   * @param child the child node to get the aunt/uncle of
   * @return the parent's parent's other child
   */
  protected Node<T> getAunt() {
    Node<T> aunt = null;
    if (this.parent.parent != null) {
      if (this.parent.isLeftChild())
        aunt = this.parent.parent.rightChild;
      else
        aunt = this.parent.parent.leftChild;
    }

    if (aunt == null) {
      aunt = new Node<T>(null);
      aunt.blackHeight = 1;
    }
    return aunt;
  }

  /**
   * Checks if a child and it's aunt are on opposite sides - determining whether it's Case 1 or Case
   * 2
   * 
   * @param child to check if it's on the opposite or same side as it's aunt
   * @return true if opposite sides, false if same side
   */
  protected boolean isOppositeSide() {
    if (this.getAunt().data == null) { // handle null so we don't get NullPointerExceptions
      return (this.parent.parent.leftChild == null && !this.isLeftChild())
          || (this.parent.parent.rightChild == null && this.isLeftChild());
    } else
      return (this.getAunt().isLeftChild() && !this.isLeftChild())
          || (!this.getAunt().isLeftChild() && this.isLeftChild());
  }
}
