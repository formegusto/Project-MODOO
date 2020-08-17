package com.modoo.wrk.board;

import java.sql.Date;

public class BoardVO {
	private int bseq;
	private String title;
	private String content;
	private String id;
	private Date regdate;
	
	// 데이터 전송용
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
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
		return "BoardVO [bseq=" + bseq + ", title=" + title + ", content=" + content + ", id=" + id + ", regdate="
				+ regdate + "]";
	}
}
