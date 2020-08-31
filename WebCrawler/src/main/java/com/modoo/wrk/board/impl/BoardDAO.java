package com.modoo.wrk.board.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.board.BHDVO;
import com.modoo.wrk.board.BoardVO;
import com.modoo.wrk.data.SearchVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.insertBoard", vo);
	}
	
	public void insertBHD(BHDVO vo) {
		mybatis.insert("BoardDAO.insertBHD", vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BHDVO> getBHDList(BoardVO vo){
		return mybatis.selectList("BoardDAO.getBHDList", vo);
	}
	
	public List<BoardVO> getBoardList(){
		return mybatis.selectList("BoardDAO.getBoardList");
	}
	
	public Integer getBoardTop(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.getBoardTop", vo);
	}
	
	public List<BoardVO> getBoardSearch(SearchVO vo){
		return mybatis.selectList("BoardDAO.getBoardSearch", vo);
	}
	
	public void clearBHD(Map<String, Object> payload) {
		mybatis.delete("BoardDAO.clearBHD", payload);
	}
}
