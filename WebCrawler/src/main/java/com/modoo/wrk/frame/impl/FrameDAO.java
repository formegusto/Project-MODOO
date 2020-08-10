package com.modoo.wrk.frame.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.frame.FHIVO;
import com.modoo.wrk.frame.FrameVO;

@Repository
public class FrameDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertFrame(FrameVO vo) {
		System.out.println("[FrameDAO] insertFrame() Call!");
		mybatis.insert("FrameDAO.insertFrame", vo);
	}
	public void insertFHI(FHIVO vo) {
		System.out.println("[FrameDAO]insertFHI() Call!");
		mybatis.insert("FrameDAO.insertFHI", vo);
	}
	public void deleteFrame(FrameVO vo) {
		System.out.println("[FrameDAO] deleteFrame() Call!");
		mybatis.delete("FrameDAO.deleteFrame", vo);
	}
	public List<FHIVO> getFHIList(FrameVO vo){
		System.out.println("[FrameDAO] getFHIList() Call!");
		return mybatis.selectList("FrameDAO.getFHIList", vo);
	}
	public FrameVO getFrame(FrameVO vo) {
		System.out.println("[FrameDAO] getFrame() Call!");
		return mybatis.selectOne("FrameDAO.getFrame", vo);
	}
	public List<FrameVO> getFrameList(FrameVO vo){
		System.out.println("[FrameDAO] getBoardList() Call!");
		return mybatis.selectList("FrameDAO.getFrameList", vo);
	}
	public List<FrameVO> getFrameListSearch(SearchVO search){
		System.out.println("===> Mybatis로 getFrameList(" + search.getKeyword() + ") 조회 기능 처리");
		return mybatis.selectList("FrameDAO.getFrameListSearch", search);
	}
}
