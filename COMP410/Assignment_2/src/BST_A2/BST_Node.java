package BST_A2;

public class BST_Node {
  String data;
  BST_Node left, right, parent;
  BST root;
  
  BST_Node(String data) { 
	  this.data = data; 
	  BST root = null;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ 
	  return data; 
  }
  public BST_Node getLeft(){ 
	  return left; 
  }
  public BST_Node getRight(){ 
	  return right; 
  }
  public BST_Node getParent() {
	  return parent;
  }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations


  public boolean containsNode(String s) { 
	  if (data.compareTo(s) > 0) {
		  if (left != null) {
			  return left.containsNode(s);
		  } else {
			  return false;
		  }
	  } else if (data.compareTo(s) < 0) {
		  if (right != null) {
			  return right.containsNode(s);
		  } else {
			  return false;
		  }
	  } else {
		  return true;
	  }
  }
  public boolean insertNode(String s) { 
	 // if lexigraphically prior to data, recursively call insert on the left node
	 // or insert a new node if none exists
	  if (data.compareTo(s) > 0) {
		  if (left != null) {
			 return left.insertNode(s);
		  } else {
			  left = new BST_Node(s);
			  left.parent = this;
			  return true;
		  }
	  } else if (data.compareTo(s) < 0) {
	 // if lexigraphically after data, recursively call insert on the right node
	 // or insert a new node if none exists
		  if (right != null) {
			  return right.insertNode(s);
		  } else {
			  right = new BST_Node(s);
			  right.parent = this;
			  return true;
		  }
	  } else {
	 // if the data and string are the same (0), this node already exists; return false
		  return false;
	  }
  }
  public boolean removeNode(String s, BST_Node parent) { 
	  
	  if (data.compareTo(s) > 0) {
		  if (left != null) {
			  return left.removeNode(s, this);
		  } else {
			  return false;
		  }
	  } else if (data.compareTo(s) < 0) {
		  if (right != null) {
			  return right.removeNode(s, this);
		  } else {
			  return false;
		  }
	  } else {
		  if (left != null && right != null) {
			  // If just a leaf:
			  data = right.findMin().data;
			  right.removeNode(data, this);
		  } else if (parent.left == this) {
			  if (left != null) {
				  parent.left = left;
			  } else {
				  parent.left = right;
			  }
		  } else if (parent.right == this) {
			  if (left != null) {
				  parent.right = left;
			  } else {
				  parent.right = right;
			  }
		  }
		  return true;
	  }
  }
  public BST_Node findMin() {
	  if (left != null) {
		  return left.findMin();
	  }
	  return this; 
  }
  public BST_Node findMax() {
	  if (right != null) {
		  return right.findMax();
	  }
	  return this; 
  }
  public int getHeight() { 
	  int leftHeight = 1;
	  int rightHeight = 1;
	  if (left != null) {
		  leftHeight = left.getHeight();
	  } else if (right != null) {
		  rightHeight = right.getHeight();
	  }
	  
	  if (leftHeight > rightHeight) {
		  return leftHeight + 1;
	  } else {
		  return rightHeight + 1;
	  }
  }

  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}