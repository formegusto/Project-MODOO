package com.modoo.wrk.comment;

public class CommentVO {
	private int cseq;
	private String content;
	private String id;
	private int bseq;
	
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
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
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	
	@Override
	public String toString() {
		return "CommentVO [cseq=" + cseq + ", content=" + content + ", id=" + id + ", bseq=" + bseq + "]";
	}
}
