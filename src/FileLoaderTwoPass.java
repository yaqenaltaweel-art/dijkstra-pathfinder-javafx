import java.io.File;
import java.util.Scanner;

public class FileLoaderTwoPass {

	public Graph graph;
	public String start, end;
	public int option;

	public void load(File file) throws Exception {

		SortedVertexList vList = new SortedVertexList();

		Scanner sc1 = new Scanner(file);

		start = sc1.next();
		end = sc1.next();
		option = sc1.nextInt();

		while (sc1.hasNext()) {
			String from = sc1.next();
			String to = sc1.next();
			sc1.nextDouble(); // distance
			sc1.nextDouble(); // time

			vList.insert(from);
			vList.insert(to);
		}
		sc1.close();

		graph = new Graph(vList.size());
		graph.vertices = vList;

		Scanner sc2 = new Scanner(file);

		sc2.next();
		sc2.next();
		sc2.next(); // skip first line

		while (sc2.hasNext()) {
			String from = sc2.next();
			String to = sc2.next();
			double d = sc2.nextDouble();
			double t = sc2.nextDouble();

			int u = graph.vertices.binarySearch(from);
			int v = graph.vertices.binarySearch(to);

			graph.adj.get(u).add(new Edge(v, d, t));
		}
		sc2.close();
	}
}
