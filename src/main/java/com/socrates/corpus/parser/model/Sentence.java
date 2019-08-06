package com.socrates.corpus.parser.model;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
	
	public Sentence() {
		words = new ArrayList<>();
	}
	
	private List<Word> words;
	
	public void addWord(Word word) {
		words.add(word);
	}
	
	public List<Word> getWords() {
		return this.words;
	}
	
	public Word getWordByIndex(int index) {
		return words.get(index);
	}
	
	public boolean hasNegation() {
		for(Word word: words) {
			if(word.isNegation()) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isEmpty() {
		return words.isEmpty();
	}
	
	public boolean hastTwoNegation() {
		for(Word word: words) {
			if(word.getSecondNegation() != null) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(Word word: words) {
			builder.append(word);
			builder.append("\n");
		}
		
		return "Sentence words: \n" + builder.toString();
	}

}
