package com.socrates.corpus.normalise.maps;

import java.util.HashMap;
import java.util.Map;

public class PartOfSpeechMap {
	
	private PartOfSpeechMap() {
		throw new IllegalStateException("Utility Map class");
	}
	
	private static Map<String,Long> partOfSpeechId;
	private static Long id;
	
	static {
		partOfSpeechId = new HashMap<>();
		id = 1L;
	}
	
	public static synchronized Long getPartOfSpeechId(String partOfSpeech) {
		
		if(partOfSpeech != null) {
			if(partOfSpeechId.containsKey(partOfSpeech)) {
				return partOfSpeechId.get(partOfSpeech);
			} else {
				id ++;
				partOfSpeechId.put(partOfSpeech, id);
				return id;
			}
		} 
		
		return 0L;
		
	}

}
