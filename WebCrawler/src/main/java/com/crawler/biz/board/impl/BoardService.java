package com.crawler.biz.board.impl;

import java.util.List;

import com.crawler.biz.board.BoardHaveInfoVO;
import com.crawler.biz.board.BoardVO;

public interface BoardService {
	public void insertBoard(BoardVO vo);
	public void insertBHI(BoardHaveInfoVO vo);
	public List<BoardVO> getBoardList(BoardVO vo);
}
