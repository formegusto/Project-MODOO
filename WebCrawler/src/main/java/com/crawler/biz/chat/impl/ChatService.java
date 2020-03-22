package com.crawler.biz.chat.impl;

import java.util.List;

import com.crawler.biz.chat.ChatVO;

public interface ChatService {
	public void insertChat(ChatVO vo);
	public List<ChatVO> getChatList(ChatVO vo);
}
