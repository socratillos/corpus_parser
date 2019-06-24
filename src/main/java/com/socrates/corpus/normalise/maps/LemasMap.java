package com.socrates.corpus.normalise.maps;

import java.util.HashMap;
import java.util.Map;

public class LemasMap {
	
	private LemasMap() {
		throw new IllegalStateException("Utility Map class");
	}
	
	private static Map<String, Long> lemasMap;
	private static Long id;
	
	static {
		lemasMap = new HashMap<>();
		id = 1L;
	}
	
	public static synchronized Long getLemaId(String lema) {
		if(lema != null) {
			if(lemasMap.containsKey(lema)) {
				return lemasMap.get(lema);
			} else {
				id ++;
				lemasMap.put(lema, id);
				return id;
			}
		}
		
		return 0L;
	}
}
