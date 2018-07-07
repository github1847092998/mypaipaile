package com.paishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebMvc
@EnableWebSocket
	public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	    @Autowired
	    MyWebSocketHandler handler;

	    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

	        //���websocket���������������������
	        webSocketHandlerRegistry.addHandler(handler, "/websocket").addInterceptors(new MyHandShakeInterceptor());

	        //���websocket���������������������
	        webSocketHandlerRegistry.addHandler(handler, "/websocket/sockjs").addInterceptors(new MyHandShakeInterceptor()).withSockJS();
	    }
	}