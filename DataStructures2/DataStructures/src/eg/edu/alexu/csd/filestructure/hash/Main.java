package eg.edu.alexu.csd.filestructure.hash;

public class Main {
	public static void main(String[] args) {
		LinearProbing<Integer, String> tab = new LinearProbing<>();
		tab.put(5, "hamada");
		tab.put(5, "ali");
		System.out.println(tab.get(5));
	}
}
