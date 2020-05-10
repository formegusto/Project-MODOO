package com.crawler.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.board.impl.CommentDAOMybatis;
import com.crawler.biz.board.impl.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAOMybatis commentDAO;
	
	@Override
	public void insertComment(CommentVO vo) {
		commentDAO.insertComment(vo);
	}

	@Override
	public List<CommentVO> getCommentList(CommentVO vo) {
		// TODO Auto-generated method stub
		return commentDAO.getCommentList(vo);
	}

}
