package com.crawler.view.chat;

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

@ServerEndpoint(value="/chat.do")
public class WebSocketChat {
	private static Map<String, List<Session>> clientMap = new HashMap<String, List<Session>>();
	
	public WebSocketChat() {
		System.out.println("웹소켓(서버) 객체 생성");
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException{
		System.out.println(message);
		String uri = session.getRequestURI().toString();
		String roomNum = uri.substring(uri.lastIndexOf("?")+1);
		System.out.println("roomNum is " + roomNum);
		List<Session> clients = clientMap.get(roomNum);
		for(Session client : clients)
			if(!client.equals(session))
				client.getBasicRemote().sendText(message);
	}
	
	@OnOpen
	public void onOpen(Session session) {
		String uri = session.getRequestURI().toString();
		String roomNum = uri.substring(uri.lastIndexOf("?")+1);
		List<Session> clients = null;
		System.out.println("roomNum is " + roomNum);
		System.out.println(session);
		if(clientMap.get(roomNum) == null) {
			clients = new ArrayList<Session>();
			clients.add(session);
			clientMap.put(roomNum, clients);
		} else {
			clients = clientMap.get(roomNum);
			clients.add(session);
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("close");
		String uri = session.getRequestURI().toString();
		String roomNum = uri.substring(uri.lastIndexOf("?")+1);
		System.out.println("roomNum is " + roomNum);
		List<Session> clients = clientMap.get(roomNum);
		clients.remove(session);
	}
}
