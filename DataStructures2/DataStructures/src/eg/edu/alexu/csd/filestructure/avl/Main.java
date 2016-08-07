package eg.edu.alexu.csd.filestructure.avl;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		IAVLTree<Integer> tree = new AVLTree<>();
		for (int i = 0; i < 100; i++) {
			tree.insert(i);
		}
		for (int i = 0; i < 100; i++) {
			System.out.println(tree.search(i));
		}
		System.out.println("---------------------");
		for (int i = 0; i < 100; i++) {
			System.out.println(tree.delete(i));
		}
		System.out.println("---------------------");
		for (int i = 0; i < 100; i++) {
			System.out.println(tree.search(i));
		}
	}

}
