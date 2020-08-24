package com.modoo.wrk.chat;

public class ChatVO {
	private int cseq;
	private String content;
	private String id;
	private int rseq;
	
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
	public int getRseq() {
		return rseq;
	}
	public void setRseq(int rseq) {
		this.rseq = rseq;
	}
	
	@Override
	public String toString() {
		return "ChatVO [cseq=" + cseq + ", content=" + content + ", id=" + id + ", rseq=" + rseq + "]";
	}
}
