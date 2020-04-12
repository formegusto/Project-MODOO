package com.crawler.biz.visual;

import java.sql.Date;

public class VisualVO {
	private int vseq;
	private String title;
	private String content;
	private String id;
	private String vtype;
	private Date regdate;
	
	public int getVseq() {
		return vseq;
	}
	public void setVseq(int vseq) {
		this.vseq = vseq;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "VisualVO [vseq=" + vseq + ", title=" + title + ", content=" + content + ", id=" + id + ", vtype="
				+ vtype + ", regdate=" + regdate + "]";
	}
	
}
