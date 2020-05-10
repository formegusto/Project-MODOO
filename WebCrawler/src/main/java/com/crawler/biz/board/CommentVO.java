package com.crawler.biz.board;

public class CommentVO {
	private int cseq;
	private int bnum;
	private String id;
	private String content;
	
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "CommentVO [cseq=" + cseq + ", bnum=" + bnum + ", id=" + id + ", content=" + content + "]";
	}
	
}
