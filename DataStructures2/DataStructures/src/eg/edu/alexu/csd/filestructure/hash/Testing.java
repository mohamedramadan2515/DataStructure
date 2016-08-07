package eg.edu.alexu.csd.filestructure.hash;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class Testing {
	HashMap<Integer, String> javaMap;
	LinearProbing<Integer, String> linMap;
	QuadraticProbing<Integer, String> quadMap;
	SeparateChaining<Integer, String> chainMap;
	DoubleHashing<Integer, String> doubleMap;

	@Before
	public void init() {
		javaMap = new HashMap<>();
		linMap = new LinearProbing<>();
		quadMap = new QuadraticProbing<>();
		chainMap = new SeparateChaining<>();
		doubleMap = new DoubleHashing<>();
	}

	@Test
	public void testGet() {
		for (int i = 50000; i >= 0; i--) {
			javaMap.put(i, "aa");
			linMap.put(i, "aa");
			quadMap.put(i, "aa");
			chainMap.put(i, "aa");
			doubleMap.put(i, "aa");
		}
		assertEquals(javaMap.size(), linMap.size());
		assertEquals(javaMap.size(), quadMap.size());
		assertEquals(javaMap.size(), chainMap.size());
		assertEquals(javaMap.size(), doubleMap.size());
		for (int i = 50000; i >= 0; i--) {
			javaMap.put(i, "aa");
			linMap.put(i, "aa");
			quadMap.put(i, "aa");
			chainMap.put(i, "aa");
			doubleMap.put(i, "aa");
		}
		assertEquals(javaMap.size(), chainMap.size());
		assertEquals(javaMap.size(), linMap.size());
		assertEquals(javaMap.size(), quadMap.size());
		assertEquals(javaMap.size(), doubleMap.size());
		for (int i = 100000; i >= 0; i--) {
			javaMap.remove(i);
			linMap.delete(i);
			quadMap.delete(i);
			chainMap.delete(i);
			doubleMap.delete(i);
		}
		assertEquals(javaMap.size(), chainMap.size());
		assertEquals(javaMap.size(), linMap.size());
		assertEquals(javaMap.size(), quadMap.size());
		assertEquals(javaMap.size(), doubleMap.size());
	}
}
