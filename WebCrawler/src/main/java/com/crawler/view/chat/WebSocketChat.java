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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.crawler.biz.chat.ChatVO;
import com.crawler.biz.chat.impl.ChatService;

@ServerEndpoint(value="/chat.do", configurator=SpringConfigurator.class)
public class WebSocketChat {
	@Autowired
	ChatService chatService;
	private static Map<String, List<Session>> clientMap = new HashMap<String, List<Session>>();
	private static Map<String, List<String>> userMap = new HashMap<String, List<String>>();
	
	public WebSocketChat() {
		System.out.println("웹소켓(서버) 객체 생성");
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException{
		System.out.println(message);
		System.out.println(chatService);
		ChatVO vo = new ChatVO();
		String sender = message.split("\\|")[0];
		String msg = message.split("\\|")[1];
		String uri = session.getRequestURI().toString();
		String roomNum = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		System.out.println("roomNum is " + roomNum);
		System.out.println("chat Id " + sender);
		System.out.println("msg is " + msg); 
		vo.setRnum(Integer.parseInt(roomNum));
		vo.setId(sender);
		vo.setData(msg);
		chatService.insertChat(vo);
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
		List<String> users = null;
		
		System.out.println("roomNum is " + roomNum);
		System.out.println("chatId is " + chatId);
		System.out.println(session);
		String message = "Notice|[" + chatId + "] 님이 입장하셨습니다.";
		
		if(clientMap.get(roomNum) == null) {
			System.out.println("New Room is " + roomNum);
			clients = new ArrayList<Session>();
			users = new ArrayList<String>();
			clients.add(session);
			users.add(chatId);
			clientMap.put(roomNum, clients);
			userMap.put(roomNum, users);
		} else {
			clients = clientMap.get(roomNum);
			users = userMap.get(roomNum);
			clients.add(session);
			users.add(chatId);
		}
		
		String userListMsg = users.toString();
		userListMsg = userListMsg.replace("[", "");
		userListMsg = userListMsg.replace("]", "");
		System.out.println(userListMsg);
		
		for(Session client : clients){
			if(!client.equals(session))
				client.getBasicRemote().sendText(message);
			client.getBasicRemote().sendText("userList|" + userListMsg);
		}
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
		List<String> users = userMap.get(roomNum);
		users.remove(chatId);
		
		String userListMsg = users.toString();
		userListMsg = userListMsg.replace("[", "");
		userListMsg = userListMsg.replace("]", "");
		
		System.out.println(userListMsg);
		for(Session client : clients) {
			if(!client.equals(session)) {
				client.getBasicRemote().sendText(message);
				client.getBasicRemote().sendText("userList|" + userListMsg);
			}
		}
		
		clients.remove(session);
		
		
		System.out.println(roomNum + " size is " + clients.size());
		if(clients.size() == 0) {
			System.out.println(roomNum + " Clear");
			clientMap.remove(roomNum);
			userMap.remove(roomNum);
		}
	}
}
