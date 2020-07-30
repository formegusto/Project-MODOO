package com.modoo.wrk.frame;

public class FHIVO {
	private int fhiseq;
	private int fseq;
	private int iseq;
	
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
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
	}
	
	@Override
	public String toString() {
		return "FHIVO [fhiseq=" + fhiseq + ", fseq=" + fseq + ", iseq=" + iseq + "]";
	}
}
