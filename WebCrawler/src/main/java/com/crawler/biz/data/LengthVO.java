package com.crawler.biz.data;

public class LengthVO {
	private int pdseq;
	private int length;
	
	public int getPdseq() {
		return pdseq;
	}
	public void setPdseq(int pdseq) {
		this.pdseq = pdseq;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public String toString() {
		return "LengthVO [pdseq=" + pdseq + ", length=" + length + "]";
	}
	
}
