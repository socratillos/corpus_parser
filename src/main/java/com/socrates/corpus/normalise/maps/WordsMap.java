package com.socrates.corpus.normalise.maps;

import java.util.HashMap;
import java.util.Map;

public class WordsMap {
	
	private WordsMap() {
		throw new IllegalStateException("Utility Map class");
	}
	
	private static Map<String, Long> wordsMap;
	private static Long id;
	
	static {
		wordsMap = new HashMap<>();
		id = 1L;
	}
	
	public static synchronized Long getWordId(String word) {
		if(word != null) {
			if(wordsMap.containsKey(word)) {
				return wordsMap.get(word);
			} else {
				id ++;
				wordsMap.put(word, id);
				return id;
			}
		}
		
		return -1L;
	}

}
