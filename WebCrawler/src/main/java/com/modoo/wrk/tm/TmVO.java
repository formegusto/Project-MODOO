package com.modoo.wrk.tm;

import java.sql.Date;

public class TmVO {
	private int tseq;
	private String title;
	private String ttype;
	private String id;
	private Date regDate;
	
	// ttype 이 Visual 인 경우 보내줄 데이터
	private TVIVO tvi;
	
	public int getTseq() {
		return tseq;
	}
	public void setTseq(int tseq) {
		this.tseq = tseq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public TVIVO getTvi() {
		return tvi;
	}
	public void setTvi(TVIVO tvi) {
		this.tvi = tvi;
	}
	
	@Override
	public String toString() {
		return "TmVO [tseq=" + tseq + ", title=" + title + ", ttype=" + ttype + ", id=" + id + ", regDate=" + regDate
				+ ", tvi=" + tvi + "]";
	}
}
