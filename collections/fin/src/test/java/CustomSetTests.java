

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import implementation.CustomHashSet;

public class CustomSetTests {
	private Set<Integer> empty = new CustomHashSet<>();
	private Set<Integer> setWithData = new CustomHashSet<>();
	
	@Before
	public void init() {
		setWithData.add(1);
		setWithData.add(2);
		setWithData.add(3);
    }
	
	@Test
    public void sizeTest() {
		assertEquals(0, empty.size());
		assertEquals(3, setWithData.size());
    }
	
	@Test
    public void isEmptyTest() {
		assertTrue(empty.isEmpty());
		assertFalse(setWithData.isEmpty());
    }
	
	@Test
    public void conteinsTest() {
		assertTrue(setWithData.contains(1));
		assertFalse(setWithData.contains(4));
    }
	
	@Test
    public void addTest() {
		empty.add(1);
		empty.add(1);
		assertTrue(empty.contains(1));
		assertEquals(1, empty.size());
    }
	
	@Test
    public void removeTest() {
		setWithData.remove(1);
		assertFalse(setWithData.contains(1));
		assertEquals(2, setWithData.size());
    }
	
	@Test
    public void containsAllTest() {
		Set<Integer> test = new CustomHashSet<>();
		test.add(1);
		test.add(2);
		assertTrue(setWithData.containsAll(test));
		test.add(55);
		assertFalse(setWithData.containsAll(test));
    }
	
	@Test
    public void addAllTest() {
		Set<Integer> test = new CustomHashSet<>();
		test.add(1);
		test.add(2);
		empty.addAll(test);
		assertTrue(empty.containsAll(test));
		assertEquals(2, empty.size());		
    }
	
	
	@Test
    public void retainAllTest() {
		Set<Integer> test = new CustomHashSet<>();
		test.add(1);
		test.add(4);
		test.add(2);
		setWithData.retainAll(test);
		assertTrue(setWithData.contains(2));
		assertFalse(setWithData.contains(3));
		assertFalse(setWithData.contains(4));
		assertEquals(2, setWithData.size());		
    }
	
	@Test
    public void removeAllTest() {
		Set<Integer> test = new CustomHashSet<>();
		test.add(1);
		test.add(2);
		setWithData.removeAll(test);
		assertFalse(setWithData.contains(2));
		assertTrue(setWithData.contains(3));
		assertEquals(1, setWithData.size());		
    }
	
	@Test
    public void clearTest() {		
		setWithData.clear();		
		assertEquals(0, setWithData.size());		
    }
	
}
