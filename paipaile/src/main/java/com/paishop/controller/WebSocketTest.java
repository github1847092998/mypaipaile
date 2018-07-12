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
  * @ServerEndpoint ע����һ�����ε�ע�⣬���Ĺ�����Ҫ�ǽ�Ŀǰ���ඨ���һ��websocket��������,
  * ע���ֵ�������ڼ����û����ӵ��ն˷���URL��ַ,�ͻ��˿���ͨ�����URL�����ӵ�WebSocket��������
  */
 @ServerEndpoint("/websocket")
 public class WebSocketTest {
	 
     //��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
     private static int onlineCount = 0;
 
     //concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
     private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();
 
     //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
     private Session session;
     @Autowired
     private UserManager userManager;
 
     /**
      * ���ӽ����ɹ����õķ���
      * @param session  ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
      */
     @OnOpen
     public void onOpen(Session session){
         this.session = session;
         webSocketSet.add(this);     //����set��
         addOnlineCount();           //��������
         System.out.println("�������Ӽ��룡��ǰ��������Ϊ" + getOnlineCount());
         //DbUtils db =null;
         //db=new DbUtils(session);
         //db.start();
       
         
     }
 
     /**
      * ���ӹرյ��õķ���
      */
     @OnClose
     public void onClose(){
    	 
         webSocketSet.remove(this);  //��set��ɾ��
         subOnlineCount();           //��������
         System.out.println("��һ���ӹرգ���ǰ��������Ϊ" + getOnlineCount());
     }
 
     /**
      * �յ��ͻ�����Ϣ����õķ���
      * @param message �ͻ��˷��͹�������Ϣ
      * @param session ��ѡ�Ĳ���
      */
     @OnMessage
     public void onMessage(String message) {
         System.out.println("���Կͻ��˵���Ϣ:" + message);
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
         //Ⱥ����Ϣ
        
     }
     
   
 
     /**
      * ��������ʱ����
      * @param session
      * @param error
      */
     @OnError
     public void onError(Session session, Throwable error){
         System.out.println("��������");
         error.printStackTrace();
     }
 
     /**
      * ������������漸��������һ����û����ע�⣬�Ǹ����Լ���Ҫ��ӵķ�����
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