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
		String roomNum = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		System.out.println("roomNum is " + roomNum);
		List<Session> clients = clientMap.get(roomNum);
		for(Session client : clients)
			if(!client.equals(session))
				client.getBasicRemote().sendText(message);
	}
	
	@OnOpen
	public void onOpen(Session session) throws IOException{
		String uri = session.getRequestURI().toString();
		String roomNum = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		String chatId = uri.substring(uri.lastIndexOf("&")+1);
		List<Session> clients = null;
		System.out.println("roomNum is " + roomNum);
		System.out.println("chatId is " + chatId);
		System.out.println(session);
		String message = "Notice|[" + chatId + "] 님이 입장하셨습니다.";
		if(clientMap.get(roomNum) == null) {
			System.out.println("New Room is " + roomNum);
			clients = new ArrayList<Session>();
			clients.add(session);
			clientMap.put(roomNum, clients);
		} else {
			clients = clientMap.get(roomNum);
			clients.add(session);
		}
		for(Session client : clients)
			if(!client.equals(session))
				client.getBasicRemote().sendText(message);
	}
	
	@OnClose
	public void onClose(Session session) throws IOException{
		System.out.println("close");
		String uri = session.getRequestURI().toString();
		String roomNum = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		String chatId = uri.substring(uri.lastIndexOf("&")+1);
		System.out.println("roomNum is " + roomNum);
		System.out.println("chatId is " + chatId);
		String message = "Notice|[" + chatId + "] 님이 퇴장하셨습니다.";
		List<Session> clients = clientMap.get(roomNum);
		for(Session client : clients)
			if(!client.equals(session))
				client.getBasicRemote().sendText(message);
		clients.remove(session);
		System.out.println(roomNum + " size is " + clients.size());
		if(clients.size() == 0) {
			System.out.println(roomNum + " Clear");
			clientMap.remove(roomNum);
		}
	}
}