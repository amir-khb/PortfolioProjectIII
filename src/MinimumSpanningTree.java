import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
	// instance variables
	private WeightedBidirectionalGraph graph;
	private List<Edge> minimumSpanningTree;

	// constructor
	public MinimumSpanningTree(WeightedBidirectionalGraph graph) {
		this.graph = graph;
		minimumSpanningTree = new ArrayList<>();
		computeMinimumSpanningTree();
	}

	// private helper method to compute the minimum spanning tree
	private void computeMinimumSpanningTree() {
		// create a priority queue to store the edges of the graph, ordered by weight
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(graph.getNumEdges());

		// create a boolean array to store the visited status of each vertex
		boolean[] visited = new boolean[graph.getNumVertices()];
		// set all vertices as unvisited
		for (int i = 0; i < graph.getNumVertices(); i++) {
			visited[i] = false;
		}

		// start with vertex "0"
		visited[0] = true;
		// add all edges connected to vertex "0" to the queue
		List<String> neighbors = graph.getNeighbors("A");
		for (String neighbor : neighbors) {
			edgeQueue.offer(new Edge("A", neighbor, graph.getWeight("A", neighbor)));
		}

		// loop until all vertices have been visited
		while (edgeQueue.size() > 0) {
			// remove the edge with the lowest weight from the queue
			Edge edge = edgeQueue.poll();

			// check if both vertices of the edge have been visited
			String source = edge.getSource();
			String destination = edge.getDestination();
			if (visited[graph.getIndex(source)] && visited[graph.getIndex(destination)]) {
				continue;
			}

			// add the edge to the minimum spanning tree
			minimumSpanningTree.add(edge);

			// mark the unvisited vertex as visited
			String newVertex = visited[graph.getIndex(source)] ? destination : source;
			visited[graph.getIndex(newVertex)] = true;

			// add all edges connected to the new vertex to the queue
			neighbors = graph.getNeighbors(newVertex);
			for (String neighbor : neighbors) {
				if (!visited[graph.getIndex(neighbor)]) {
					edgeQueue.offer(new Edge(newVertex, neighbor,
							graph.getWeight(newVertex, neighbor)));
				}
			}
		}
	}

	// public getter method for the minimum spanning tree
	public List<Edge> getMinimumSpanningTree() {
		return minimumSpanningTree;
	}

	// public method to calculate the total weight of the minimum spanning tree
	public int getTotalWeight() {
		int totalWeight = 0;
		for (Edge edge : minimumSpanningTree) {
			totalWeight += edge.getWeight();
		}
		return totalWeight;
	}
}
