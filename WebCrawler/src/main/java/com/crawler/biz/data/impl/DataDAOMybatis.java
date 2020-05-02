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
	// DATAAll 등록
	public void insertDataAll(DataVO vo) {
		System.out.println("===> Mybatis�� insertDataAll() 등록 기능 처리");
		mybatis.insert("DataDAO.insertDataAll", vo);
	}
	// DATAUpdate 
	public void updateData(DataVO vo) {
		System.out.println("===> Mybatis�� updateData() 수정 기능 처리");
		mybatis.update("DataDAO.updateData", vo);
	}
	// DATAInum 등록
	public void insertDataInum(DataVO vo) {
		System.out.println("===> Mybatis�� insertDataInum() 등록 기능 처리");
		mybatis.insert("DataDAO.insertDataInum", vo);
	}
	// DATA(List) 등록
	public void insertDataInum(List<DataVO> dataList) {
		System.out.println("===> Mybatis�� insertDataInum(List) 등록 기능 처리");
		for(DataVO vo : dataList)
			insertDataInum(vo);
	}
	// DATA 상세 조회
	public DataVO getDataDseq(DataVO vo) {
		System.out.println("===> Mybatis�� getDataDseq() 조회 기능 처리");
		return mybatis.selectOne("DataDAO.getDataDseq", vo);
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
	
	// DATA 특정 데이터 삭제
	public void deleteDataSeq(DataVO vo) {
		System.out.println("===> Mybatis로 deleteDataSeq() 삭제 기능 처리");
		mybatis.delete("DataDAO.deleteDataSeq", vo);
	}
	
	// DATA 조건 삭제(문자열 길이)
	public void deleteDataLength(Integer length, String condition) {
		System.out.println("===> Mybatis로 deleteDataLength() 삭제 기능 처리");
		if(condition.equals("lt")) {
			mybatis.delete("DataDAO.deleteDataLengthLt", length);
		} else if(condition.equals("le")) {
			mybatis.delete("DataDAO.deleteDataLengthLe", length);
		} else if(condition.equals("gt")) {
			mybatis.delete("DataDAO.deleteDataLengthGt", length);
		} else if(condition.equals("ge")) {
			mybatis.delete("DataDAO.deleteDataLengthGe", length);
		} else if(condition.equals("eq")) {
			mybatis.delete("DataDAO.deleteDataLengthEq", length);
		} else if(condition.equals("ne")) {
			mybatis.delete("DataDAO.deleteDataLengthNe", length);
		}
	}
	
	// DATA 전체 삭제
	public void deleteData(DataVO vo) {
		System.out.println("===> Mybatis로 deleteData() 삭제 기능 처리");
		mybatis.delete("DataDAO.deleteData", vo);
	}
}
