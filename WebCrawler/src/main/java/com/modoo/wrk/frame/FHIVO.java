package com.modoo.wrk.frame;

import java.util.List;

import com.modoo.wrk.data.DataVO;

public class FHIVO {
	private int fhiseq;
	private int fseq;
	private int iseq;
	
	/* UI용 Data */
	private String field;
	private List<DataVO> dataList;
	
	public int getFhiseq() {
		return fhiseq;
	}
	public void setFhiseq(int fhiseq) {
		this.fhiseq = fhiseq;
	}
	public int getFseq() {
		return fseq;
	}
	public void setFseq(int fseq) {
		this.fseq = fseq;
	}
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}	
	public List<DataVO> getDataList() {
		return dataList;
	}
	public void setDataList(List<DataVO> dataList) {
		this.dataList = dataList;
	}
	
	@Override
	public String toString() {
		return "FHIVO [fhiseq=" + fhiseq + ", fseq=" + fseq + ", iseq=" + iseq + ", field=" + field + ", dataList="
				+ dataList + "]";
	}
}
