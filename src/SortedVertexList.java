public class SortedVertexList {

	private MyArrayList<String> vertices = new MyArrayList<>();

	public int size() {
		return vertices.size();
	}

	public String get(int i) {
		return vertices.get(i);
	}

	public int binarySearch(String key) {
		int low = 0, high = vertices.size() - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			int cmp = vertices.get(mid).compareTo(key);

			if (cmp == 0)
				return mid;
			else if (cmp < 0)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return -1;
	}

	// insert sorted without duplicate
	public void insert(String key) {

		if (vertices.size() == 0) {
			vertices.add(key);
			return;
		}

		int low = 0, high = vertices.size() - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			int cmp = vertices.get(mid).compareTo(key);

			if (cmp == 0)
				return; // duplicate
			else if (cmp < 0)
				low = mid + 1;
			else
				high = mid - 1;
		}

		vertices.addAt(low, key);
	}
}
