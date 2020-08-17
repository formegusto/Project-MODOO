package com.modoo.wrk.board;

public class BHDVO {
	private int bhdseq;
	private String type;
	private int bseq;
	private int seq;
	
	public int getBhdseq() {
		return bhdseq;
	}
	public void setBhdseq(int bhdseq) {
		this.bhdseq = bhdseq;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	@Override
	public String toString() {
		return "BHDVO [bhdseq=" + bhdseq + ", type=" + type + ", bseq=" + bseq + ", seq=" + seq + "]";
	}
}
