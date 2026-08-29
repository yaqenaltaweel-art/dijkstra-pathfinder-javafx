public class MyLinkedList<T> {

	private MyNode<T> head;

	public void add(T data) {
		MyNode<T> node = new MyNode<>(data);

		if (head == null) {
			head = node;
		} else {
			MyNode<T> cur = head;
			while (cur.next != null)
				cur = cur.next;
			cur.next = node;
		}
	}

	public MyNode<T> getHead() {
		return head;
	}
}
