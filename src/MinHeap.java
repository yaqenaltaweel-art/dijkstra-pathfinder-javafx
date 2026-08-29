public class MinHeap {

	private Node[] heap;
	private int size;

	public MinHeap(int capacity) {
		heap = new Node[capacity];
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(Node n) {
		heap[size] = n;
		heapifyUp(size);
		size++;
	}

	public Node extractMin() {
		Node min = heap[0];
		heap[0] = heap[--size];
		heapifyDown(0);
		return min;
	}

	private void heapifyUp(int i) {
		while (i > 0) {
			int p = (i - 1) / 2;
			if (heap[i].cost >= heap[p].cost)
				break;
			swap(i, p);
			i = p;
		}
	}

	private void heapifyDown(int i) {
		while (true) {
			int l = 2 * i + 1;
			int r = 2 * i + 2;
			int smallest = i;

			if (l < size && heap[l].cost < heap[smallest].cost)
				smallest = l;
			if (r < size && heap[r].cost < heap[smallest].cost)
				smallest = r;

			if (smallest == i)
				break;
			swap(i, smallest);
			i = smallest;
		}
	}

	private void swap(int i, int j) {
		Node tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}
}
