package com.crawler.biz.chat.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.chat.ChatVO;

@Repository
public class ChatDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis ;

	public void insertChat(ChatVO vo) {
		System.out.println("===> Mybatis로 insertChat() 등록 기능 처리");
		mybatis.insert("ChatDAO.insertChat", vo);
	}
	
	public List<ChatVO> getChatList(ChatVO vo){
		System.out.println("===> Mybatis로 getChatList() 조회 기능 처리");
		return mybatis.selectList("ChatDAO.getChatList", vo);
	}
}
