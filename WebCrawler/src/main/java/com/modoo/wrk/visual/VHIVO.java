package com.modoo.wrk.visual;

public class VHIVO {
	private int vhiseq;
	private int vseq;
	private int iseq;
	private String dtype;
	
	public int getVhiseq() {
		return vhiseq;
	}
	public void setVhiseq(int vhiseq) {
		this.vhiseq = vhiseq;
	}
	public int getVseq() {
		return vseq;
	}
	public void setVseq(int vseq) {
		this.vseq = vseq;
	}
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	
	@Override
	public String toString() {
		return "VHIVO [vhiseq=" + vhiseq + ", vseq=" + vseq + ", iseq=" + iseq + ", dtype=" + dtype + "]";
	}
}
