package com.socrates.corpus.normalise.maps;

import java.util.HashMap;
import java.util.Map;

public class PartOfSpeechTypeMap {
	
	private PartOfSpeechTypeMap() {
		throw new IllegalStateException("Utility Map class");
	}
	
	private static Map<String,Long> partOfSpeechTypeId;
	private static Long id;

	static {
		partOfSpeechTypeId = new HashMap<>();
		id = 1L;
	}
	
	public static synchronized Long getPartOfSpeechTypeId(String partOfSpeech) {
		
		if(partOfSpeech != null) {
			if(partOfSpeechTypeId.containsKey(partOfSpeech)) {
				return partOfSpeechTypeId.get(partOfSpeech);
			} else {
				id ++;
				partOfSpeechTypeId.put(partOfSpeech, id);
				return id;
			}
		} 

		return 0L;
		
	}
	
}
