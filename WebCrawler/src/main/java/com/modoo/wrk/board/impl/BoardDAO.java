package com.modoo.wrk.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.board.BHDVO;
import com.modoo.wrk.board.BoardVO;

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
	
	public List<BoardVO> getBoardList(){
		return mybatis.selectList("BoardDAO.getBoardList");
	}
	
	public Integer getBoardTop(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.getBoardTop", vo);
	}
}
