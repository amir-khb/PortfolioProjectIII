import java.io.FileNotFoundException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		TextReader t = new TextReader();
		WeightedBidirectionalGraph graph = t.returnGraph();
//		graph.addVertex("A");
//		graph.addVertex("B");
//		graph.addVertex("C");
//		graph.addVertex("D");
//
//		graph.addEdge("A", "B", 2);
//		graph.addEdge("B", "C", 1);
//		graph.addEdge("C", "D", 3);
//		graph.addEdge("D", "A", 4);
//		graph.addEdge("A", "C", 5);
//		graph.removeEdge("A", "C");

		List<Edge> edges = graph.getEdgeList();
		for (Edge edge : edges) {
			System.out.println(edge.getSource() + " --(" + edge.getWeight() + ")-- " + edge.getDestination());
		}
//		graph.printGraph();
		System.out.println(graph.isConnected());
//		for (String s : graph.getNeighbors("C")) {
//			System.out.println(s);
//		}
		MinimumSpanningTree mst = new MinimumSpanningTree(graph, "Laem Chabang");

		// print the edges of the minimum spanning tree and its total weight
		System.out.println("Minimum Spanning Tree:");
		for (Edge edge : mst.getMinimumSpanningTree()) {
			System.out.println("source: "+edge.getSource()+" Dest: "+edge.getDestination()+" Weight: "+edge.getWeight());
		}
		System.out.println("Total Weight: " + mst.getTotalWeight());

	}
}
