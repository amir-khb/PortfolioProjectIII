import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;

public class TextReader {
	private HashSet<String> vertices;
	public TextReader() throws FileNotFoundException {
		vertices = new HashSet<>();
		BufferedReader in = new BufferedReader(new FileReader("network.txt"));
		try {
			while (true) {
				String s = in.readLine();
				if (s == null) break;
				String a[] = s.split(",");
				vertices.add(a[0]);
				vertices.add(a[1]);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println();
	}
	public WeightedBidirectionalGraph returnGraph() throws FileNotFoundException {
		WeightedBidirectionalGraph graph = new WeightedBidirectionalGraph();
		for(String vertex: vertices){
			graph.addVertex(vertex);
		}
		BufferedReader in = new BufferedReader(new FileReader("network.txt"));
		try {
			while (true) {
				String s = in.readLine();
				if (s == null) break;
				String a[] = s.split(",");
				graph.addEdge(a[0], a[1], Integer.parseInt(a[2]));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return graph;
	}
}
