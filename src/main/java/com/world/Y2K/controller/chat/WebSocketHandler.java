package com.world.Y2K.controller.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Controller
public class WebSocketHandler extends TextWebSocketHandler{

	//websocket ���� ������
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }
	
	
	//websocket ���� �����
	 @Override
	 public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	 
	 }
	
	 //websocket �޼��� ���� �� �۽�
	 @Override
	  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	 
	    }
	
	
	
	
	
	
	
}
