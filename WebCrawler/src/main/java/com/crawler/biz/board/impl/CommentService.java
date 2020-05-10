package com.crawler.biz.board.impl;

import java.util.List;

import com.crawler.biz.board.CommentVO;

public interface CommentService {
	public void insertComment(CommentVO vo);
	public List<CommentVO> getCommentList(CommentVO vo);
}
