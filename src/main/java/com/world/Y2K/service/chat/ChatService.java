package com.world.Y2K.service.chat;

import java.util.List;

import com.world.Y2K.model.vo.chat.ChatMessage;
import com.world.Y2K.model.vo.chat.ChatRoom;

public interface ChatService {

	//���ȣ�� �����ϴ� �޼ҵ�
	//���ȣ�� ���� �����ຸ��
	//@param roomid
	//@return
	ChatRoom selectChatRoom(String roomId);

	
	
	//ä�� �޼��� DB�� ����
	//@param chatMessage
	//@return
	int insertMessage(ChatMessage chatMessage);
	
	
	
	//�޼��� ���� ����Ʈ ���
	//@param roomId
	//@return
	List<ChatMessage> messageList(String roomId);
	
	
	
	//ä�ù�DB����
	//@param room
	//@return
	int createChat(ChatRoom room);	
	
	
	
	//DB�� ä�ù� �ִ��� Ȯ��
	//@param room
	//@return
	ChatRoom searchChatRoom(ChatRoom room);



	void updateCount(ChatMessage message);
	
	
	
	
	
	
	
	
	
}
