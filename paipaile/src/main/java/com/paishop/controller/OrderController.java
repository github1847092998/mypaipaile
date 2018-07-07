package com.paishop.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paishop.entity.Order;
import com.paishop.entity.Product;
import com.paishop.entity.User;
import com.paishop.manager.OrderManager;
import com.paishop.manager.ProductManager;
import com.paishop.manager.UserManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderManager orderManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private ProductManager productManager;
	
    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
	public @ResponseBody String addOrder(@RequestParam int id,@RequestParam int pId,@RequestParam int uId, 
			@RequestParam String address,@RequestParam String openid,@RequestParam int sId, @RequestParam String sUsername,
			@RequestParam String pName,@RequestParam String pPic,@RequestParam float marketPrice,@RequestParam float buyPrice, 
			@RequestParam int coupon,@RequestParam String oSpec, @RequestParam String oExpress,@RequestParam String uRemark,
			@RequestParam int oStatus, @RequestParam float expressFee,@RequestParam float sumMoney) {
		Map<String, Object> orderMap = new HashMap<String, Object>();
		Order order = new Order();
		order.setId(id);
		order.setpId(pId);
		order.setuId(uId);
		order.setoAddress(address);		
		order.setMarketPrice(marketPrice);
		order.setBuyPrice(buyPrice);
		order.setoStatus(oStatus);
		order.setUserRemark(uRemark);
		order.setoCreatetime(new Date());
		 boolean bo = orderManager.addOrderInfo(order);
		 String result="";
		 if(bo==true) {
			 result="添加成功";
			return result; 
		 }else {
			 result="添加失败";
				return result; 
		 }      
	}
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
	public @ResponseBody String addOrder(@RequestParam int uId, @RequestParam String wx_openid,
			  @RequestParam int order_id,@RequestParam String address, @RequestParam String message) {
		Map<String, Object> orderMap = new HashMap<String, Object>();
		
		
		return null;
		
	}
}
