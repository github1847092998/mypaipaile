package com.paishop.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.paishop.entity.Product;

/*@Controller
	public class MessageController {
	    @MessageMapping("/product")
	    @SendTo("/topic/messages")
	    public Product returnMessage(HelloMessage message) throws Exception {
	        Thread.sleep(3000); // simulated delay
	        return new Greeting("Hello, " + message.getName() + "!");
	    }
}*/
