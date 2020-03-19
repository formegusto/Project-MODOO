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
	
	// DATA 등록
	public void insertData(DataVO vo) {
		System.out.println("===> Mybatis�� insertData() 등록 기능 처리");
		mybatis.insert("DataDAO.insertData", vo);
	}
	
	// DATA(List) 등록
	public void insertData(List<DataVO> dataList) {
		System.out.println("===> Mybatis�� insertData(List) 등록 기능 처리");
		for(DataVO vo : dataList)
			insertData(vo);
	}
	
	// DATA 상세 조회
	public List<DataVO> getData(DataVO vo) {
		System.out.println("===> Mybatis�� getData() 조회 기능 처리");
		return mybatis.selectList("DataDAO.getData", vo);
	}
	
	// DATA 상세 조회 return List<String>
	public List<String> getDataStr(DataVO vo) {
			System.out.println("===> Mybatis�� String getData() 조회 기능 처리");
			return mybatis.selectList("DataDAO.getDataStr", vo);
	}
	
	// DATA 전체 조회
	public List<DataVO> getDataList(DataVO vo) {
		System.out.println("===> Mybatis�� getDataList() 조회 기능 처리");
		return mybatis.selectList("DataDAO.getDataList", vo);
	}
	
	// DATA 전체 삭제
	public void deleteData(DataVO vo) {
		System.out.println("===> Mybatis로 deleteData() 삭제 기능 처리");
		mybatis.delete("DataDAO.deleteData", vo);
	}
}
