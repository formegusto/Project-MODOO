package com.modoo.wrk.frame;

import java.util.Date;
import java.util.List;

public class FrameVO {
	private int fseq;
	private String title;
	private String id;
	private Date regdate;

	public int getFseq() {
		return fseq;
	}

	public void setFseq(int fseq) {
		this.fseq = fseq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return "FrameVO [fseq=" + fseq + ", title=" + title + ", id=" + id + ", regdate=" + regdate + "]";
	}
}
