package eg.edu.alexu.csd.filestructure.hash;

public class Node<K, V> {
	K key;
	V value;
	boolean deleted;
	int idx;

	public Node(K key, V value, boolean deleted, int idx) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.value = value;
		this.deleted = deleted;
		this.idx = idx;
	}
}
