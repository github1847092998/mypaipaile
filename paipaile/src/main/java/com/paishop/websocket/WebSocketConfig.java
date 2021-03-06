package com.paishop.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  
@EnableWebMvc  
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer { 
	private static final long heartbeatTime =  60000L; // 1 minute
	
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	try {
    		registry.addHandler(myHandler(), "/myHandler").addInterceptors(new MyHandshakeInterceptor());
     	   
        	registry.addHandler(myHandler(), "/sockjs/myHandler").addInterceptors(new MyHandshakeInterceptor())
        		.withSockJS().setHeartbeatTime(heartbeatTime);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
    	
    }

    @Bean
    public WebSocketHandler myHandler() {
    	
    	return new MyHandler();
    }

}