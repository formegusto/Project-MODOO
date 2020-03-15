package com.crawler.biz.data.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.data.DataVO;

@Repository
public class DataDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertData(DataVO vo) {
		System.out.println("===> Mybatis�� insertData() ��� ó��");
		mybatis.insert("DataDAO.insertData", vo);
	}
	
	//DATA(List) ���
	public void insertData(List<DataVO> dataList) {
		System.out.println("===> Mybatis�� insertData(List) ��� ó��");
		for(DataVO vo : dataList)
			insertData(vo);
	}
	
	public List<DataVO> getData(DataVO vo) {
		System.out.println("===> Mybatis�� getData() ��� ó��");
		return mybatis.selectList("DataDAO.getData", vo);
	}
	
	public List<DataVO> getDataList(DataVO vo) {
		System.out.println("===> Mybatis�� getDataList() ��� ó��");
		return mybatis.selectList("DataDAO.getDataList", vo);
	}
}
