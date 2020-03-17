package com.crawler.biz.data.impl;

import java.util.List;

import com.crawler.biz.data.DataVO;

public interface DataService {
	//CRUD ����� �޼ҵ� ����
	//DATA ���
	public void insertData(DataVO vo);
	//DATA(List) ���
	public void insertData(List<DataVO> dataList);
	//Ư�� ũ�ѷ��� DATA ��ȸ
	public List<DataVO> getData(DataVO vo);
	//��ü DATA ��ȸ
	public List<DataVO> getDataList(DataVO vo);
	// DATA 전체 삭제
	public void deleteData(DataVO vo);
}
