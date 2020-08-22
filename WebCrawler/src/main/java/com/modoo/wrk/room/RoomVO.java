package com.modoo.wrk.room;

import java.sql.Date;

public class RoomVO {
	private int rseq;
	private String title;
	private String content;
	private String id;
	private Date regdate;
	
	public int getRseq() {
		return rseq;
	}
	public void setRseq(int rseq) {
		this.rseq = rseq;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "RoomVO [rseq=" + rseq + ", title=" + title + ", content=" + content + ", id=" + id + ", regdate="
				+ regdate + "]";
	}
}
