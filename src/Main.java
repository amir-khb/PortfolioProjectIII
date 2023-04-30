import java.io.FileNotFoundException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		TextReader t = new TextReader();
		WeightedBidirectionalGraph graph = t.returnGraph();

		List<Edge> edges = graph.getEdgeList();

		//Visualizing the graph
		System.out.println("Visualizing the graph\n");
		for (Edge edge : edges) {
			System.out.println(edge.getSource() + " --(" + edge.getWeight() + ")-- " + edge.getDestination());
		}
		System.out.println();

		System.out.println("Is the graph connected: "+graph.isConnected()+"\n");

		MinimumSpanningTree mst = new MinimumSpanningTree(graph, "Laem Chabang");

		// print the edges of the minimum spanning tree and its total weight
		System.out.println("Minimum Spanning Tree:");
		for (Edge edge : mst.getMinimumSpanningTree()) {
			System.out.println("source: "+edge.getSource()+" Dest: "+edge.getDestination()+" Weight: "+edge.getWeight());
		}
		System.out.println("Total Weight: " + mst.getTotalWeight());

	}
}