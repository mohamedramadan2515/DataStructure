package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
	private ArrayList<INode<T>> arr;
	private int size;

	private class Node<T extends Comparable<T>> implements INode<T> {
		private int idx;
		private T value;

		public Node(int idx, T value) {
			// TODO Auto-generated constructor stub
			this.idx = idx;
			this.value = value;
		}

		@Override
		public INode<T> getLeftChild() {
			// TODO Auto-generated method stub
			if (2 * idx + 1 >= size)
				return null;
			return (INode<T>) arr.get(2 * idx + 1);
		}

		@Override
		public INode<T> getRightChild() {
			// TODO Auto-generated method stub
			if (2 * idx + 2 >= size)
				return null;
			return (INode<T>) arr.get(2 * idx + 2);
		}

		@Override
		public INode<T> getParent() {
			// TODO Auto-generated method stub
			if ((idx - 1) / 2 >= size || (idx - 1) / 2 < 0)
				return null;
			return (INode<T>) arr.get((idx - 1) / 2);
		}

		@Override
		public T getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public void setValue(T value) {
			// TODO Auto-generated method stub
			this.value = value;
		}

	}

	public Heap() {
		// TODO Auto-generated constructor stub
		arr = new ArrayList<>();
		size = 0;
	}

	@Override
	public INode<T> getRoot() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new NullPointerException();
		return arr.get(0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void heapify(INode<T> node) {
		// TODO Auto-generated method stub
		INode<T> child;
		if (node == null)
			return;
		if (node.getLeftChild() == null)
			return;
		child = node.getLeftChild();
		if (node.getRightChild() != null) {
			if (child.getValue().compareTo(node.getRightChild().getValue()) < 0) {
				child = node.getRightChild();
			}
		}
		if (node.getValue().compareTo((T) child.getValue()) < 0) {
			T temp = node.getValue();
			node.setValue((T) child.getValue());
			child.setValue(temp);
			heapify(child);
		}
	}

	@Override
	public T extract() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new NullPointerException();
		T root = (T) arr.get(0).getValue();
		arr.get(0).setValue(arr.get(size - 1).getValue());
		arr.get(size - 1).setValue(root);
		size--;
		heapify(arr.get(0));
		return root;
	}

	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub
		INode<T> node = new Node(size, element);
		if (size == arr.size()) {
			arr.add(node);
		} else
			arr.set(size, node);
		while (node.getParent() != null) {
			if (node.getValue().compareTo(node.getParent().getValue()) > 0) {
				T temp = node.getValue();
				node.setValue(node.getParent().getValue());
				node.getParent().setValue(temp);
				node = node.getParent();
			} else
				break;
		}
		size++;
	}

	@Override
	public void build(Collection<T> unordered) {
		// TODO Auto-generated method stub
		arr = new ArrayList<>();
		size = 0;
		for (T col : unordered) {
			arr.add(new Node(size++, col));
		}
		if (size == 0)
			return;
		int idx = size / 2;
		for (int i = idx; i >= 0; i--) {
			heapify(arr.get(i));
		}
	}

	public void setSize(int size) {
		this.size = size;
	}
}
