package com.modoo.wrk.data;

public class SearchVO {
	private int iseq;
	private String id;
	private String keyword;
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "SearchVO [iseq=" + iseq + ", id=" + id + ", keyword=" + keyword + "]";
	}
}
