package com.world.Y2K.model.vo.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessage {

	private String roomId;            // �� ��ȣ
    private String messageId;        // �޼��� ��ȣ
    private String message;            // �޼��� ����
    private String name;            // ������ �̸�
    private String email;            // ������ �̸���
    private int unReadCount;        // �� ���� �޼��� ��
    private int sessionCount;        // ���� ���� ��
	
	
}
