package com.modoo.wrk.visual;

public class VCVO {
	private String vtype;
	private String comment;
	
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "VCVO [vtype=" + vtype + ", comment=" + comment + "]";
	}
}
