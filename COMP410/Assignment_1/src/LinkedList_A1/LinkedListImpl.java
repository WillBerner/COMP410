/**
 * COMP 410 
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node sentinel; //this will be the entry point to your linked list (the head)
  int numElts = 0;
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel=new Node(Double.NaN); //Note that the root's data is not a true part of your data set!
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
public boolean insert(double elt, int index) {
	// checking sizes
	if (index < 0 || index > numElts) {
		return false;
	}
	// Edge case
	if (index == 0 && numElts == 0) {
		Node inserted = new Node(elt);
		sentinel.next = inserted;
		sentinel.prev = inserted;
		inserted.next = sentinel;
		inserted.prev = sentinel;
		numElts++;
		return true;
	}
	
	// Skipping to the inserted Node
	int counter = -1;
	Node currentNode = sentinel;
	while (counter < index -1) {
		currentNode = currentNode.next;
		counter++;
	}
	// Grabbing Nodes to move positions
	Node inserted = new Node(elt);
	Node following = currentNode.next;
	
	// Reassigning pointers
	currentNode.next = inserted;
	inserted.prev = currentNode;
	inserted.next = following;
	following.prev = inserted;
	
	// Increment Size
	numElts++;
	
	// Return
	return true;
}

@Override
public boolean remove(int index) {
	// checking sizes
	if (index < 0 || index > numElts) {
		return false;
	}
	
	// Skipping to the inserted Node
	int counter = -1;
	Node currentNode = sentinel;
	while (counter < index) {
		currentNode = currentNode.next;
		counter++;
	}
	// Grabbing Nodes to reassign
	Node prev = currentNode.prev;
	Node next = currentNode.next;
		
	// Reassigning Nodes
	prev.next = next;
	next.prev = prev;
	
	// decrementing size
	numElts--;
	return true;
}

@Override
public double get(int index) {
	// Checking size
	if (index < 0 || index > numElts) {
		return Double.NaN;
	}
	if (numElts == 0 && index == 0) {
		return Double.NaN;
	}
	
	// Skipping to grabbed Node
	int counter = -1;
	Node currentNode = sentinel;
	while (counter < index) {
		currentNode = currentNode.next;
		counter++;
	}
	
	// Return Node's data
	
	return currentNode.data;
}

@Override
public int size() {
	return numElts;
}

@Override
public boolean isEmpty() {
	if (numElts > 0) {
		return false;	
	} else if (numElts == 0){
		return true;
	} else {
		throw new RuntimeException("wtf negative size??");
	}

}

@Override
public void clear() {
	sentinel.next = null;
	sentinel.prev = null;
	numElts = 0;
	
}

}