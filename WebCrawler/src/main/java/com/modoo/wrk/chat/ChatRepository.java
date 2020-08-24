package com.modoo.wrk.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.modoo.wrk.chat.impl.ChatService;

@Component
public class ChatRepository {
	@Autowired
	private ChatService chatService;
	private Map<String, List<Session>> clientMap;
	
	public ChatRepository() {
		this.clientMap = new HashMap<String, List<Session>>();
	}
	
	public String getRseq(Session session) {
		String uri = session.getRequestURI().toString();
		String rseq = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		
		return rseq;
	}
	
	public List<Session> getClients(Session session) {
		String uri = session.getRequestURI().toString();
		String rseq = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		
		return clientMap.get(rseq);
	}
	
	public List<Session> getClients(String rseq) {
		return clientMap.get(rseq);
	}
	
	public Boolean emptyRoom(String rseq) {
		return (clientMap.get(rseq) == null);
	}
	
	public String makeMessage(String message, Session session) {
		String uri = session.getRequestURI().toString();
		String id = uri.substring(uri.lastIndexOf("&")+1);
		
		return "msg|" + id + "|" + message;
	}
	
	public void saveMessage(String message, Session session) {
		String uri = session.getRequestURI().toString();
		String rseq = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		String id = uri.substring(uri.lastIndexOf("&")+1);
		
		ChatVO vo = new ChatVO();
		
		vo.setRseq(Integer.parseInt(rseq));
		vo.setId(id);
		vo.setContent(message);
		
		chatService.insertChat(vo);
	}
	
	public String byeUser(Session session) {
		String uri = session.getRequestURI().toString();
		String rseq = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		String id = uri.substring(uri.lastIndexOf("&")+1);
		
		List<Session> clients = clientMap.get(rseq);
		clients.remove(session);
		
		if(clients.size() == 0)
			clientMap.remove(rseq);
		
		return "msg|" + id + "|" + id + "님이 퇴장하셨습니다.";
	}
	
	public String welcomeUser(Session session) {
		String uri = session.getRequestURI().toString();
		String rseq = uri.substring(uri.lastIndexOf("?")+1,uri.lastIndexOf("?")+2);
		String id = uri.substring(uri.lastIndexOf("&")+1);
		
		List<Session> clients = null;
		
		if(clientMap.get(rseq) == null) {
			clients = new ArrayList<Session>();
			clients.add(session);
			clientMap.put(rseq, clients);
		} else {
			clients = clientMap.get(rseq);
			clients.add(session);
		}
		
		return "msg|" + id + "|" + id + "님이 입장하셨습니다.";
	}
}
