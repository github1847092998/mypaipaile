package com.paishop.controller;


 
 import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
 
 import javax.websocket.*;
 import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paishop.entity.User;
import com.paishop.manager.UserManager;

import net.sf.json.JSONObject;
 
 /**
  * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
  * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
  */
 @ServerEndpoint("/websocket")
 public class WebSocketTest {
	 
     //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     private static int onlineCount = 0;
 
     //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
     private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();
 
     //与某个客户端的连接会话，需要通过它来给客户端发送数据
     private Session session;
     @Autowired
     private UserManager userManager;
 
     /**
      * 连接建立成功调用的方法
      * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
      */
     @OnOpen
     public void onOpen(Session session){
         this.session = session;
         webSocketSet.add(this);     //加入set中
         addOnlineCount();           //在线数加
         System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
         //DbUtils db =null;
         //db=new DbUtils(session);
         //db.start();
       
         
     }
 
     /**
      * 连接关闭调用的方法
      */
     @OnClose
     public void onClose(){
    	 
         webSocketSet.remove(this);  //从set中删除
         subOnlineCount();           //在线数减
         System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
     }
 
     /**
      * 收到客户端消息后调用的方法
      * @param message 客户端发送过来的消息
      * @param session 可选的参数
      */
     @OnMessage
     public void onMessage(String message) {
         System.out.println("来自客户端的消息:" + message);
         int uid=Integer.parseInt(message);
         
         System.out.println(uid);
         for(WebSocketTest item: webSocketSet){
             
             try {
				item.sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
     } 

         //User user = new User();
         //UserController usContro = new UserController();
         //Map<String, Object> map=new HashMap<String, Object>();   
         //JSONObject js= new JSONObject();
        
			//Thread.sleep(5000);
			//String message1= usContro.findUser1(2);
			//System.out.println(user);
			/*map.put("nickname", user.getNicknae());
	    	map.put("gender", user.getGender());
	    	map.put("telephone", user.getTelphone());*/
	    	// js= JSONObject.fromObject(map);
	         //message = js.toString();
         
	        
       
         /*map.put("nickname", "nihao");
     	 map.put("gender", "nv");
     	 map.put("telephone", "13546423456");*/
         //群发消息
        
     }
     
   
 
     /**
      * 发生错误时调用
      * @param session
      * @param error
      */
     @OnError
     public void onError(Session session, Throwable error){
         System.out.println("发生错误");
         error.printStackTrace();
     }
 
     /**
      * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
      * @param message
      * @throws IOException
      */
     public void sendMessage(String message) throws IOException{
    	 User user = userManager.findUserByUid(2);
    	 Map<String, Object> map=new HashMap<String, Object>();   
    	 map.put("nickname", user.getNicknae());
     	 map.put("gender", user.getGender());
     	 map.put("telephone", user.getTelphone());
         message=map.toString();
    	 System.out.println("message"+message);
    	 //message = us.findUser1(uid);
    	
         this.session.getBasicRemote().sendText(message);
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