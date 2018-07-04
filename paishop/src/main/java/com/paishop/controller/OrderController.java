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
	
	@RequestMapping(value = "/showOrderUI", method = RequestMethod.GET)
	public @ResponseBody JSONObject showOrderUI(@RequestParam int pId,@RequestParam int uId, 
			@RequestParam String openid,@RequestParam int sId, @RequestParam 	String sUsername,
			@RequestParam String pName,@RequestParam String pPic,@RequestParam float marketPrice,
			@RequestParam int buyPrice, @RequestParam int coupon ) {
		
		      Map<String, Object> orderMap = new HashMap<String, Object>();
		      
		      User user = userManager.findUserByOpenid(openid);
		      System.out.println(user);
		     //JSONArray js= JSONArray.fromObject(user.getDetailAddress());
		     //JSONObject object = (JSONObject) js.get(1);
		     String address="";
		    // if(object.getInt("type")==1) {
		    	 //address=object.getString("defaultAddress");
		     //}
		     Product product = productManager.findProductById(pId);
		     System.out.println(product);
		     orderMap.put("uId", uId);
		     orderMap.put("sId", sId);
		     orderMap.put("sUsername", sUsername);
		      orderMap.put("address", address);
		      orderMap.put("pId", product.getId());
		      orderMap.put("pName", product.getpName());
		      orderMap.put("marketPrice", marketPrice);
		      orderMap.put("buyPrice", buyPrice);
		      orderMap.put("coupon", coupon+"浼樻儬");
		      orderMap.put("buyPrice", buyPrice);
		    //鐢熸垚涓�涓鍗曠紪鍙�
		      orderMap.put("id", UUID.randomUUID().toString());
		      JSONObject jsArray = JSONObject.fromObject(orderMap);
		      return jsArray;
	}
	
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
		order.setsId(sId);
		order.setsUsername(sUsername);
		order.setpPic(pPic);
		order.setMarketPrice(marketPrice);
		order.setBuyPrice(buyPrice);
		order.setoSpec(oSpec);
		order.setoExpress(oExpress);
		order.setExpressFee(expressFee);
		order.setoStatus(oStatus);
		order.setUserRemark(uRemark);
		order.setSumMoney(sumMoney);
		 boolean bo = orderManager.addOrderInfo(order);
		 String result="";
		 if(bo==true) {
			 result="添加成功";
			return result; 
		 }else {
			 result="添加失败";
				return result; 
		 }
		/*orderMap.put("id", id); 
		 orderMap.put("pId", pId);
		 orderMap.put("uId", uId);
		 orderMap.put("address", address);
	     orderMap.put("sId", sId);
	     orderMap.put("sUsername", sUsername);
	      orderMap.put("pName", pName);
	      orderMap.put("pId", pId);
	      orderMap.put("pName", pName);
	      orderMap.put("pPic", pPic);
	      orderMap.put("marketPrice", marketPrice);
	      orderMap.put("buyPrice", buyPrice);
	      orderMap.put("coupon", coupon);
	      orderMap.put("buyPrice", oSpec);
	      orderMap.put("oExpress", oExpress);
	      orderMap.put("expressFee", expressFee);
	      orderMap.put("sum", sumMoney);*/
	   
	      
	}
}
