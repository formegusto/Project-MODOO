package com.modoo.wrk.chat.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.chat.ChatVO;

@Repository
public class ChatDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertChat(ChatVO vo) {
		mybatis.insert("ChatDAO.insertChat", vo);
	}
	
	public List<ChatVO> getChatList(ChatVO vo){
		return mybatis.selectList("ChatDAO.getChatList", vo);
	}
}
