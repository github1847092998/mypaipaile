package com.paishop.controller;

	 import java.io.IOException;
	 import java.util.concurrent.CopyOnWriteArraySet;
	 import javax.websocket.*;
	 import javax.websocket.server.ServerEndpoint;
	 
	/*@ServerEndpoint("/websocket")
	 public class WebSocketTest {
	     private static int onlineCount = 0;
	     //线程安全的类
	     private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();
	
	        private Session session;
	          
	          @OnOpen
	          public void onOpen(Session session){
	             this.session = session;
	              webSocketSet.add(this);    //加入集合
	             addOnlineCount();           //计算当前在线人数
	              System.out.println("当前在线人数：" + getOnlineCount());
	         }
	          *//**
	                * 
	                * 
	                *//*
	               @OnClose
	               public void onClose(){
	                   webSocketSet.remove(this);  //
	                   subOnlineCount();           //
	               }       
	               
	                    @OnMessage
	                    public void onMessage(String username, String password,Session session) {
	                        System.out.println("数据是:" + username+password);
	                        //double price=Double.parseDouble(message);
	                        //message = String.valueOf(price*1.1);
	                       //
	                        for(WebSocketTest item: webSocketSet){
	                            try {
	                                item.sendMessage(username, password);
	                               // item.sendMessage(message1);
	                            } catch (IOException e) {
	                                e.printStackTrace();
	                               continue;
	                            }
	                        }
	                    }
	                    @OnError
	                         public void onError(Session session, Throwable error){
	                             System.out.println("错误信息");
	                             error.printStackTrace();
	                         }
	                     
	                        
	                         public void sendMessage(String message,String message1) throws IOException{
	                        	 //text= String.valueOf(Double.parseDouble(text)*1.1);
	                             this.session.getBasicRemote().sendText(message);
	                             this.session.getBasicRemote().sendText(message1);
//	                             this.session.getBasicRemote().sendText(message1);
//	                             this.session.getBasicRemote().sendText(message2);
//	                             this.session.getBasicRemote().sendText(message3);
	                             //this.session.getAsyncRemote().sendText(message);
	                         }
	                     
	                         public static synchronized int getOnlineCount() {
	                             return onlineCount;
	                         }
	                     
	                         public static synchronized void addOnlineCount() {
	                             WebSocketTest.onlineCount++;
	                         }
	                         public static synchronized void subOnlineCount() {
	                    	 WebSocketTest.onlineCount--;
	                    	    }
	                    }
*/