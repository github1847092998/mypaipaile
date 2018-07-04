package com.paishop.web.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
	 * Componentע�����SpringMVC������һ��SpringIOC�����¹������
	 * ��ʵ@Controller, @Service, @Repository��@Component��ϸ��
	 */
	@Component
	@EnableWebSocket
	public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	    @Autowired
	    MyWebSocketHandler handler;

	    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

	        //���websocket���������������������
	        webSocketHandlerRegistry.addHandler(handler, "/search").addInterceptors(new MyHandShakeInterceptor());

	        //���websocket���������������������
	        webSocketHandlerRegistry.addHandler(handler, "/ws/sockjs").addInterceptors(new MyHandShakeInterceptor()).withSockJS();
	    }
	}
