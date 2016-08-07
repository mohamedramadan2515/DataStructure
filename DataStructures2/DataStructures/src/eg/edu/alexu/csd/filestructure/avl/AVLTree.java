package eg.edu.alexu.csd.filestructure.avl;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {
	private class Node<T extends Comparable<T>> implements INode<T> {
		private INode<T> right;
		private INode<T> left;
		private int height = 0;
		private T value;

		@Override
		public INode<T> getLeftChild() {
			// TODO Auto-generated method stub
			return left;
		}

		@Override
		public INode<T> getRightChild() {
			// TODO Auto-generated method stub
			return right;
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

	private INode<T> root;

	@Override
	public void insert(T key) {
		// TODO Auto-generated method stub
		root = insert(root, key);
	}

	private INode<T> insert(INode<T> root, T key) {
		if (root == null) {
			root = new Node<>();
			root.setValue(key);
		} else if (key.compareTo((T) root.getValue()) > 0) {
			((Node<T>) root).right = insert(root.getRightChild(), key);
		} else if (key.compareTo((T) root.getValue()) <= 0) {
			((Node<T>) root).left = insert(root.getLeftChild(), key);
		}
		int balance = getHeight(root.getLeftChild()) - getHeight(root.getRightChild());
		if (balance > 1) {
			if (getHeight(root.getLeftChild().getLeftChild()) > getHeight(root.getLeftChild().getRightChild())) {
				root = rotateRight(root);
			} else {
				((Node<T>) root).left = rotateLeft(root.getLeftChild());
				root = rotateRight(root);
			}
		} else if (balance < -1) {
			if (getHeight(root.getRightChild().getRightChild()) > getHeight(root.getRightChild().getLeftChild())) {
				root = rotateLeft(root);
			} else {
				((Node<T>) root).right = rotateRight(root.getRightChild());
				root = rotateLeft(root);
			}
		} else {
			balance(root);
		}
		return root;
	}

	private void balance(INode<T> node) {
		((Node<T>) node).height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
	}

	private INode<T> rotateLeft(INode<T> root) {
		if (root.getRightChild() == null)
			return root;
		INode<T> newRoot = root.getRightChild();
		((Node<T>) root).right = newRoot.getLeftChild();
		((Node<T>) newRoot).left = root;
		balance(root);
		balance(newRoot);
		return newRoot;
	}

	private INode<T> rotateRight(INode<T> root) {
		if (root.getLeftChild() == null)
			return root;
		INode<T> newRoot = root.getLeftChild();
		((Node<T>) root).left = newRoot.getRightChild();
		((Node<T>) newRoot).right = root;
		balance(root);
		balance(newRoot);
		return newRoot;
	}

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		boolean ret = search(key);
		if (ret)
			root = delete(root, key);
		return ret;
	}

	private INode<T> delete(INode<T> root, T key) {
		if (key.compareTo((T) root.getValue()) > 0) {
			((Node<T>) root).right = delete(root.getRightChild(), key);
		} else if (key.compareTo((T) root.getValue()) < 0) {
			((Node<T>) root).left = delete(root.getLeftChild(), key);
		} else {
			if (root.getLeftChild() == null) {
				root = root.getRightChild();
			} else if (root.getRightChild() == null) {
				root = root.getLeftChild();
			} else {
				root.setValue(getMin(root.getRightChild()));
				((Node<T>) root).right = delete(root.getRightChild(), (T) root.getValue());
			}
		}
		if (root == null)
			return root;
		int balance = getHeight(root.getLeftChild()) - getHeight(root.getRightChild());
		if (balance > 1) {
			if (getHeight(root.getLeftChild().getLeftChild()) > getHeight(root.getLeftChild().getRightChild())) {
				root = rotateRight(root);
			} else {
				((Node<T>) root).left = rotateLeft(root.getLeftChild());
				root = rotateRight(root);
			}
		} else if (balance < -1) {
			if (getHeight(root.getRightChild().getRightChild()) > getHeight(root.getRightChild().getLeftChild())) {
				root = rotateLeft(root);
			} else {
				((Node<T>) root).right = rotateRight(root.getRightChild());
				root = rotateLeft(root);
			}
		} else {
			balance(root);
		}
		return root;
	}

	private T getMin(INode<T> root) {
		if (root.getLeftChild() == null)
			return (T) root.getValue();
		else {
			return getMin(root.getLeftChild());
		}
	}

	@Override
	public boolean search(T key) {
		// TODO Auto-generated method stub
		return search(root, key);
	}

	private boolean search(INode<T> root, T key) {
		if (root == null)
			return false;
		if (root.getValue().compareTo(key) == 0)
			return true;
		else if (root.getValue().compareTo(key) > 0) {
			return search(root.getLeftChild(), key);
		} else
			return search(root.getRightChild(), key);
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return getHeight(root);
	}

	@Override
	public INode<T> getTree() {
		// TODO Auto-generated method stub
		return root;
	}

	private int getHeight(INode<T> node) {
		if (node == null)
			return 0;
		else {
			if (node.getLeftChild() != null && node.getRightChild() != null)
				return 1 + Math.max(((Node<T>) node.getLeftChild()).height, ((Node<T>) node.getRightChild()).height);
			else if (node.getLeftChild() != null) {
				return 1 + ((Node<T>) node.getLeftChild()).height;
			} else if (node.getRightChild() != null) {
				return 1 + ((Node<T>) node.getRightChild()).height;
			} else {
				return 1;
			}
		}
	}
}
