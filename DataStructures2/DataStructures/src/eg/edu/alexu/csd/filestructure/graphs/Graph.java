package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph implements IGraph {
	private Map<Integer, List<Pair>> adj;
	private ArrayList<Integer> processedOrder;
	private int numOfNodes, numOfEdges;

	public Graph() {
		// TODO Auto-generated constructor stub
		numOfEdges = numOfNodes = 0;
	}

	@Override
	public void readGraph(File file) {
		// TODO Auto-generated method stub
		adj = null;
		try {
			Scanner scan = new Scanner(file);
			adj = new HashMap<>();
			numOfNodes = scan.nextInt();
			numOfEdges = scan.nextInt();
			for (int i = 0; i < numOfNodes; i++) {
				adj.put(i, new ArrayList<Pair>());
			}
			for (int i = 0; i < numOfEdges; i++) {
				int from, to, weight;
				from = scan.nextInt();
				to = scan.nextInt();
				weight = scan.nextInt();
				if (from < 0 || from >= numOfNodes || to < 0 || to >= numOfNodes)
					throw new IllegalArgumentException();
				adj.get(from).add(new Pair(to, weight));
			}
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numOfEdges;
	}

	@Override
	public ArrayList<Integer> getVertices() {
		// TODO Auto-generated method stub
		return new ArrayList<>(adj.keySet());
	}

	@Override
	public ArrayList<Integer> getNeighbors(int v) {
		// TODO Auto-generated method stub
		ArrayList<Integer> ret = new ArrayList<>();
		for (Pair p : adj.get(v)) {
			ret.add(p.getFirst());
		}
		return ret;
	}

	@Override
	public void runDijkstra(int src, int[] distances) {
		// TODO Auto-generated method stub
		if (src < 0 || src >= numOfNodes || distances == null || adj == null || distances.length != numOfNodes)
			throw new RuntimeException();
		for (int i = 0; i < numOfNodes; i++) {
			distances[i] = Integer.MAX_VALUE / 2;
		}
		PriorityQueue<Pair> q = new PriorityQueue<>();
		distances[src] = 0;
		q.add(new Pair(distances[src], src));
		processedOrder = new ArrayList<>();
		while (!q.isEmpty()) {
			Pair p = q.poll();
			processedOrder.add(p.getSecond());
			for (Pair node : adj.get(p.getSecond())) {
				if (distances[p.getSecond()] + node.getSecond() < distances[node.getFirst()]) {
					distances[node.getFirst()] = distances[p.getSecond()] + node.getSecond();
					q.add(new Pair(distances[node.getFirst()], node.getFirst()));
				}
			}
		}
	}

	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {
		// TODO Auto-generated method stub
		if (processedOrder == null)
			return null;
		return new ArrayList<>(processedOrder);
	}

	@Override
	public boolean runBellmanFord(int src, int[] distances) {
		// TODO Auto-generated method stub
		for (int i = 0; i < numOfNodes; i++) {
			distances[i] = Integer.MAX_VALUE / 2;
		}
		distances[src] = 0;
		for (int i = 0; i < numOfNodes - 1; i++) {
			int flag = 0;
			for (int j = 0; j < numOfNodes; j++) {
				for (Pair p : adj.get(j)) {
					if (distances[j] + p.getSecond() < distances[p.getFirst()]) {
						distances[p.getFirst()] = distances[j] + p.getSecond();
						flag = 1;
					}
				}
			}
			if (flag == 0)
				break;
		}
		for (int j = 0; j < numOfNodes; j++) {
			for (Pair p : adj.get(j)) {
				if (distances[j] + p.getSecond() < distances[p.getFirst()]) {
					return false;
				}
			}
		}
		return true;
	}

}
