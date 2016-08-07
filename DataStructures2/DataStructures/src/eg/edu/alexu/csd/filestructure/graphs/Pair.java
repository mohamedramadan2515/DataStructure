package eg.edu.alexu.csd.filestructure.graphs;

public class Pair implements Comparable {
	private int first, second;

	public Pair() {
		// TODO Auto-generated constructor stub
	}

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return Integer.valueOf(first).compareTo(Integer.valueOf(((Pair) arg0).getFirst()));
	}

}
