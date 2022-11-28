package com.world.Y2K.model.vo.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




//���ȣ�� ����� - ������ ������ ����ִ�.
//roomId�� ä�ù��� �����ϸ�,
//unReadCount�� ���� �о����� �ľ� �� �� �ְ� ���ش�.


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoom {
    
    private String roomId;        // �� ��ȣ ����
    
    private String userEmail;    // ����� �̸��� �̰� �ʼ��ΰ�..?
    private String userName;    // ����� �̸�
    private String masterEmail; // ���� �̸���
    private String masterName;    // ���� �̸�
 
    
}