package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Sort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public IHeap<T> heapSort(ArrayList<T> unordered) {
		// TODO Auto-generated method stub
		Heap<T> heap = new Heap();
		heap.build(unordered);
		for (int i = 0; i < unordered.size() - 1; i++)
			heap.extract();
		heap.setSize(unordered.size());
		return heap;
	}

	@Override
	public void sortSlow(ArrayList<T> unordered) {
		// TODO Auto-generated method stub
		for (int i = 1; i < unordered.size(); i++) {
			T x = unordered.get(i);
			int j = i - 1;
			while (j >= 0 && x.compareTo(unordered.get(j)) < 0) {
				unordered.set(j + 1, unordered.get(j));
				j--;
			}
			unordered.set(j + 1, x);
		}
	}

	@Override
	public void sortFast(ArrayList<T> unordered) {
		// TODO Auto-generated method stub
		mergesort(0, unordered.size() - 1, unordered);
	}

	private void mergesort(int f, int l, ArrayList<T> arr) {
		if (l <= f)
			return;
		mergesort(f, (f + l) / 2, arr);
		mergesort(((f + l) / 2) + 1, l, arr);
		merge(f, (f + l) / 2, ((f + l) / 2) + 1, l, arr);
	}

	private void merge(int f, int l, int ff, int ll, ArrayList<T> arr) {
		ArrayList<T> sorted = new ArrayList<>();
		int idx = f;
		while (f <= l && ff <= ll) {
			if (arr.get(f).compareTo(arr.get(ff)) < 0) {
				sorted.add(arr.get(f));
				f++;
			} else {
				sorted.add(arr.get(ff));
				ff++;
			}
		}
		if (f <= l) {
			for (int i = f; i <= l; i++)
				sorted.add(arr.get(i));
		} else
			for (int i = ff; i <= ll; i++)
				sorted.add(arr.get(i));
		for (T a : sorted) {
			arr.set(idx++, a);
		}
	}
}
