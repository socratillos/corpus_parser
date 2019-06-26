package com.socrates.corpus.parser.model;

public class Word {
	
	private static final String NEGATION_STRING = "***";
	
	private String domain;
	private int sentenceNumber;
	private int tokenNumber;
	private String word;
	private String lema;
	private String partOfSpeach;
	private String partOfSpeachType;
	private String negation;
	private String secondNegation;
	
	public boolean isNegation() {
		return negation != null && !negation.contains(NEGATION_STRING);
	}
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getSentenceNumber() {
		return sentenceNumber;
	}
	public void setSentenceNumber(int sentenceNumber) {
		this.sentenceNumber = sentenceNumber;
	}
	public int getTokenNumber() {
		return tokenNumber;
	}
	public void setTokenNumber(int tokenNumber) {
		this.tokenNumber = tokenNumber;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getLema() {
		return lema;
	}
	public void setLema(String lema) {
		this.lema = lema;
	}
	public String getPartOfSpeach() {
		return partOfSpeach;
	}
	public void setPartOfSpeach(String partOfSpeach) {
		this.partOfSpeach = partOfSpeach;
	}
	public String getPartOfSpeachType() {
		return partOfSpeachType;
	}
	public void setPartOfSpeachType(String partOfSpeachType) {
		this.partOfSpeachType = partOfSpeachType;
	}
	public String getNegation() {
		return negation;
	}
	public void setNegation(String negation) {
		this.negation = negation;
	}
	public String getSecondNegation() {
		return secondNegation;
	}
	public void setSecondNegation(String secondNegation) {
		this.secondNegation = secondNegation;
	}
	@Override
	public String toString() {
		return "Word [domain=" + domain + ", sentenceNumber=" + sentenceNumber + ", tokenNumber=" + tokenNumber
				+ ", word=" + word + ", lema=" + lema + ", partOfSpeach=" + partOfSpeach + ", partOfSpeachType="
				+ partOfSpeachType + ", negation=" + negation + ", secondNegation=" + secondNegation + "]";
	}
	
}
