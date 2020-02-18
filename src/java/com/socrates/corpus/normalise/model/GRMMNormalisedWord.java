package com.socrates.corpus.normalise.model;

public class GRMMNormalisedWord {
	
	private String domain;
	private int sentenceNumber;
	private int tokenNumber;
	private String word;
	private String lema;
	private String partOfSpeach;
	private String partOfSpeachType;
	private String negation;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((lema == null) ? 0 : lema.hashCode());
		result = prime * result + ((negation == null) ? 0 : negation.hashCode());
		result = prime * result + ((partOfSpeach == null) ? 0 : partOfSpeach.hashCode());
		result = prime * result + ((partOfSpeachType == null) ? 0 : partOfSpeachType.hashCode());
		result = prime * result + sentenceNumber;
		result = prime * result + tokenNumber;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GRMMNormalisedWord other = (GRMMNormalisedWord) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (lema == null) {
			if (other.lema != null)
				return false;
		} else if (!lema.equals(other.lema))
			return false;
		if (negation == null) {
			if (other.negation != null)
				return false;
		} else if (!negation.equals(other.negation))
			return false;
		if (partOfSpeach == null) {
			if (other.partOfSpeach != null)
				return false;
		} else if (!partOfSpeach.equals(other.partOfSpeach))
			return false;
		if (partOfSpeachType == null) {
			if (other.partOfSpeachType != null)
				return false;
		} else if (!partOfSpeachType.equals(other.partOfSpeachType))
			return false;
		if (sentenceNumber != other.sentenceNumber)
			return false;
		if (tokenNumber != other.tokenNumber)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GRMMNormalisedWord [domain=" + domain + ", sentenceNumber=" + sentenceNumber + ", tokenNumber="
				+ tokenNumber + ", word=" + word + ", lema=" + lema + ", partOfSpeach=" + partOfSpeach
				+ ", partOfSpeachType=" + partOfSpeachType + ", negation=" + negation + "]";
	}

}
