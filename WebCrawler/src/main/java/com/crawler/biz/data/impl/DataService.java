package com.crawler.biz.data.impl;

import java.util.List;

import com.crawler.biz.data.DataVO;

public interface DataService {
	//CRUD ����� �޼ҵ� ����
	//DATA ���
	public void insertData(DataVO vo);
	//DATA(List) ���
	public void insertData(List<DataVO> dataList);
	// DATAInum 등록
	public void insertDataInum(DataVO vo);
	// DATA(List) 등록
	public void insertDataInum(List<DataVO> dataList);
	//Ư�� ũ�ѷ��� DATA ��ȸ
	public List<DataVO> getData(DataVO vo);
	// DATA 상세 조회 return List<String>
	public List<String> getDataStr(DataVO vo);
	//��ü DATA ��ȸ
	public List<DataVO> getDataList(DataVO vo);
	// DATA 전체 삭제
	public void deleteData(DataVO vo);
}
