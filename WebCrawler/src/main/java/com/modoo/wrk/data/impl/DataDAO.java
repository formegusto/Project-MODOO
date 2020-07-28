package com.modoo.wrk.data.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.SearchVO;

@Repository
public class DataDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// DATA 등록
	public void insertData(DataVO vo) {
		System.out.println("[DataDAO Log] insertData() Call ");
		mybatis.insert("DataDAO.insertData", vo);
	}
	
	// DATA 리스트 조회
	public List<DataVO> getData(DataVO vo) {
		System.out.println("[DataDAO Log] getData() Call ");
		return mybatis.selectList("DataDAO.getData", vo);
	}
	
	// DATA 리스트 (키워드로) 조회
	public List<DataVO> getDataSearch(SearchVO vo){
		System.out.println("[DataDAO Log] getDataSearch() Call");
		return mybatis.selectList("DataDAO.getDataSearch", vo);
	}
	
	// DATA 랜덞 리스트 조회
	public List<String> getDataRand(DataVO vo) {
		System.out.println("[DataDAO Log] getDataRand() Call ");
		return mybatis.selectList("DataDAO.getDataRand", vo);
	}
	
	// DATA만 조회
	public List<String> getDataNotVO(DataVO vo) {
		System.out.println("[DataDAO Log] getDataNotVO() Call ");
		return mybatis.selectList("DataDAO.getDataNotVO", vo);
	}
	
	// DATA 삭제
	public void deleteData(DataVO vo) {
		System.out.println("[DataDAO Log] deleteData() Call ");
		mybatis.delete("DataDAO.deleteData", vo);
	}
	
	// DATA 수정
	public void updateData(DataVO vo) {
		System.out.println("[DataDAO Log] updateData() Call ");
		mybatis.update("DataDAO.updateData", vo);
	}
}
