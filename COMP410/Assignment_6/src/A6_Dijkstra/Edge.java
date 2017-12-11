package A6_Dijkstra;

import A6_Dijkstra.Node;

public class Edge {

	private String label;
	private Node sourceNode;
	private Node destinationNode;
	public long weight;
	public long ID;
	
	public Edge(String label, Node from, Node to, long id, long weight) {
		this.label = label;
		this.sourceNode = from;
		this.destinationNode = to;
		this.ID = id;
		this.weight = weight;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public Node getSource() {
		return sourceNode;
	}
	
	public Node getDestination() {
		return destinationNode;
	}
	
	
}
