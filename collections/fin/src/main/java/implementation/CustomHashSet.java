package implementation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CustomHashSet<T> implements Set<T>{
	private CustomHashMap<T, Object> map = null;
	private static final Object PRESENT = new Object();
	
	public CustomHashSet() {
		map = new CustomHashMap<T, Object>();
	}
	
	public CustomHashSet(int capacity) {
		map = new CustomHashMap<T, Object>(capacity);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		if(map.size()==0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {		
		return map.containsKey(o);
	}

	@Override
	public Iterator iterator() {
		return map.keySet().iterator();
	}

	@Override
	public Object[] toArray() {
		return map.keySet().toArray();
	}

	@Override
	public T[] toArray(Object[] a) {		
		return (T[]) map.keySet().toArray(a);
	}

	@Override
	public boolean add(T t) {
		return map.put(t, PRESENT);
	}

	@Override
	public boolean remove(Object o) {
		return map.remove((T)o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		boolean modified = false;
	    for (Object t : c) {
	    	if (contains(t)) {
	    		modified = true;
	    	}else {
	    		return false;
	    	}
	    }
	    return modified;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean modified = false;
	    for (T t : c) {
	    	if (add(t)) {
	    		modified = true;
	    	} 
	    }
	    return modified;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean modified = false;
		HashMap<T, Object> m = new HashMap<>();
		for(T t: map.keySet()) {
			for (Object o : c) {
		    	if (t.equals(o)) {
		    		m.put((T)o, PRESENT);		    		
		    		modified = true;
		    	} 
		    }
		}
		map.clear();
		map.putAll(m);
	    return modified;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
	    for (Object t : c) {
	    	if (remove(t)) {
	    		modified = true;
	    	} 
	    }
	    return modified;
	}

	@Override
	public void clear() {
		map.clear();		
	}

	@Override
	public String toString() {
		return map.keySet().toString();
	}
	
}
