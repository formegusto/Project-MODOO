package com.modoo.wrk.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.comment.impl.CommentDAO;
import com.modoo.wrk.comment.impl.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public void insertComment(CommentVO vo) {
		// TODO Auto-generated method stub
		commentDAO.insertComment(vo);
	}

	@Override
	public List<CommentVO> getCommentList(CommentVO vo) {
		// TODO Auto-generated method stub
		return commentDAO.getCommentList(vo);
	}
}
