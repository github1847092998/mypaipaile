package com.paishop.websocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final ArrayList<WebSocketSession> users;
	
	/** 鏃ュ織 */
    private Logger logger = LoggerFactory.getLogger(MyHandler.class);
	 
    static {
        users = new ArrayList<WebSocketSession>();
    }
	
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);  
        TextMessage res = new TextMessage(message.getPayload()+" received at server");  
        session.sendMessage(res);  
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connect to the websocket success......");
        users.add(session);
       /* String userName = (String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME);
        if(userName!= null){
            //鏌ヨ鏈娑堟伅
            int count = webSocketService.getUnReadNews((String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME));
 
            session.sendMessage(new TextMessage(count + ""));
        }*/
        
        
       /* String userCd = (String) session.getAttributes().get("WS_USER_CD");
        if(userCd != null) {
        	// 寰楀埌DB璇ョ敤鎴锋湭璇绘秷鎭�
        	
        	// 鍙戦�佺粰鎸囧畾cd鐢ㄦ埛
        	session.sendMessage(new TextMessage("<a href='../rebook/exApplyCenter?remark=1'>杩欐槸鏈淇℃伅</a>"));
        	
        	logger.info("------in------");
        }*/
    }
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("websocket connection closed......");
        users.remove(session);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    	System.out.println("websocket connection closed......");
        users.remove(session);
    }
    
    /**
     * 缁欐墍鏈夊湪绾跨敤鎴峰彂閫佹秷鎭�
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage res) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(res);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 
     *
     * @param userCd
     * @param message
     */
    public void sendMessageToUser(String userCd, TextMessage message) {
        for (WebSocketSession user : users) {
            if (userCd.equals(user.getAttributes().get("WS_USER_CD"))) { // 搴斾粠session鍙朇D瀵规瘮
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}