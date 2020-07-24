package com.modoo.wrk.tm;

import java.sql.Date;

public class TmVO {
	private int tseq;
	private String title;
	private String ttpye;
	private String id;
	private Date regDate;
	
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
	public String getTtpye() {
		return ttpye;
	}
	public void setTtpye(String ttpye) {
		this.ttpye = ttpye;
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
	
	@Override
	public String toString() {
		return "TmVO [tseq=" + tseq + ", title=" + title + ", ttpye=" + ttpye + ", id=" + id + ", regDate=" + regDate
				+ "]";
	}
}
