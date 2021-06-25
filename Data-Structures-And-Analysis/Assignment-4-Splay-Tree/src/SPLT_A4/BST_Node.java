package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations


  public BST_Node containsNode(String s) { 
	  if (s.equals(data)) {
		  return splay(this);			// base case
	  }

	  if (data.compareTo(s) > 0) {	// if s is lexiconically less
		  if (left == null) {
			  return null;
		  } else {
			  return left.containsNode(s);
		  }
	  }

	  if (data.compareTo(s) < 0) {	// if lexiconically greater
		  if (right == null) {
			  return null;
		  } else {
			  return right.containsNode(s);
		  }
	  }
	  
	  return splay(this); 
  } //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.

  public BST_Node insertNode(String s) { 	
	  if (data.compareTo(s) > 0) {		// insert further down to the right
		  if (left == null) {
			  left = new BST_Node(s);
			  left.par = this;
			  return splay(left);
		  } else {
			  return left.insertNode(s);
		  }
	  }

	  if (data.compareTo(s) < 0) {	// insert further down to the right
		  if (right == null) {
			  right = new BST_Node(s);
			  right.par = this;
			  return splay(right);
		  } else {
			  return right.insertNode(s);
		  }
	  }

	  return null;		// we have a duplicate

  } //Really same logic as above note

  public boolean removeNode(String s) { 
	  if (data == null) {
		  return false;
	  }
	  return false; 
  } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
  public BST_Node findMin() { 
	  if (left != null) {
		  return left.findMin();
	  } else {
		  return splay(this);
	  }
  } 
  public BST_Node findMax() { 
	  if (right != null) {
		  return right.findMax();
	  } else {
		  return splay(this);
	  }
  }
  public int getHeight() { 
	  int leftSum = 0;
	  int rightSum = 0;
	  if (left != null) {
		  leftSum += left.getHeight() + 1;
	  } else if (right != null) {
		  rightSum += right.getHeight() + 1;
	  }
	  int ans = (leftSum > rightSum) ? leftSum : rightSum;
	  return ans;
  }

  private BST_Node splay(BST_Node toSplay) { // bottom-up implementation  
	  while (toSplay.par != null) {
		  if (toSplay.par != null && toSplay.par.par != null) {		// zig-zig or zig-zag - splaying up a tree
			  BST_Node parent = toSplay.par; 
			  BST_Node grandparent = toSplay.par.par;
			  BST_Node greatgrandparent = toSplay.par.par.par;
			  if (parent == grandparent.left && toSplay == parent.left) { 			// case 1
				  zigZigAsLeftLeftGrandChild(toSplay);
			  } else if (parent == grandparent.right && toSplay == parent.right) {	// case 2
				  zigZigAsRightRightGrandChild(toSplay);
			  } else if (parent == grandparent.left && toSplay == parent.right) {	// case 3
				  zigZagAsLeftRightGrandchild(toSplay);
			  } else {																// case 4
				  zigZagAsRightLeftGrandchild(toSplay);
			  }

			  if (greatgrandparent == null) { 
				  toSplay.par = null;
			  } else {
				  if (greatgrandparent.left == grandparent) {
					  toSplay.par = greatgrandparent;
					  greatgrandparent.left = toSplay;
				  } else {
					  toSplay.par = greatgrandparent;
					  greatgrandparent.right = toSplay;
				  }
			  }
		  } else {							// zig - splay around the root of the tree
			  BST_Node parent = toSplay.par;
			  if (toSplay == parent.left) { // if toSplay is the left node of its parent
				  zigAsLeftChild(toSplay);
			  } else {						// if toSplay is the right node of its parent
				  zigAsRightChild(toSplay);
			  }
			  toSplay.par = null;
			  return toSplay;
		  }
	  }
	  return toSplay;
  } //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  

  // --- end example methods --------------------------------------
  
  private void zigAsRightChild(BST_Node toSplay) {
	  
	  // keeping track of all nodes to be linked
	  BST_Node parent = toSplay.par;
	  BST_Node leftChild = toSplay.left;
	  BST_Node rightChild = toSplay.right;
	  BST_Node parentLeftChild = parent.left;
	  
	  // linking up nodes
	  toSplay.left = parent;
	  parent.par = toSplay;
	  toSplay.right = rightChild;
	  if (rightChild != null) {
		  rightChild.par = toSplay;
	  }
	  parent.left = parentLeftChild;
	  if (parentLeftChild != null) {
		  parentLeftChild.par = parent;
	  }
	  parent.right = leftChild;
	  if (leftChild != null) {
		  leftChild.par = parent;
	  }
  }
  
  private void zigAsLeftChild(BST_Node toSplay) {

	  // keeping track of all nodes to be linked
	  BST_Node parent = toSplay.par;
	  BST_Node leftChild = toSplay.left;
	  BST_Node rightChild = toSplay.right;
	  BST_Node parentRightChild = parent.right;
	  
	  // link up nodes
	  toSplay.left = leftChild;
	  if (leftChild != null) {
		  leftChild.par = toSplay;
	  }
	  toSplay.right = parent;
	  parent.par = toSplay;
	  parent.left = rightChild;
	  if (rightChild != null) {
		  rightChild.par = parent;
	  }
	  parent.right = parentRightChild;
	  if (parentRightChild != null) {
		  parentRightChild.par = parent;
	  }
  }

  private void zigZigAsLeftLeftGrandChild(BST_Node toSplay) {
	  // keeping track of nodes to be linked
	  BST_Node grandparent = toSplay.par.par; 
	  BST_Node parent = toSplay.par;
	  BST_Node leftChild = toSplay.left;
	  BST_Node rightChild = toSplay.right;
	  BST_Node parentRightChild = parent.right;
	  BST_Node grandparentRightChild = grandparent.right;
	  
	  // linking up nodes
	  toSplay.left = leftChild;
	  if (leftChild != null) {
		  leftChild.par = toSplay;
	  }
	  toSplay.right = parent;
	  parent.par = toSplay;
	  parent.left = rightChild;
	  if (rightChild != null) {
		  rightChild.par = parent;
	  }
	  parent.right = grandparent;
	  grandparent.par = parent;
	  grandparent.left = parentRightChild;
	  if (parentRightChild != null) {
		  parentRightChild.par = grandparent;
	  }
	  grandparent.right = grandparentRightChild;
	  if (grandparentRightChild != null) {
		  grandparentRightChild.par = grandparent;
	  }
  }
  
  private void zigZigAsRightRightGrandChild(BST_Node toSplay) {
	  // keeping track of nodes to be linked
	  BST_Node grandparent = toSplay.par.par; 
	  BST_Node parent = toSplay.par;
	  BST_Node grandparentLeftChild = grandparent.left;
	  BST_Node parentLeftChild = parent.left;
	  BST_Node leftChild = toSplay.left;
	  BST_Node rightChild = toSplay.right;
	  
	  // linking up the nodes
	  toSplay.left = parent;
	  parent.par = toSplay;
	  toSplay.right = rightChild;
	  if (rightChild != null) {
		  rightChild.par = toSplay;
	  }
	  parent.left = grandparent;
	  grandparent.par = parent;
	  parent.right = leftChild;
	  if (leftChild != null) {
		  leftChild.par = parent;
	  }
	  grandparent.left = grandparentLeftChild;
	  if (grandparentLeftChild != null) {
		  grandparentLeftChild.par = grandparent;
	  }
	  grandparent.right = parentLeftChild;
	  if (parentLeftChild != null) {
		  parentLeftChild.par = grandparent;
	  }
	  
  }

  private void zigZagAsLeftRightGrandchild(BST_Node toSplay) { 
	  // keeping track of nodes to be linked
	  BST_Node grandparent = toSplay.par.par; 
	  BST_Node parent = toSplay.par; 
	  BST_Node leftChild = toSplay.left;
	  BST_Node rightChild = toSplay.right;
	  BST_Node parentLeftChild = parent.left;
	  BST_Node grandparentRightChild = grandparent.right;
	  
	// linking up the nodes
	  parentLeftChild = parent.left;
      leftChild = toSplay.left;
      rightChild = toSplay.right;
      grandparentRightChild = grandparent.right;

      toSplay.left = parent;
      parent.par = toSplay;
      toSplay.right = grandparent;
      grandparent.par = toSplay;
      parent.left = parentLeftChild;
      if (parentLeftChild != null) {
    	  parentLeftChild.par = parent;
      }
      parent.right = leftChild;
      if (leftChild != null) {
    	  leftChild.par = parent;
      }
      grandparent.left = rightChild;
      if (rightChild != null) {
    	  rightChild.par = grandparent;
      }
      grandparent.right = grandparentRightChild;
      if (grandparentRightChild != null) {
    	  grandparentRightChild.par = grandparent;
      }
  }
  
  private void zigZagAsRightLeftGrandchild(BST_Node toSplay) {
	  // keeping track of nodes to be linked
	  BST_Node grandparent = toSplay.par.par; 
	  BST_Node parent = toSplay.par;  
	  BST_Node leftChild = toSplay.left;
	  BST_Node rightChild = toSplay.right;
	  BST_Node grandparentLeftChild = grandparent.left;
	  BST_Node parentRightChild = parent.right;
	  
	  // linking up the nodes
      toSplay.left = grandparent;
      grandparent.par = toSplay;
      toSplay.right = parent;
      parent.par = toSplay;
      parent.left = rightChild;
      if (rightChild != null) {
    	  rightChild.par = parent;
      }
      parent.right = parentRightChild;
      if (parentRightChild != null) {
    	  parentRightChild.par = parent;
      }
      grandparent.left = grandparentLeftChild;
      if (grandparentLeftChild != null) {
    	  grandparentLeftChild.par = grandparent;
      }
      grandparent.right = leftChild;
      if (leftChild != null) {
    	  leftChild.par = grandparent;
      }
  }
 
  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  
}