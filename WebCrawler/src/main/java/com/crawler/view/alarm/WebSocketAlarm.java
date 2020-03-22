package com.crawler.view.alarm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.crawler.biz.chat.ChatVO;
import com.crawler.biz.chat.impl.ChatService;

@ServerEndpoint(value="/alarm.do", configurator=SpringConfigurator.class)
public class WebSocketAlarm {
	private static List<Session> sessionList = new ArrayList<Session>();
	
	public WebSocketAlarm() {
		System.out.println("웹소켓알람(서버) 객체 생성");
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException{
		System.out.println(message);
		for(Session client : sessionList)
			if(!client.equals(session))
				client.getBasicRemote().sendText(message);
	}
	
	@OnOpen
	public void onOpen(Session session) throws IOException{
		sessionList.add(session);
	}
	
	@OnClose
	public void onClose(Session session) throws IOException{
		sessionList.remove(session);
	}
}
