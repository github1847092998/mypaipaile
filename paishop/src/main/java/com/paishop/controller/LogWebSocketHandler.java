/*package com.paishop.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
 import org.springframework.web.socket.TextMessage;
 import org.springframework.web.socket.WebSocketSession;
 import org.springframework.web.socket.handler.TextWebSocketHandler;
 
 
 public class LogWebSocketHandler extends TextWebSocketHandler {

     private SimpMessagingTemplate template;
 
     public LogWebSocketHandler(SimpMessagingTemplate template) {
         this.template = template;
         System.out.println("��ʼ�� handler");
     }
      @Override
     protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
         String text = message.getPayload(); // ��ȡ�ύ��������Ϣ
         System.out.println("handMessage:" + text);
         // template.convertAndSend("/topic/getLog", text); // �������ڹ㲥
         session.sendMessage(message);
     }
 }*/