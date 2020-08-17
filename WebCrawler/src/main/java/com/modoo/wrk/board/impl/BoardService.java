package com.modoo.wrk.board.impl;

import java.util.List;

import com.modoo.wrk.board.BHDVO;
import com.modoo.wrk.board.BoardVO;

public interface BoardService {
	public void insertBoard(BoardVO vo);
	public void insertBHD(BHDVO vo);
	public List<BoardVO> getBoardList();
	public Integer getBoardTop(BoardVO vo);
}
