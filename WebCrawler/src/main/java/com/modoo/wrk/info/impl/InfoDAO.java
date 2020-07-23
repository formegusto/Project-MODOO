package com.modoo.wrk.info.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.info.InfoVO;

@Repository
public class InfoDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// INFO 등록
	public void insertInfo(InfoVO vo) {
		System.out.println("[InfoDAO Log] insertInfo() Call ");
		mybatis.insert("InfoDAO.insertInfo", vo);
	}
	
	// INFOLIST 조회
	public List<InfoVO> getInfoList(InfoVO vo) {
		System.out.println("[InfoDAO Log] getInfoList() Call ");
		return mybatis.selectList("InfoDAO.getInfoList", vo);
	}
	
	// INFO 조회
	public InfoVO getInfo(InfoVO vo) {
		System.out.println("[InfoDAO Log] getInfo() Call ");
		return mybatis.selectOne("InfoDAO.getInfo", vo);
	}
}
