package com.modoo.wrk.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.chat.impl.ChatDAO;
import com.modoo.wrk.chat.impl.ChatService;

@Service("ChatService")
public class ChatServiceImpl implements ChatService {
	@Autowired
	private ChatDAO chatDAO;

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
