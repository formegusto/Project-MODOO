package com.crawler.biz.info;

public class FrameHaveInfoVO {
	private int fhiseq;
	private int fseq;
	private int inum;
	
	public int getFhiseq() {
		return fhiseq;
	}
	public void setFhiseq(int fhiseq) {
		this.fhiseq = fhiseq;
	}
	public int getFseq() {
		return fseq;
	}
	public void setFseq(int fseq) {
		this.fseq = fseq;
	}
	public int getInum() {
		return inum;
	}
	public void setInum(int inum) {
		this.inum = inum;
	}
	
	@Override
	public String toString() {
		return "FrameHaveInfoVO [fhiseq=" + fhiseq + ", fseq=" + fseq + ", inum=" + inum + "]";
	}
	
}
