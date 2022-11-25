package com.world.Y2K.controller.test;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("rService")
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private ReplyDAO rDAO;

	@Override
	public Map<String, Object> insertReply(String content, Long boardNo, String nickName, Long userNo) {
		
//		Photo photo = new Photo();
//		photo.setBoardNo(boardNo);
		
//		System.out.println("���� : " + content);
//		System.out.println("���� : " + boardNo);
//		System.out.println("���� : " + rNickName);
//		System.out.println("���� : " + userNo);
		
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("content", content);
		map.put("nickname", nickName);
		map.put("boardNo", boardNo);
		map.put("userNo", userNo);
		rDAO.insertReply(sqlSession, map);
//		Reply reply = new Reply();
//		reply.setReplyContent(content);
//		reply.setRboardNo(boardNo);
//		reply.setReplyWriter(userNo);
//		reply.setRNickName(rNickName);
	
		
		
		
		return map;
	}
	
	
	
}
