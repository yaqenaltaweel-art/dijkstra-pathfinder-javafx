public class Graph {

	public SortedVertexList vertices;
	public MyArrayList<MyLinkedList<Edge>> adj;

	public Graph(int n) {
		vertices = new SortedVertexList();
		adj = new MyArrayList<>();

		for (int i = 0; i < n; i++)
			adj.add(new MyLinkedList<>());
	}
}
