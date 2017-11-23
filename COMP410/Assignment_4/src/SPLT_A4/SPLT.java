// SPLT By Will Berner COMP410 Fall 2017
// Uses bottom-up splaying
// On insert, remove, findMin, findMax, and contains

package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
    this.root = null;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

@Override
public void insert(String s) {
	// TODO Auto-generated method stub
	if (size == 0) {
		root = new BST_Node(s);
		size += 1;
	}
	
	if (contains(s)) {
		return;
	} else {
		size += 1;
		root = root.insertNode(s); // assign new root to the returned Node (the splayed node)
		root.par = null;
	}
}

@Override
public void remove(String s) {
	// TODO Auto-generated method stub
	if (root == null) {
		return;
	} else if (root.left == null && root.right == null && root.data.equals(s)) {
		this.root = null;
		size--;
		return;
	}
	boolean contains = (contains(s));
	if (!contains) { // node to remove doesn't exists
		return;
	} else {
		// THIS.ROOT IS THE NODE TO BE REMOVED af
		if (this.root.data == s) {
			BST_Node leftChild = root.left;
			BST_Node rightChild = root.right;
			BST_Node newRoot = null;
			if (leftChild != null) {
				newRoot = leftChild.findMax();
			} else {
				newRoot = root;
			}
			if (newRoot != null && newRoot.data != s) {
				newRoot.right = rightChild;
				newRoot.par = null;
			} else {

			}

			root = newRoot;
			this.size--;
		} else {
			return;
		}

	}
}

@Override
public String findMin() {
	// TODO Auto-generated method stub
	if (empty()) {
		return null;
	} else {
		BST_Node splayedNode = root.findMin();
		root = splayedNode;
		return splayedNode.data;
	}
}

@Override
public String findMax() {
	// TODO Auto-generated method stub
	if (empty()) {
		return null;
	} else {
		BST_Node splayedNode = root.findMax();
		root = splayedNode;
		return splayedNode.data;
	}
}

@Override
public boolean empty() {
	// TODO Auto-generated method stub
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

@Override
public boolean contains(String s) {
	// TODO Auto-generated method stub
	if (empty()) {
		return false;
	} else {
		BST_Node splayedRoot = root.containsNode(s);
		if (splayedRoot != null) {
			this.root = splayedRoot;
			return true;
		} else {
			return false;
		}
		
	}
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return this.size;
}

@Override
public int height() {
	// TODO Auto-generated method stub
	if (empty()) {
		return -1;
	} else {
		return root.getHeight();
	}
}  

}