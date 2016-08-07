package eg.edu.alexu.csd.filestructure.hash;

public class DoubleHashing<K, V> extends OpenAdressing<K, V> implements IHashDouble {
	public DoubleHashing() {
		// TODO Auto-generated constructor stub
		super();
		np = new boolean[100001];
		np[0] = true;
		np[1] = true;
		for (int i = 2; i * i <= 100000; i++) {
			if (!np[i]) {
				for (int j = i * i; j <= 100000; j += i)
					np[j] = true;
			}
		}
		for (int i = cap; i >= 1; i--) {
			if (!np[i]) {
				maxPrime = i;
				break;
			}
		}
	}

	@Override
	protected int getNext(K key, int i) {
		// TODO Auto-generated method stub
		return key.hashCode() % cap + (int) (((long) i % cap * (maxPrime - ((Integer) key % maxPrime)) % cap) % cap);
	}

}
