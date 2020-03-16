package com.crawler.biz.info;

import java.sql.Date;

public class InfoVO {
	int seq;
	String title;
	String link;
	String content;
	String field;
	String cssQuery;
	Date regDate;
	String id;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getCssQuery() {
		return cssQuery;
	}
	public void setCssQuery(String cssQuery) {
		this.cssQuery = cssQuery;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "InfoVO [seq=" + seq + ", title=" + title + ", link=" + link + ", content=" + content + ", field="
				+ field + ", cssQuery=" + cssQuery + ", regDate=" + regDate + ", id=" + id + "]";
	}
}
