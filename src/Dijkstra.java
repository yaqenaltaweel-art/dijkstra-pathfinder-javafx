public class Dijkstra {

	public int[] parent;
	public double[] dist;

	public void run(Graph g, int src, int mode) {

		int n = g.vertices.size();
		dist = new double[n];
		parent = new int[n];

		for (int i = 0; i < n; i++) {
			dist[i] = Double.MAX_VALUE;
			parent[i] = -1;
		}

		MinHeap heap = new MinHeap(n * 10);
		dist[src] = 0;
		heap.insert(new Node(src, 0));

		while (!heap.isEmpty()) {
			Node cur = heap.extractMin();
			if (cur.cost > dist[cur.vertex])
				continue;

			MyNode<Edge> e = g.adj.get(cur.vertex).getHead();
			while (e != null) {
				double w;

				if (mode == 1) {
					w = e.data.distance;
				} else {
					w = e.data.time;
				}

				if (dist[e.data.to] > dist[cur.vertex] + w) {
					dist[e.data.to] = dist[cur.vertex] + w;
					parent[e.data.to] = cur.vertex;
					heap.insert(new Node(e.data.to, dist[e.data.to]));
				}
				e = e.next;
			}
		}
	}
}
