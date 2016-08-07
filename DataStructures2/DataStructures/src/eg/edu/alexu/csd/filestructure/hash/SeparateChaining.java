package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;
import java.util.LinkedList;

public class SeparateChaining<K, V> implements IHash<K, V>, IHashChaining {
	private ArrayList<LinkedList<Node>> chain = new ArrayList<>();
	private int cap, size, collisions;

	public SeparateChaining() {
		// TODO Auto-generated constructor stub
		cap = 1200;
		size = 0;
		collisions = 0;
		for (int i = 0; i < cap; i++)
			chain.add(new LinkedList<Node>());
	}

	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		int idx = key.hashCode();
		idx %= cap;
		for (Node node : chain.get(idx)) {
			if (node.key.equals(key)) {
				node.value = value;
				return;
			}
			collisions++;
		}
		chain.get(idx).add(new Node<K, V>(key, value, false, 0));
		size++;
		// if(size == cap)full();
	}

	private void full() {
		ArrayList<LinkedList<Node>> prev = chain;
		chain = new ArrayList<>();
		cap *= 2;
		size = 0;
		for (int i = 0; i < cap; i++) {
			chain.add(new LinkedList<Node>());
		}
		for (LinkedList<Node> list : prev) {
			for (Node node : list) {
				put((K) node.key, (V) node.value);
			}
		}
	}

	@Override
	public String get(K key) {
		// TODO Auto-generated method stub
		int idx = key.hashCode();
		idx %= cap;
		for (Node node : chain.get(idx)) {
			if (node.key.equals(key)) {
				return (String) node.value;
			}
			collisions++;
		}
		return null;
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub
		int idx = key.hashCode();
		idx %= cap;
		for (Node node : chain.get(idx)) {
			if (node.key.equals(key)) {
				chain.get(idx).remove(node);
				size--;
				break;
			}
			collisions++;
		}
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		int idx = key.hashCode();
		idx %= cap;
		for (Node node : chain.get(idx)) {
			if (node.key.equals(key)) {
				return true;
			}
			collisions++;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return cap;
	}

	@Override
	public int collisions() {
		// TODO Auto-generated method stub
		return collisions;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		LinkedList<K> ret = new LinkedList<>();
		for (LinkedList<Node> list : chain) {
			for (Node node : list) {
				ret.add((K) node.key);
			}
		}
		return ret;
	}

}
