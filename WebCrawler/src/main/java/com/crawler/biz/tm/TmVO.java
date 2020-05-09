package com.crawler.biz.tm;

import java.sql.Date;

public class TmVO {
	private int tseq;
	private String title;
	private String content;
	private String ttype;
	private String id;
	private Date regdate;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "TmVO [tseq=" + tseq + ", title=" + title + ", content=" + content + ", ttype=" + ttype + ", id=" + id
				+ ", regdate=" + regdate + "]";
	}
}
