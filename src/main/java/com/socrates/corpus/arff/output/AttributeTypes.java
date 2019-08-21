package com.socrates.corpus.arff.output;

public enum AttributeTypes {
	NUMERIC("numeric"),STRING("string"), CLASS("class");
	
	public final String value;
	
	private AttributeTypes(String value) {
		this.value = value;
	}

}
