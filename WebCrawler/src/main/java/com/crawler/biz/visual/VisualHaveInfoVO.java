package com.crawler.biz.visual;

public class VisualHaveInfoVO {
	private int vhiseq;
	private int vnum;
	private int inum;
	private String dtype;
	
	public int getVhiseq() {
		return vhiseq;
	}
	public void setVhiseq(int vhiseq) {
		this.vhiseq = vhiseq;
	}
	public int getVnum() {
		return vnum;
	}
	public void setVnum(int vnum) {
		this.vnum = vnum;
	}
	public int getInum() {
		return inum;
	}
	public void setInum(int inum) {
		this.inum = inum;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	
	@Override
	public String toString() {
		return "VisualHaveInfoVO [vhiseq=" + vhiseq + ", vnum=" + vnum + ", inum=" + inum + ", dtype=" + dtype + "]";
	}
}
