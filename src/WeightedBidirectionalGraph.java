import java.util.*;

public class WeightedBidirectionalGraph {
	private Map<String, Integer> vertexMap;
	// adjacency matrix to store the edges and their weights
	private int[][] adjacencyMatrix;
	// number of vertices in the graph
	private int numVertices;
	private List<Edge> edges;
	private int numEdges;


	// constructor to initialize the graph with the given number of vertices to be zero
	public WeightedBidirectionalGraph() {
		vertexMap = new HashMap<>();
		numVertices = 0;
		numEdges = 0;
		edges = new ArrayList<>();
	}

	public void addVertex(String vertex) {
		if (!vertexMap.containsKey(vertex)) {
			vertexMap.put(vertex, numVertices++);
			int[][] newAdjacencyMatrix = new int[numVertices][numVertices];
			for (int i = 0; i < numVertices - 1; i++) {
				System.arraycopy(adjacencyMatrix[i], 0, newAdjacencyMatrix[i], 0, numVertices - 1);
			}
			adjacencyMatrix = newAdjacencyMatrix;
		}
	}

	public void addEdge(String source, String destination, int weight) {
		if (!vertexMap.containsKey(source) || !vertexMap.containsKey(destination)) {
			throw new IllegalArgumentException("Source or destination vertex not found in graph");
		}
		int sourceIndex = vertexMap.get(source);
		int destIndex = vertexMap.get(destination);
		adjacencyMatrix[sourceIndex][destIndex] = weight;
		adjacencyMatrix[destIndex][sourceIndex] = weight;
		edges.add(new Edge(source, destination, weight));
		numEdges++;
	}

	public void removeEdge(String source, String destination) {
		if (!vertexMap.containsKey(source) || !vertexMap.containsKey(destination)) {
			throw new IllegalArgumentException("Source or destination vertex not found in graph");
		}
		int sourceIndex = vertexMap.get(source);
		int destIndex = vertexMap.get(destination);
		adjacencyMatrix[sourceIndex][destIndex] = 0;
		adjacencyMatrix[destIndex][sourceIndex] = 0;
		for (Edge edge : edges) {
			if (edge.getSource().equals(source) && edge.getDestination().equals(destination)) {
				edges.remove(edge);
				numEdges--;
				break;
			}
		}
	}

	public boolean hasEdge(String source, String destination) {
		if (!vertexMap.containsKey(source) || !vertexMap.containsKey(destination)) {
			throw new IllegalArgumentException("Source or destination vertex not found in graph");
		}
		int sourceIndex = vertexMap.get(source);
		int destIndex = vertexMap.get(destination);
		return adjacencyMatrix[sourceIndex][destIndex] > 0 && adjacencyMatrix[destIndex][sourceIndex] > 0;
	}

	public int getWeight(String source, String destination) {
		if (!vertexMap.containsKey(source) || !vertexMap.containsKey(destination)) {
			throw new IllegalArgumentException("Source or destination vertex not found in graph");
		}
		int sourceIndex = vertexMap.get(source);
		int destIndex = vertexMap.get(destination);
		return adjacencyMatrix[sourceIndex][destIndex];
	}

	public int getNumVertices() {
		return numVertices;
	}

	public int getNumEdges() {
		return numEdges;
	}

	public void printGraph() {
		System.out.print("   ");
		for (String vertex : vertexMap.keySet()) {
			System.out.print(vertex + " ");
		}
		System.out.println();
		for (String vertex : vertexMap.keySet()) {
			System.out.print(vertex + "  ");
			int vertexIndex = vertexMap.get(vertex);
			for (int i = 0; i < numVertices; i++) {
				System.out.print(adjacencyMatrix[vertexIndex][i] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * The time complexity of the isConnected() method is O(V^2), where V is the number of vertices in the graph.
	 * This is because we need to visit each node and perform a DFS on its adjacent nodes, which can take up to V iterations in the worst case.
	 * The space complexity is also O(V), since we need to store the visited set.
	 */
	public boolean isConnected() {
		Set<Integer> visited = new HashSet<>();
		dfs(0, visited);
		return visited.size() == numVertices;
	}

	/**
	 * Initialize a set of visited nodes as empty.
	 * Pick any node as the starting node and mark it as visited.
	 * For each adjacent node that has not been visited, perform a recursive DFS starting from that node, and mark all visited nodes.
	 * If the number of visited nodes is equal to the total number of nodes in the graph, then the graph is connected. Otherwise, it is disconnected.
	 */
	private void dfs(int vertexIndex, Set<Integer> visited) {
		visited.add(vertexIndex);
		for (int i = 0; i < numVertices; i++) {
			if (adjacencyMatrix[vertexIndex][i] > 0 && !visited.contains(i)) {
				dfs(i, visited);
			}
		}
	}

	public List<Edge> getEdgeList() {
		return edges;
	}

	public List<String> getNeighbors(String vertex) {
		if (!vertexMap.containsKey(vertex)) {
			throw new IllegalArgumentException("Vertex not found in graph");
		}
		int vertexIndex = vertexMap.get(vertex);
		List<String> neighbors = new ArrayList<>();
		for (int i = 0; i < numVertices; i++) {
			if (adjacencyMatrix[vertexIndex][i] > 0) {
				String neighbor = getKeyByValue(vertexMap, i);
				neighbors.add(neighbor);
			}
		}
		return neighbors;
	}

	private String getKeyByValue(Map<String, Integer> map, int value) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == value) {
				return entry.getKey();
			}
		}
		return null;
	}

	public int getIndex(String vertex) {
		int index = 0;
		for (String v : vertexMap.keySet()) {
			if (v.equals(vertex)) {
				return index;
			}
			index++;
		}
		return -1; // vertex not found in the graph
	}
}
