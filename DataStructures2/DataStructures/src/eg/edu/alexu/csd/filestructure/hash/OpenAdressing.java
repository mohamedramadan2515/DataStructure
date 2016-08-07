package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class OpenAdressing<K, V> implements IHash<K, V> {
	private ArrayList<Node> arr, rehash;
	private int size;
	private int collisions;
	protected boolean[] np;
	protected int maxPrime, cap;

	public OpenAdressing() {
		// TODO Auto-generated constructor stub
		arr = new ArrayList<>();
		rehash = new ArrayList<>();
		size = 0;
		cap = 1200;
		collisions = 0;
		for (int i = 0; i < 1200; i++)
			arr.add(null);
	}

	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		int idx = key.hashCode();
		idx %= cap;
		int i = 0;
		while (i < cap && arr.get(idx) != null && arr.get(idx).deleted == false) {
			if (arr.get(idx).key.equals(key)) {
				if (i > 0)
					collisions++;
				arr.get(idx).value = value;
				rehash.get(arr.get(idx).idx).value = value;
				return;
			}
			i++;
			collisions++;
			idx = getNext(key, i);
			idx %= cap;
		}
		if (i == cap) {
			collisions++;
			full();
			idx = key.hashCode();
			idx %= cap;
			i = 0;
			while (i < cap && arr.get(idx) != null && arr.get(idx).deleted == false) {
				if (arr.get(idx).key.equals(key)) {
					if (i > 0)
						collisions++;
					arr.get(idx).value = value;
					rehash.get(arr.get(idx).idx).value = value;
					return;
				}
				i++;
				collisions++;
				idx = getNext(key, i);
				idx %= cap;
			}
		}
		if (i > 0 && maxPrime != 1193)
			collisions++;
		arr.set(idx, new Node<K, V>(key, value, false, rehash.size()));
		rehash.add(new Node<K, V>(key, value, false, 0));
		size++;
	}

	private void full() {
		ArrayList<Node> past = rehash;
		cap *= 2;
		size = 0;
		arr = new ArrayList<>();
		rehash = new ArrayList<>();
		for (int i = 0; i < cap; i++)
			arr.add(null);
		for (Node node : past) {
			if (node != null && node.deleted == false) {
				put((K) node.key, (V) node.value);
			}
		}
		/*
		 * if(np!=null){ for(int i=Math.min(cap,100000) ;i>=1;i--){ if(!np[i]){
		 * maxPrime = i; break; } } }
		 */
	}

	protected abstract int getNext(K key, int i);

	@Override
	public String get(K key) {
		// TODO Auto-generated method stub
		int idx = key.hashCode();
		idx %= cap;
		int i = 0;
		while (arr.get(idx) != null && arr.get(idx).key.equals(key) == false) {
			i++;
			collisions++;
			idx = getNext(key, i);
			idx %= cap;
		}
		if (arr.get(idx) != null && arr.get(idx).key.equals(key) && arr.get(idx).deleted == false)
			return (String) arr.get(idx).value;
		return null;
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub
		int idx = key.hashCode();
		idx %= cap;
		int i = 0;
		while (arr.get(idx) != null && arr.get(idx).key.equals(key) == false) {
			i++;
			collisions++;
			idx = getNext(key, i);
			idx %= cap;
		}
		if (arr.get(idx) != null && arr.get(idx).key.equals(key) && arr.get(idx).deleted == false) {
			arr.get(idx).deleted = true;
			size--;
			rehash.get(arr.get(idx).idx).deleted = true;
		}
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		int idx = key.hashCode();
		idx %= cap;
		int i = 0;
		while (arr.get(idx) != null && arr.get(idx).key.equals(key) == false) {
			i++;
			idx = getNext(key, i);
			idx %= cap;
			collisions++;
		}
		if (arr.get(idx) != null && arr.get(idx).key.equals(key) && arr.get(idx).deleted == false)
			return true;
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
		for (Node node : arr) {
			if (node != null && node.deleted == false)
				ret.add((K) node.key);
		}
		return ret;
	}
}
