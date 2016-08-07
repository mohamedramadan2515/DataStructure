package eg.edu.alexu.csd.filestructure.hash;

public class QuadraticProbing<K,V> extends OpenAdressing<K, V> implements IHashQuadraticProbing{

	@Override
	protected int getNext(K key, int i) {
		// TODO Auto-generated method stub
		return key.hashCode() % cap + (int) (((long) i * (long) i) % cap);
	}

}
