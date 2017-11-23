package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ 
	  size = 0; 
	  root = null; 
  }

  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

public boolean insert(String s) {
	if (root == null) {
		root = new BST_Node(s);
		root.root = this;
		size();
		return true;
	} else {
		return root.insertNode(s);
	}
}

public boolean remove(String s) {
	if (root == null) {
		return false;
	} else if (root.left == null && root.right == null) {
		if (root.data.compareTo(s) == 0) {
			root = null;
			return true;
		} else {
			return false;
		}
	} else if (root.left == null && root.right != null) {
		if (root.data.compareTo(s) == 0) {
			root = root.right;
		}
	} else if (root.left != null && root.right == null) {
		if (root.data.compareTo(s) == 0) {
			root = root.left;
		}
	}
	return root.removeNode(s, null);
}

public String findMin() {
	if (size == 0) {
		return null;
	}
	return root.findMin().data;
}

public String findMax() {
	if (size == 0) {
		return null;
	}
	return root.findMax().data;
}

public boolean empty() {
	if (size() == 0 && root == null) {
		return true;
	} else {
		return false;
	}
}

public boolean contains(String s) {
	if (root == null) {
		return false;
	} else {
		return root.containsNode(s);
	}
}

public int size() {
	size = size(root);
	return size;
}

// Helper method to recursively go through the tree to get the size
public int size(BST_Node node){
	if (node == null) {
		return 0;
	} else {
		return 1 + (size(node.left)) + (size(node.right));
	}
}

public int height() {
	if (root == null) {
		return -1;
	} else if (root.left == null && root.right == null) {
		return 0;
	} else if (root.left.left == null && root.left.right == null && root.right.right == null && root.right.left == null) {
		// I know how bad this ^ looks
		return 1;
		
	}
	return root.getHeight();
}

}
