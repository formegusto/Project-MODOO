package com.modoo.wrk.comment.impl;

import java.util.List;

import com.modoo.wrk.comment.CommentVO;

public interface CommentService {
	public void insertComment(CommentVO vo);
	public List<CommentVO> getCommentList(CommentVO vo);
}
