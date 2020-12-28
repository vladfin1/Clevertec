import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import implementation.CustomHashMap;
import implementation.CustomHashSet;

public class Runner {
	private static final Logger logger = LogManager.getRootLogger();

	public static void main(String[] args) {
		CustomHashMap<Integer, Integer> map = new CustomHashMap<Integer, Integer>();
		logger.debug(map.put(1, 22));
		logger.debug(map.put(0, 223));
		logger.debug(map.put(3, 2233));
		logger.debug(map.put(366, 2233));
		logger.debug(map.put(31, 2233));
		logger.debug(map.put(321, 2233));
		logger.debug(map.put(355, 2233));
		logger.debug(map.put(3231, 2233));
		logger.debug(map.put(113, 2233));
		logger.debug(map.put(3111, 2233));
		logger.debug(map.put(3131, 2233));
		logger.debug(map.put(1553, 2233));
		logger.debug(map.put(63, 2233));
		logger.debug(map.remove(63));
		logger.debug(map.size());
		logger.debug(map.containsKey(222222222));
		for(Integer i: map.keySet()) {
			logger.debug(i);
		}
		map.display();
	}

}
