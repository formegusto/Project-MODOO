package com.crawler.biz.data.impl;

import java.util.List;

import com.crawler.biz.data.DataVO;

public interface DataService {
	//CRUD 기능의 메소드 구현
	//DATA 등록
	public void insertData(DataVO vo);
	//DATA(List) 등록
	public void insertData(List<DataVO> dataList);
	//특정 크롤러의 DATA 조회
	public List<DataVO> getData(DataVO vo);
	//전체 DATA 조회
	public List<DataVO> getDataList(DataVO vo);
}
