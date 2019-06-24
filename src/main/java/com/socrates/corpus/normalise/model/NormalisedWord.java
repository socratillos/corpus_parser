package com.socrates.corpus.normalise.model;

public class NormalisedWord {
	
	private Long sentenceNumber;
	private Long tokenNumber;
	private Long word;
	private Long lema;
	private Long partOfSpeach;
	private Long partOfSpeachType;
	private int negation;
	private int secondNegation;
	
	public Long getSentenceNumber() {
		return sentenceNumber;
	}
	public void setSentenceNumber(Long sentenceNumber) {
		this.sentenceNumber = sentenceNumber;
	}
	public Long getTokenNumber() {
		return tokenNumber;
	}
	public void setTokenNumber(Long tokenNumber) {
		this.tokenNumber = tokenNumber;
	}
	public Long getWord() {
		return word;
	}
	public void setWord(Long word) {
		this.word = word;
	}
	public Long getLema() {
		return lema;
	}
	public void setLema(Long lema) {
		this.lema = lema;
	}
	public Long getPartOfSpeach() {
		return partOfSpeach;
	}
	public void setPartOfSpeach(Long partOfSpeach) {
		this.partOfSpeach = partOfSpeach;
	}
	public Long getPartOfSpeachType() {
		return partOfSpeachType;
	}
	public void setPartOfSpeachType(Long partOfSpeachType) {
		this.partOfSpeachType = partOfSpeachType;
	}
	public int getNegation() {
		return negation;
	}
	public void setNegation(int negation) {
		this.negation = negation;
	}
	public int getSecondNegation() {
		return secondNegation;
	}
	public void setSecondNegation(int secondNegation) {
		this.secondNegation = secondNegation;
	}

}
