package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ISort<Integer> sort = new Sort();
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 10; i >= 1; i--) {
			arr.add(i);
		}
		sort.sortFast(arr);
		for (Integer a : arr)
			System.out.println(a);
	}

}
