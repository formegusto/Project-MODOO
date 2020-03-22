package com.crawler.biz.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.chat.impl.ChatDAOMybatis;
import com.crawler.biz.chat.impl.ChatService;

@Service("chatService")
public class ChatServiceImpl implements ChatService {
	@Autowired
	private ChatDAOMybatis chatDAO;

	@Override
	public void insertChat(ChatVO vo) {
		// TODO Auto-generated method stub
		chatDAO.insertChat(vo);
	}

	@Override
	public List<ChatVO> getChatList(ChatVO vo) {
		// TODO Auto-generated method stub
		return chatDAO.getChatList(vo);
	}

}
