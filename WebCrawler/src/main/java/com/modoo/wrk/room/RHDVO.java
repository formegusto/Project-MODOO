package com.modoo.wrk.room;

public class RHDVO {
	private int rhdseq;
	private String type;
	private int rseq;
	private int seq;
	
	public int getRhdseq() {
		return rhdseq;
	}
	public void setRhdseq(int rhdseq) {
		this.rhdseq = rhdseq;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRseq() {
		return rseq;
	}
	public void setRseq(int rseq) {
		this.rseq = rseq;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	@Override
	public String toString() {
		return "RHDVO [rhdseq=" + rhdseq + ", type=" + type + ", rseq=" + rseq + ", seq=" + seq + "]";
	}
}
