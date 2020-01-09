package com.socrates.corpus.arff.output;

public enum Headers {
	RELATION("@RELATION"), ATTRIBUTE("@ATTRIBUTE"), DATA("@DATA");
	
	public final String header;
	
	private Headers(String header) {
		this.header = header;
	}

}
