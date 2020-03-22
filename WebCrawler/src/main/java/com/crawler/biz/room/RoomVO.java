package com.crawler.biz.room;

import java.sql.Date;

public class RoomVO {
	private int rnum;
	private int bnum;
	private String rtitle;
	private String id;
	private Date regDate;
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getRtitle() {
		return rtitle;
	}
	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
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
		return "RoomVO [rnum=" + rnum + ", bnum=" + bnum + ", rtitle=" + rtitle + ", id=" + id + ", regDate=" + regDate
				+ "]";
	}
}
