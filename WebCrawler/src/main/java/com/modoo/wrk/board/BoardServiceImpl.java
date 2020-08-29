package com.modoo.wrk.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.board.impl.BoardDAO;
import com.modoo.wrk.board.impl.BoardService;
import com.modoo.wrk.data.SearchVO;

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
	public BoardVO getBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BHDVO> getBHDList(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.getBHDList(vo);
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

	@Override
	public List<BoardVO> getBoardSearch(SearchVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.getBoardSearch(vo);
	}

}
