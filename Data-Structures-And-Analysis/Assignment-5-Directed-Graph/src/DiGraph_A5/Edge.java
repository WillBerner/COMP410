package DiGraph_A5;

public class Edge {

	private String label;
	private Node sourceNode;
	private Node destinationNode;
	public int wieght;
	public long ID;
	
	public Edge(String label, Node from, Node to, long id) {
		this.label = label;
		this.sourceNode = from;
		this.destinationNode = to;
		this.ID = id;
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
