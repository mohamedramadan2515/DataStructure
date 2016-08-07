package eg.edu.alexu.csd.filestructure.hash;

public class LinearProbing<K,V> extends OpenAdressing<K, V> implements IHashLinearProbing{

	@Override
	protected int getNext(K key, int i) {
		// TODO Auto-generated method stub
		return key.hashCode()%cap + i;
	}
	

}
