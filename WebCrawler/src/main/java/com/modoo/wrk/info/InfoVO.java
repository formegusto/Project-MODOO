package com.modoo.wrk.info;

import java.sql.Date;
import java.util.List;

public class InfoVO {
	private int iseq;
	private String title;
	private String link;
	private String content;
	private String field;
	private String cssQuery;
	private String itype;
	private Date redDate;
	private String id;
	
	/* UI 용 Data */
	private List<String> dataList;
	
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
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
	public String getItype() {
		return itype;
	}
	public void setItype(String itype) {
		this.itype = itype;
	}
	public Date getRedDate() {
		return redDate;
	}
	public void setRedDate(Date redDate) {
		this.redDate = redDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/* UI 용 */
	public List<String> getDataList() {
		return dataList;
	}
	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}
	
	@Override
	public String toString() {
		return "InfoVO [iseq=" + iseq + ", title=" + title + ", link=" + link + ", content=" + content + ", field="
				+ field + ", cssQuery=" + cssQuery + ", itype=" + itype + ", redDate=" + redDate + ", id=" + id
				+ ", dataList=" + dataList + "]";
	}
}
