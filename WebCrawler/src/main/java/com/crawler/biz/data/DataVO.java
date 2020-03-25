package com.crawler.biz.data;

public class DataVO {
	private int dseq;
	private int inum;
	private String data;
	
	public int getDseq() {
		return dseq;
	}
	public void setDseq(int dseq) {
		this.dseq = dseq;
	}
	public int getInum() {
		return inum;
	}
	public void setInum(int inum) {
		this.inum = inum;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "DataVO [dseq=" + dseq + ", inum=" + inum + ", data=" + data + "]";
	}
}
