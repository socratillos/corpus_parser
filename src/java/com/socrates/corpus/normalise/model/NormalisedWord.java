package com.socrates.corpus.normalise.model;

public class NormalisedWord {
	
	private String domainName;
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
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domainName == null) ? 0 : domainName.hashCode());
		result = prime * result + ((lema == null) ? 0 : lema.hashCode());
		result = prime * result + negation;
		result = prime * result + ((partOfSpeach == null) ? 0 : partOfSpeach.hashCode());
		result = prime * result + ((partOfSpeachType == null) ? 0 : partOfSpeachType.hashCode());
		result = prime * result + secondNegation;
		result = prime * result + ((sentenceNumber == null) ? 0 : sentenceNumber.hashCode());
		result = prime * result + ((tokenNumber == null) ? 0 : tokenNumber.hashCode());
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
		NormalisedWord other = (NormalisedWord) obj;
		if (domainName == null) {
			if (other.domainName != null)
				return false;
		} else if (!domainName.equals(other.domainName))
			return false;
		if (lema == null) {
			if (other.lema != null)
				return false;
		} else if (!lema.equals(other.lema))
			return false;
		if (negation != other.negation)
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
		if (secondNegation != other.secondNegation)
			return false;
		if (sentenceNumber == null) {
			if (other.sentenceNumber != null)
				return false;
		} else if (!sentenceNumber.equals(other.sentenceNumber))
			return false;
		if (tokenNumber == null) {
			if (other.tokenNumber != null)
				return false;
		} else if (!tokenNumber.equals(other.tokenNumber))
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
		return "NormalisedWord [domainName=" + domainName + ", sentenceNumber=" + sentenceNumber + ", tokenNumber="
				+ tokenNumber + ", word=" + word + ", lema=" + lema + ", partOfSpeach=" + partOfSpeach
				+ ", partOfSpeachType=" + partOfSpeachType + ", negation=" + negation + ", secondNegation="
				+ secondNegation + "]";
	}

}
