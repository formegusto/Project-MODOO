package com.modoo.wrk.data;

public class DataVO {
	private int dseq;
	private int iseq;
	private String data;
	
	public int getDseq() {
		return dseq;
	}
	public void setDseq(int dseq) {
		this.dseq = dseq;
	}
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "DataVO [dseq=" + dseq + ", iseq=" + iseq + ", data=" + data + "]";
	}
}
