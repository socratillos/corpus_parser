package com.socrates.corpus.redis.model;

import java.io.Serializable;

public class Lema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4503990431158906908L;
	
	private String lemaId;
	private Long lemaValue;
	
	
	public Lema(String lemaId, Long lemaValue) {
		super();
		this.lemaId = lemaId;
		this.lemaValue = lemaValue;
	}
	
	public String getLemaId() {
		return lemaId;
	}
	public void setLemaId(String lemaId) {
		this.lemaId = lemaId;
	}
	public Long getLemaValue() {
		return lemaValue;
	}
	public void setLemaValue(Long lemaValue) {
		this.lemaValue = lemaValue;
	}

	@Override
	public String toString() {
		return "Lema [lemaId=" + lemaId + ", lemaValue=" + lemaValue + "]";
	}
	
}
