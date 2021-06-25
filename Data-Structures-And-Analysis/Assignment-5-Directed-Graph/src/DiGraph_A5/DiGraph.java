package DiGraph_A5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet; 
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
  // and the topo sort operation
	private Hashtable<String, Node> nodes;  // for hashing the string to the node
	private HashSet <Long> nodeIDs;			// IDs for node labels
	private HashSet <Long> edgeIDs;			// IDs for edges
	
	public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  nodes = new Hashtable<String, Node>();
	  nodeIDs = new HashSet<Long>();
	  edgeIDs = new HashSet<Long>();
	  
  }

@Override
public boolean addNode(long idNum, String label) {
	// TODO Auto-generated method stub
	// check if the node has a matching ID or label
	if (label == null || idNum < 0 || nodeIDs.contains(idNum) || nodes.containsKey(label)) { // if no label or it already exists
		return false;
	} else {
		Node toBeInserted = new Node(label,idNum);	// create the node
		nodes.put(label, toBeInserted);				// insert node into the hashmap
		nodeIDs.add(idNum);							// add the node's ID to the hashset
		return true;
	}
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	// TODO Auto-generated method stub
	if (edgeIDs.contains(idNum) || idNum < 0) {		// double check for duplicates
		return false;
	}
	if (!edgeIDs.contains(idNum) || idNum >= 0) {		// edge can't already exist
		if (nodes.containsKey(sLabel) && nodes.containsKey(dLabel)) {	// if the two nodes exist
			if (!nodes.get(sLabel).outEdges().containsKey(dLabel)) {	// can't add the same edge twice
				
				edgeIDs.add(idNum);						// saving edge's ID in hashSet
				Node source = nodes.get(sLabel);		// saving source node
				Node destination = nodes.get(dLabel);	// saving destination node
				Edge edge = new Edge(eLabel, source, destination, idNum);
				source.outEdges().put(dLabel, edge);	// hooking up nodes and edges
				destination.inEdges().put(sLabel, edge);
				return true;
			}
		} 
	}
	return false;
}

@Override
public boolean delNode(String label) {
	if (label != null && nodes.containsKey(label)) {	// make sure the node exists
		for (Edge EdgesInto: nodes.get(label).inEdges.values()) {	// loop through surrounding Nodes
			Node node = EdgesInto.getSource();		// get surrounding node
			if (node != null) {
				node.outEdges.remove(EdgesInto.getSource().getName());	// remove edge from surrounding node
				edgeIDs.remove(EdgesInto.ID);
			}
		}
		for (Edge EdgesOut: nodes.get(label).outEdges.values()) {	// same as above 
			Node node = EdgesOut.getDestination();					// but with edges out
			if (node != null) {
				node.inEdges.remove(EdgesOut.getSource().getName());
				edgeIDs.remove(EdgesOut.ID);
			}
		}
		nodeIDs.remove(nodes.get(label).getID());
		nodes.remove(label);	// remove the label from the hashtable
		return true;
		
	}
	return false;
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	if (nodes.containsKey(sLabel) && nodes.containsKey(dLabel)) {	
		
		Node source = nodes.get(sLabel);		// get source node
		Node destination = nodes.get(dLabel);	// get destination node
		long eg;
		if (source.outEdges.get(dLabel) != null) {
			eg = source.outEdges.get(dLabel).ID;	// get the ID of the edge
		} else {
			return false;
		}
		source.outEdges.remove(dLabel);		// remove the Node from the source's out
		destination.inEdges.remove(sLabel);	// remove the Node from the destination's in
		edgeIDs.remove(eg);	
		return true;
	} else {
		return false;
	}
}

@Override
public long numNodes() {
	// TODO Auto-generated method stub
	return nodes.size();
}

@Override
public long numEdges() {
	// TODO Auto-generated method stub
	if (nodes.size() <= 1) {
		return 0;
	} else {
		return edgeIDs.size();
	}
}

@Override
public String[] topoSort() {
	Hashtable<String, Node> copy = new Hashtable<String,Node>();
	copy.putAll(this.nodes);	// to save the graph for after
	List<String> topoSort = new ArrayList<String>();
	List<String> zeroDegreeNodes = new ArrayList<String>();
	Hashtable<String, Node> nodesToSort = nodes;	// another copy to use
	
	while (true) {
						// until a return is reached (no more 0-inDegree nodes or cycle is Acyclic)
		boolean zeroFound = false;
		for (Map.Entry<String, Node> node: nodesToSort.entrySet()) {	// go through all edges
			Node aNode = node.getValue();
			if (aNode.inEdgeSize() == 0) {				// if a node has 0 inEdges
				zeroDegreeNodes.add(aNode.getName());	// add it
				zeroFound = true;						// keep topo sorting
			}
		}

		for (String label: zeroDegreeNodes) {	// add 0-inDegree nodes to the topo sorted arraylist
			topoSort.add(label);
			this.delNode(label);				// then delete it
		}
		zeroDegreeNodes.clear();				// clear out the nodes just added

		if (zeroFound == false) {				// no more zeros: done or cycle is acyclic
			if (nodesToSort.size() != 0) {		
				return null;					// acyclic
			} else {
				nodes = copy;					// restore original graph
				String[] done =	topoSort.toArray(new String[(topoSort.size())]);	// put the arraylist into a string array
				return done;
			}
		}
	}
}

  // rest of your code to implement the various operations
}
