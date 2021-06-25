package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
												//be null. This is ok! Just build out 
												//from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); 	//0th will be unused for simplicity 
													//of child/parent computations...
													//the book/animation page both do this.
		size = 0;
	}

	@Override
	public void delMin() {
		if (size == 0) {
			return;
		} else if (size == 1) {
			array[1] = null;
			size--;
			return;
		} else {
			array[1] = array[size];
			size--;
			array[size + 1] = null;
			if (size != 1) {
				bubbleDown(1);
			}
			return;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void insert(EntryPair entry) {
		if (size == 0){
			array[1] = entry;
			size++;
		} else {
			size++;
			array[size] = entry;
			bubbleUp(size);
			return;
		}
	}

	@Override
	public EntryPair getMin() {
		EntryPair min = (size == 0) ? null : array[1];
		return min;
	}
	
	@Override
	public void build(EntryPair[] entries) {
		size = entries.length;
		// fill in an array with the entries
		for (int i = 0; i < size; i++) {
			array[i + 1] = entries[i];
		}
		// while you're not at array[0], bubble down each entryPair
		for (int i = size; i >= 1; i--) {
			bubbleDown(i);
		}
	}

	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	}

	// helper method to bubble an entryPair down the tree
	public void bubbleDown(int i) {
		while (i * 2 != size + 1) {
			// find which branch has higher priority
			int nextChild = getHigherPriority(i * 2);
			// swap if priorities are out of order
			if (comparePriority(array[i], array[nextChild])) {
				EntryPair temp = array[i];
				array[i] = array[nextChild];
				array[nextChild]= temp;
				i = nextChild;
			} else {
				break;
			}
		}
		return;
	}

	// helper method to quickly get which of two children has a higher priority
	public int getHigherPriority(int i) {
		if (i == size){
			return i;
		} else {
			if (array[i] != null && array[i + 1] != null) {
				// array[i] and array[i+1] correspond to two children of a node
				int priority = (array[i].getPriority() < array[i + 1].getPriority()) ? i : i + 1;
				return priority;
			} else {
				return i;
			}
		}
	}

	// helper method to compare two entryPair priorities
	public boolean comparePriority (EntryPair pair1, EntryPair pair2) {
		if (pair1 != null && pair2 != null) {
			boolean priority = (pair1.getPriority() > pair2.getPriority()) ? true : false;
			return priority;
		} else {
			return false;
		}
	}
	
	// helper method to bubble an entryPair up the tree
	public void bubbleUp(int i) {
		// similar to bubbleDown...
		while (comparePriority(array[i / 2], array[i]) && i > 1) {
			EntryPair temp = array[i / 2];
			array[i / 2] = array[i];
			array[i] = temp;
			i = i / 2;
		}
	}
}