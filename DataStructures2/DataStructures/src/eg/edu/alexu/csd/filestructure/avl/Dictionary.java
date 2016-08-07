package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary implements IDictionary {
	private int size;
	private AVLTree<String> tree;

	public Dictionary() {
		// TODO Auto-generated constructor stub
		tree = new AVLTree<>();
		size = 0;
	}

	@Override
	public void load(File file) {
		// TODO Auto-generated method stub
		BufferedReader read = null;
		try {
			read = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				throw new FileNotFoundException();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		String s = "a";
		while (s != null) {
			try {
				s = read.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (s != null) {
				if (tree.search(s) == false) {
					size++;
					tree.insert(s);
				}
			}
		}
		try {
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(String word) {
		// TODO Auto-generated method stub
		boolean ret = tree.search(word);
		if (ret == false) {
			size++;
			tree.insert(word);
		}
		return !ret;
	}

	@Override
	public boolean exists(String word) {
		// TODO Auto-generated method stub
		return tree.search(word);
	}

	@Override
	public boolean delete(String word) {
		// TODO Auto-generated method stub
		boolean ret = tree.delete(word);
		if (ret)
			size--;
		return ret;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return tree.height();
	}

}
