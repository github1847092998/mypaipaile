package com.paishop.testUser;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/*@ClientEndpoint
	public class Test {

	    @OnOpen
	    public void onOpen(Session session) {
	        System.out.println("Connected to endpoint: " + session.getBasicRemote());
	    }

	    @OnMessage
	    public void onMessage(String message) {
	        System.out.println(message);
	    }

	    @OnError
	    public void onError(Throwable t) {
	        t.printStackTrace();
	    }
	  
	    public static void sendMsg() {
	    	         try {
	    	              WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // ��ȡWebSocket�����������о���ʵ�ֿ��Բ���websocket-api.jar��Դ��,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
	    	              String uri = "ws://localhost:8080/paishop/log";
	    	              Session session = container.connectToServer(Test.class, new URI(uri)); // ���ӻỰ
	    	              session.getBasicRemote().sendText("123132132131"); // �����ı���Ϣ
	    	              session.getBasicRemote().sendText("4564546");
	    	         } catch (Exception e) {
	    	              e.printStackTrace();
	    	         }
	    	     }
}*/
