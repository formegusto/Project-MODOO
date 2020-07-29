package com.modoo.wrk.visual;

import java.util.Date;
import java.util.List;

public class VisualVO {
	private int vseq;
	private String title;
	private String id;
	private String vtype;
	private String color;
	private Date regDate;
	
	/* 보내기용 */
	private List<String> datas;
	private List<String> labels;
	
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public List<String> getDatas() {
		return datas;
	}
	public void setDatas(List<String> datas) {
		this.datas = datas;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	@Override
	public String toString() {
		return "VisualVO [vseq=" + vseq + ", title=" + title + ", id=" + id + ", vtype=" + vtype + ", color=" + color
				+ ", regDate=" + regDate + ", datas=" + datas + ", labels=" + labels + "]";
	}
}
