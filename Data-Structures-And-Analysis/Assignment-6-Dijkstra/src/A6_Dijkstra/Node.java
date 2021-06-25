package A6_Dijkstra;

import java.util.HashMap;

public class Node {

	private String name;
	private long ID;
	public HashMap<String, Edge> inEdges;
	public HashMap<String, Edge> outEdges;
	
	
	public Node(String name, long ID) {
		this.name = name;	// unique labels.
		this.ID = ID;		// greater than 0 and unique
		inEdges = new HashMap<String, Edge>();
		outEdges = new HashMap<String, Edge>();
		
	}
	
	
	public long sizeOut() {
		return outEdges.size();
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public long getID() {
		return this.ID;
	}
	
	public HashMap<String, Edge> inEdges() {
		return inEdges;
	}
	
	public HashMap<String, Edge> outEdges() {
		return outEdges;
	}
	
	public long outEdgeSize() {
		return outEdges.size();
	}
	
	public long inEdgeSize() {
		return inEdges.size();
	}
}
