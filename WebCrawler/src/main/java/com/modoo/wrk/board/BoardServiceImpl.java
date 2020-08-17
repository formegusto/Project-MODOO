package com.modoo.wrk.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.board.impl.BoardDAO;
import com.modoo.wrk.board.impl.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void insertBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.insertBoard(vo);
	}

	@Override
	public void insertBHD(BHDVO vo) {
		// TODO Auto-generated method stub
		boardDAO.insertBHD(vo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		// TODO Auto-generated method stub
		return boardDAO.getBoardList();
	}

	@Override
	public Integer getBoardTop(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.getBoardTop(vo);
	}

}
