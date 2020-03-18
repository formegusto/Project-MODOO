package com.crawler.biz.board;

public class BoardHaveInfoVO {
	private int bseq;
	private int inum;
	
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	public int getInum() {
		return inum;
	}
	public void setInum(int inum) {
		this.inum = inum;
	}
	
	@Override
	public String toString() {
		return "BoardHaveInfoVO [bseq=" + bseq + ", inum=" + inum + "]";
	}
}
