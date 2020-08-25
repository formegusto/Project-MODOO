package com.modoo.wrk.tm;

public class TCVO {
	private String ttype;
	private String comment;
	
	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "TCVO [ttype=" + ttype + ", comment=" + comment + "]";
	}
}
