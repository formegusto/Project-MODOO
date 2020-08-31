package com.modoo.wrk.board.impl;

import java.util.List;
import java.util.Map;

import com.modoo.wrk.board.BHDVO;
import com.modoo.wrk.board.BoardVO;
import com.modoo.wrk.data.SearchVO;

public interface BoardService {
	public void insertBoard(BoardVO vo);
	public void insertBHD(BHDVO vo);
	public BoardVO getBoard(BoardVO vo);
	public List<BHDVO> getBHDList(BoardVO vo);
	public List<BoardVO> getBoardList();
	public Integer getBoardTop(BoardVO vo);
	public List<BoardVO> getBoardSearch(SearchVO vo);
	public void clearBHD(Map<String, Object> payload);
}
