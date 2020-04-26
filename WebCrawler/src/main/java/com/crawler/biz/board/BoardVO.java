package com.crawler.biz.board;

import java.sql.Date;

public class BoardVO {
	private int bseq; // 보드 순번
	private int bnum; // 보드가 참조하는 데이터 seq
	private String btype;
	private String title;
	private String content;
	private String id;
	private Date regDate;
	
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bseq=" + bseq + ", bnum=" + bnum + ", btype=" + btype + ", title=" + title + ", content="
				+ content + ", id=" + id + ", regDate=" + regDate + "]";
	}
}
