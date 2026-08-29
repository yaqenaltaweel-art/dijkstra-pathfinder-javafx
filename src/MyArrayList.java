public class MyArrayList<T> {

	private Object[] data;
	private int size;

	public MyArrayList() {
		data = new Object[10];
		size = 0;
	}

	public void add(T value) {
		if (size == data.length)
			resize();

		data[size++] = value;
	}

	private void resize() {
		Object[] newData = new Object[data.length * 2];
		for (int i = 0; i < data.length; i++)
			newData[i] = data[i];
		data = newData;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T) data[index];
	}

	public int size() {
		return size;
	}

	public void addAt(int index, T value) {
		if (size == data.length)
			resize();

		for (int i = size; i > index; i--)
			data[i] = data[i - 1];

		data[index] = value;
		size++;
	}

	public void set(int index, T value) {
		data[index] = value;
	}

}
