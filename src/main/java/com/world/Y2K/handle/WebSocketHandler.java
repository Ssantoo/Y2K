package com.world.Y2K.handle;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.world.Y2K.model.vo.chat.ChatMessage;
import com.world.Y2K.model.vo.chat.ChatRoom;
import com.world.Y2K.service.chat.ChatService;



@Controller
public class WebSocketHandler extends TextWebSocketHandler implements InitializingBean {
	
	@Autowired
	private ChatService chatService;
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	// ä�ù� ��� <�� ��ȣ, ArrayList<session> >�� ����.
    private Map<String, ArrayList<WebSocketSession>> RoomList = new ConcurrentHashMap<String, ArrayList<WebSocketSession>>();
    
    // session, �� ��ȣ�� ����.
    private Map<WebSocketSession, String> sessionList = new ConcurrentHashMap<WebSocketSession, String>();
    
    private static int i;
    
    
    /**
     * websocket ���� ���� ��
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        i++;
        System.out.println(session.getId() + " ���� ���� => �� ���� �ο� : " + i + "��");
    }
    
    /**
     * websocket ���� ���� ��
     */
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    			i--;
    			
    			System.out.println(session.getId() + "���� ���� => �� ���� �ο� : "+ i + "��");
    			
    			//sessionList�� session�� �ִٸ�
    			
    			if(sessionList.get(session) != null) {
    				//�ش� session�� �� ��ȣ�� �����ͼ� ���� ã�� �� ���� ArrayList<session>���� �ش� session�� �����.
    				
    				RoomList.get(sessionList.get(session)).remove(session);
    				sessionList.remove(session);
    		
    			}
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	
    	//���� ���� �޼���
    	String msg = message.getPayload();
    	
    	//Json��ü -> java
    	 // ��°� : [roomId=123, messageId=love, message=asdf, name=������, email=Ssanto@gmail.com, unReadCount=0]
        ChatMessage chatMessage = objectMapper.readValue(msg,ChatMessage.class);
        
        // ���� �޼����� ��� roomId�� �ش� ä�ù��� ã�ƿ´�.
        ChatRoom chatRoom = chatService.selectChatRoom(chatMessage.getRoomId());
        
        // ä�� ���� ��Ͽ� ä�ù��� �������� �ʰ�, ó�� ���԰�, DB������ ä�ù��� ���� ��
        // ä�ù� ����
        if(RoomList.get(chatRoom.getRoomId()) == null && chatMessage.getMessage().equals("ENTER-CHAT") && chatRoom != null) {
            
            // ä�ù濡 �� session��
            ArrayList<WebSocketSession> sessionTwo = new ArrayList<>();
            // session �߰�
            sessionTwo.add(session);
            // sessionList�� �߰�
            sessionList.put(session, chatRoom.getRoomId());
            // RoomList�� �߰�
            RoomList.put(chatRoom.getRoomId(), sessionTwo);
            // Ȯ�ο�
            System.out.println("ä�ù� ����");
        }
        
        // ä�ù��� ���� �� ��
        else if(RoomList.get(chatRoom.getRoomId()) != null && chatMessage.getMessage().equals("ENTER-CHAT") && chatRoom != null) {
            
            // RoomList���� �ش� ���ȣ�� ���� ���� �ִ��� Ȯ��.
            RoomList.get(chatRoom.getRoomId()).add(session);
            // sessionList�� �߰�
            sessionList.put(session, chatRoom.getRoomId());
            // Ȯ�ο�
            System.out.println("������ ä�ù����� ����");
        }
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
