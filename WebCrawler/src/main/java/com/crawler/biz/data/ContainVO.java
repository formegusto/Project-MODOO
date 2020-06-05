package com.crawler.biz.data;

public class ContainVO {
	private int pdseq;
	private String containWord;
	
	public int getPdseq() {
		return pdseq;
	}
	public void setPdseq(int pdseq) {
		this.pdseq = pdseq;
	}
	public String getContainWord() {
		return containWord;
	}
	public void setContainWord(String containWord) {
		this.containWord = containWord;
	}
	
	@Override
	public String toString() {
		return "ContainVO [pdseq=" + pdseq + ", containWord=" + containWord + "]";
	}
}
