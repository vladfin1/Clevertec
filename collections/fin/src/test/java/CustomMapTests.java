

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import implementation.CustomHashMap;

public class CustomMapTests {
	private CustomHashMap<Integer, Integer> emptyMap = new CustomHashMap<Integer, Integer>();
	private CustomHashMap<Integer, Integer> mapWithData = new CustomHashMap<Integer, Integer>();
	
	@Before
	public void init() {
        mapWithData.put(1, 1);
        mapWithData.put(2, 2);
        mapWithData.put(3, 3);
    }
	
	@Test
    public void keySetTest() {
		Set<Integer> s = mapWithData.keySet();
		assertEquals(3, s.size());		
		assertTrue(s.contains(3));
		assertFalse(s.contains(4));
    }
	
	@Test
    public void putAllTest() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(4, 4);
		map.put(3, 3);
		map.put(5, 5);
		map.put(null, null);
		mapWithData.putAll(map);
		assertEquals(5, mapWithData.size());
    }
	
	@Test
    public void putTest() {
		assertTrue(emptyMap.put(1, 1));		
		assertFalse(emptyMap.put(1, 1));	
		assertEquals(1, emptyMap.size());
    }
	
	@Test
    public void removeTest() {
		assertTrue(mapWithData.remove(1));
		assertFalse(mapWithData.remove(222));
		assertEquals(2, mapWithData.size());
    }
	
	@Test
    public void containsKeyTest() {
		assertTrue(mapWithData.containsKey(1));
		assertFalse(mapWithData.containsKey(222));		
    }
	
	@Test
    public void clearTest() {
		assertEquals(3, mapWithData.size());
		mapWithData.clear();
		assertEquals(0, mapWithData.size());	
    }
	
	@Test
    public void sizeTest() {
		assertEquals(0, emptyMap.size());
		emptyMap.put(1, 1);
		assertEquals(1, emptyMap.size());
    }
	
}
