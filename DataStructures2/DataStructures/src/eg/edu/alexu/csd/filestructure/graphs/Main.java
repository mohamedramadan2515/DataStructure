package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IGraph graph = new Graph();
		File file = new File("input.txt");
		graph.readGraph(file);
		int[] arr = new int[graph.getVertices().size()];
		graph.runBellmanFord(0, arr);
		for (int a : arr) {
			System.out.print(a + " ");
		}
	}

}
