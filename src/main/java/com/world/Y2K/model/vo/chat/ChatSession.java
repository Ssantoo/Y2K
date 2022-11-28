package com.world.Y2K.model.vo.chat;

import java.util.ArrayList;

import org.springframework.stereotype.Component;



//���� �α��� �Ǿ� �ִ� �������� ����
//DB ���� X / �¶��� �������� Ȯ�ο�

@Component("cSession")
public class ChatSession {
    
    // static���� �ʵ庯���� �����Ͽ� ���� ArrayList�� �̿� �� �� �ֵ��� �����մϴ�.
    private static ArrayList<String> loginUser = new ArrayList<String>();
    
    // �α��� �� ArrayList�� �߰��մϴ�.
    public void addLoginUser(String email) {
        loginUser.add(email);
    }
    //�̸��� �ʼ� �ƴϸ� �ٸ��ɷ� ��ü�غ���
    // �α׾ƿ� �� ArrayList���� �����մϴ�. 
    public void removeLoginUser(String email) {
        loginUser.remove(email);
    }
 
    // ���� �α��� �Ǿ� �ִ� ���� ����� �����ɴϴ�.
    public static ArrayList<String> getLoginUser() {
        return loginUser;
    }
 
    // �ڵ� setter. ������� �ʴ´�
    public static void setLoginUser(ArrayList<String> loginUser) {
        ChatSession.loginUser = loginUser;
    }
}
