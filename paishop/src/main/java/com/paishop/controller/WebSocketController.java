/*package com.paishop.controller;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.messaging.handler.annotation.MessageMapping;
	import org.springframework.messaging.handler.annotation.SendTo;
	import org.springframework.messaging.simp.SimpMessagingTemplate;
	import org.springframework.messaging.simp.annotation.SendToUser;
	import org.springframework.stereotype.Controller;

import com.paishop.entity.Product;
	 
	@Controller
	public class WebSocketController {
	 
	    public SimpMessagingTemplate template;
	 
	    @Autowired
	    public WebSocketController(SimpMessagingTemplate template) {
	        this.template = template;
	    }
	 
	    @MessageMapping("/test")
	    @SendTo("/test/find")
	    public Product findProduct(Product product) throws Exception {
	        return product;
	    }
	 
	    @MessageMapping("/message")
	    @SendToUser("/message")
	    public UserMessage userMessage(UserMessage userMessage) throws Exception {
	        return userMessage;
	    }
	 
	}*/
	