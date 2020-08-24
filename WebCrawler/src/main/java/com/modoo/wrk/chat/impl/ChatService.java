package com.modoo.wrk.chat.impl;

import java.util.List;

import com.modoo.wrk.chat.ChatVO;

public interface ChatService {
	public void insertChat(ChatVO vo);
	public List<ChatVO> getChatList(ChatVO vo);
}
