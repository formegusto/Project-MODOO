package com.modoo.wrk.tm;

public class THIVO {
	private int thiseq;
	private int tseq;
	private int iseq;
	
	public int getThiseq() {
		return thiseq;
	}
	public void setThiseq(int thiseq) {
		this.thiseq = thiseq;
	}
	public int getTseq() {
		return tseq;
	}
	public void setTseq(int tseq) {
		this.tseq = tseq;
	}
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
	}
	
	@Override
	public String toString() {
		return "THIVO [thiseq=" + thiseq + ", tseq=" + tseq + ", iseq=" + iseq + "]";
	}
}
