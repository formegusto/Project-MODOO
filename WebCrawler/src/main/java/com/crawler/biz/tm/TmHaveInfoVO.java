package com.crawler.biz.tm;

public class TmHaveInfoVO {
	private int thiseq;
	private int tnum;
	private int inum;
	
	public int getThiseq() {
		return thiseq;
	}
	public void setThiseq(int thiseq) {
		this.thiseq = thiseq;
	}
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	public int getInum() {
		return inum;
	}
	public void setInum(int inum) {
		this.inum = inum;
	}
	
	@Override
	public String toString() {
		return "TmHaveInfoVO [thiseq=" + thiseq + ", tnum=" + tnum + ", inum=" + inum + "]";
	}
}
