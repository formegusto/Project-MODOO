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
		System.out.println("===> Mybatis로 insertData() 기능 처리");
		mybatis.insert("DataDAO.insertData", vo);
	}
	
	//DATA(List) 등록
	public void insertData(List<DataVO> dataList) {
		System.out.println("===> Mybatis로 insertData(List) 기능 처리");
		for(DataVO vo : dataList)
			insertData(vo);
	}
	
	public List<DataVO> getData(DataVO vo) {
		System.out.println("===> Mybatis로 getData() 기능 처리");
		return mybatis.selectList("DataDAO.getData", vo);
	}
	
	public List<DataVO> getDataList(DataVO vo) {
		System.out.println("===> Mybatis로 getDataList() 기능 처리");
		return mybatis.selectList("DataDAO.getDataList", vo);
	}
}
