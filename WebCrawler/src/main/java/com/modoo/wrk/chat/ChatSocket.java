package com.modoo.wrk.chat;

import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

@ServerEndpoint(value="/chat.do", configurator=SpringConfigurator.class)
public class ChatSocket {
	@Autowired
	private ChatRepository chatRepository;
	
	public ChatSocket() {
		System.out.println("=======ChatSocket Open!=======");
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		String msg = chatRepository.makeMessage(message, session);
		List<Session> clients = chatRepository.getClients(session);
		
		for(Session client : clients)
			if(!client.equals(session))
				client.getBasicRemote().sendText(msg);
		
		chatRepository.saveMessage(message, session);
	}
	
	@OnOpen
	public void onOpen(Session session) throws Exception {
		String message = chatRepository.welcomeUser(session);
		List<Session> clients = chatRepository.getClients(session);
		
		for(Session client : clients)
			if(!client.equals(session))
				client.getBasicRemote().sendText(message);
	}
	
	@OnClose
	public void onClose(Session session) throws Exception {
		String rseq = chatRepository.getRseq(session);
		String msg = chatRepository.byeUser(session);
		
		if(!chatRepository.emptyRoom(rseq)) {
			List<Session> clients = chatRepository.getClients(rseq);
			for(Session client : clients)
					client.getBasicRemote().sendText(msg);
		}
	}
}
