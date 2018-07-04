package com.paishop.web.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paishop.entity.Message;
import com.paishop.entity.Product;
import com.paishop.manager.ProductManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


@Component
public class MyWebSocketHandler implements WebSocketHandler{

    @Autowired
    private ProductManager productManager;
    
    private static int onlineCount = 0;

    //��MyWebSocketHandler�౻����ʱ�ͻᴴ����Map���������
    public static final Map<Integer, WebSocketSession> userSocketSessionMap;
    
    private static CopyOnWriteArraySet<MyWebSocketHandler> webSocketSet = new CopyOnWriteArraySet<MyWebSocketHandler>();

    static {
        userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
    }

    //����ʵ�����Ӻ�
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
    	webSocketSet.add(this);
    	addOnlineCount();
        int uid = (Integer) webSocketSession.getAttributes().get("uid");
        
        if (userSocketSessionMap.get(uid) == null) {
            userSocketSessionMap.put(uid, webSocketSession);
        }
    }

    //������Ϣǰ�Ĵ���
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

        if(webSocketMessage.getPayloadLength()==0)return;

        //�õ�Socketͨ���е����ݲ�ת��ΪMessage����
        Product product=new Gson().fromJson(webSocketMessage.getPayload().toString(),Product.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        product.setModifyTime(now);
        //����Ϣ���������ݿ�
        productManager.modifyProductInfo(product);
        MyWebSocketHandler handler = new MyWebSocketHandler();
        //����Socket��Ϣ
        handler.sendMessage(product.getId(),new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(product)));
    }

    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    /**
     * �ڴ�ˢ��ҳ����൱�ڶϿ�WebSocket����,ԭ���ھ�̬����userSocketSessionMap�е�
     * WebSocketSession���ɹر�״̬(close)������ˢ�º�ĵڶ������ӷ�����������
     * ��WebSocketSession(open״̬)�ֲ�����뵽userSocketSessionMap��,�����������޷�������Ϣ
     * ���Ӧ���ڹر����������������ȥ��userSocketSessionMap�е�ǰ����close״̬��WebSocketSession��
     * ���´�����WebSocketSession(open״̬)���Լ��뵽userSocketSessionMap��
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

        System.out.println("WebSocket:"+webSocketSession.getAttributes().get("uid")+"close connection");
        Iterator<Map.Entry<Integer,WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,WebSocketSession> entry = iterator.next();
            if(entry.getValue().getAttributes().get("uid")==webSocketSession.getAttributes().get("uid")){
                userSocketSessionMap.remove(webSocketSession.getAttributes().get("uid"));
                System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("uid") + "removed");
            }
            subOnlineCount();
        }
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    //������Ϣ��ʵ��
    public void sendMessage(int id, TextMessage message) throws IOException {
        WebSocketSession session = userSocketSessionMap.get(id);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }
    
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocketHandler.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
    	MyWebSocketHandler.onlineCount--;
	    }
}