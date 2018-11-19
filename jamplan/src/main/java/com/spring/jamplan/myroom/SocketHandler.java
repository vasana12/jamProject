package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.spring.jamplan.model.MessageVO;

@Repository
public class SocketHandler extends TextWebSocketHandler{
	
	@Autowired(required=false)
	SqlSession sqlSession;
	
	private final Logger logger = LogManager.getLogger(getClass());
	HttpServletRequest request;
	
	
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();
	
	public SocketHandler() {
		super();
		this.logger.info("create SocketHandler instance!");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		
		sessionSet.remove("remove session from myroom!");
	}
	

	@Override 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		if (session.getAcceptedProtocol().equals("mailCheck")) {
			sessionSet.add(session);
		}
//		else {
//			session.close();
//		}
		
		
		this.logger.info("add session!");
	}
	

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
		this.logger.info(message.getPayload().getClass());
		System.out.println("(String)(message.getPayload()) = " + (String)(message.getPayload()));
		
		
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		ArrayList<MessageVO> messageList = myRoomMapper.getMessageList((String)(message.getPayload()));
		session.sendMessage(new TextMessage("" + messageList.size() + ""));

		Map<String, Object> map = session.getAttributes();
		String userId = (String)map.get("userId");
		System.out.println("전송자 아이디: " + userId);
		
		for(WebSocketSession client_session : this.sessionSet) {
			if(client_session.isOpen()) {
				try {
					client_session.sendMessage(message);
				}catch(Exception ignored) {
					
					this.logger.error("fail to send message!", ignored);
				}
			}
		}
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("에러 발생");
		this.logger.error("web socket error!", exception);
	}
	
	
	@Override
	public boolean supportsPartialMessages() {
		this.logger.info("call method!");
		
		return false;
	}
}
