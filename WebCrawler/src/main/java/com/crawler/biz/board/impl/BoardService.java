package com.crawler.biz.board.impl;

import java.util.List;

import com.crawler.biz.board.BoardVO;

public interface BoardService {
	public void insertBoard(BoardVO vo);
	public void deleteBoard(BoardVO vo);
	public BoardVO getBoard(BoardVO vo);
	public List<BoardVO> getBoardList(BoardVO vo);
	public int boardCnt(BoardVO vo);
}
