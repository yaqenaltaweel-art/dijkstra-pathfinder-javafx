public class PathUtil {

	public static MyArrayList<String> buildPath(Graph g, Dijkstra d, int dest) {

		MyArrayList<String> path = new MyArrayList<>();

		if (d.parent == null || d.parent[dest] == -1)
			return path;

		int cur = dest;
		while (cur != -1) {
			path.add(g.vertices.get(cur));
			cur = d.parent[cur];
		}

		// reverse
		int i = 0, j = path.size() - 1;
		while (i < j) {
			String tmp = path.get(i);
			path.set(i, path.get(j));
			path.set(j, tmp);
			i++;
			j--;
		}

		return path;
	}
}
