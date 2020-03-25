package com.socrates.corpus.normalise.model;

import com.socrates.corpus.parser.model.Word;

public class GRMMNeighbour {
	
	private String lema;
	private String partOfSpeech;
	private String partOfSpeechType;
	private Integer index;
	
	public GRMMNeighbour(Word word, Integer index) {
		this.lema = word.getLema();
		this.partOfSpeech = word.getPartOfSpeach();
		this.partOfSpeechType = word.getPartOfSpeachType();
		this.index = index;
	}

	public String getLema() {
		return lema;
	}

	public String getPartOfSpeech() {
		return partOfSpeech;
	}

	public String getPartOfSpeechType() {
		return partOfSpeechType;
	}

	public Integer getIndex() {
		return index;
	}

}
