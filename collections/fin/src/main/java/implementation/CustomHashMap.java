package implementation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CustomHashMap<T, V extends Object> {
	private Entry<T, V>[] table;
	private int capacity;
	private double loadFactor;
	private static final int DEFAULT_CAPACITY = 16;
	private static final int MAXIMUM_CAPACITY = 1 << 30;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	private static final Logger logger = LogManager.getRootLogger();

	static class Entry<T, V> {
		T key;
		V value;
		int hash;

		public Entry(T key, V value) {
			this.key = key;
			this.value = value;
			this.hash = hash(key);
		}

		public T getKey() {
			return key;
		}

		public void setHash(int hash) {
			this.hash = hash;
		}

		public int getHash() {
			return hash;
		}

		private int hash(Object key) {
			int h;
			return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		}

	}

	@SuppressWarnings("unchecked")
	public CustomHashMap() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.capacity = DEFAULT_CAPACITY;
		table = new Entry[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public CustomHashMap(int capacity) {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		if (capacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " + capacity);
		}
		if (capacity > MAXIMUM_CAPACITY) {
			this.capacity = MAXIMUM_CAPACITY;
		} else {
			this.capacity = capacity;
		}
		table = new Entry[capacity];
	}

	@SuppressWarnings("unchecked")
	public CustomHashMap(int capacity, double loadFactor) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Illegal initial load factor: " + loadFactor);
		}
		if (capacity > DEFAULT_LOAD_FACTOR) {
			this.loadFactor = DEFAULT_LOAD_FACTOR;
		} else {
			this.loadFactor = loadFactor;
		}
		if (capacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " + capacity);
		}
		if (capacity > MAXIMUM_CAPACITY) {
			this.capacity = MAXIMUM_CAPACITY;
		} else {
			this.capacity = capacity;
		}
		table = new Entry[capacity];
	}
	
	public Set<T> keySet() {
		HashSet<T> hashSet = new HashSet<>();
		int i = 0;
		while (table[i] != null) {
			hashSet.add(table[i].getKey());
			i++;
		}
		return hashSet;
		
	}
	
	public void putAll(Map<? extends T, ? extends V> map) {
		for(java.util.Map.Entry<? extends T, ? extends V> m : map.entrySet()) {
			put(m.getKey(), m.getValue());			
		}		
	}

	public boolean put(T newKey, V data) {
		if (newKey == null) {
			return false;
		}
		int i = 0;
		while (table[i] != null) {
			if (table[i].getKey() == newKey) {
				return false;
			}
			i++;
		}
		increaseCapacity(i);
		table[i] = new Entry<T, V>(newKey, data);
		sortTableByHash(table);
		return true;
	}

	public boolean remove(T newKey) {
		if (newKey == null) {			
			return false;
		}
		int i = 0;
		while (table[i] != null) {			
			if (table[i].getKey().equals(newKey)) {				
				while(table[i] != null) {
					table[i] = table[i+1];
					i++;
				}				
				return true;
			}
			i++;
		}			
        return false;
	}
	
	public boolean containsKey(Object o) {
		int i = 0;
		while (table[i] != null) {
			if (table[i].getKey().equals(o)) {	
				return true;
			}
			i++;
		}
		return false;
	}

	public void display() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				Entry<T, V> entry = table[i];
				logger.debug("{" + entry.key + "=" + entry.value + "}" + " ");
			}
		}
	}

	public void clear() {
		table = new Entry[DEFAULT_CAPACITY];
	}

	public int size() {
		int counter = 0;
		while (table[counter] != null) {
			counter++;
		}
		return counter;
	}

	private void sortTableByHash(Entry[] table) {
		boolean isSorted = false;
		Entry<T, V> buf = null;
		while (!isSorted) {
			isSorted = true;
			int k = 0;
			while (table[k + 1] != null) {
				if (table[k].getHash() > table[k + 1].getHash()) {
					isSorted = false;
					buf = table[k];
					table[k] = table[k + 1];
					table[k + 1] = buf;
				}
				k++;
			}
		}
	}

	private void increaseCapacity(int len) {
		if (len >= capacity * loadFactor) {
			logger.debug("The capacity has been increased!!!");
			capacity *= 2;
			Entry<T, V>[] newTable = Arrays.copyOf(table, capacity);
			table = newTable;
		}
	}

}
