package com.paishop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.paishop.entity.Product;
import com.paishop.manager.ProductManager;

import net.sf.json.JSONArray;

public class TestController {
	static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired(required = false)
    private ProductManager productManager;

    @Bean
    public MyWebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }


    @RequestMapping("/auditing")
    
    public @ResponseBody String auditing(@RequestParam int id){
        //无关代码都省略了
         List<Product> productList = productManager.findAllProducts(0, 2);
        try {
			myWebSocketHandler().sendMessage(id,new TextMessage(productList.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "成功";
    }
}
