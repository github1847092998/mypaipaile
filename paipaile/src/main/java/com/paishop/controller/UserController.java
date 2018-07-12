package com.paishop.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paishop.entity.Auction;
import com.paishop.entity.Order;
import com.paishop.entity.Saler;
import com.paishop.entity.User;
import com.paishop.manager.AuctionManager;
import com.paishop.manager.OrderManager;
import com.paishop.manager.SalerManager;
import com.paishop.manager.UserManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private OrderManager orderManager;

	@Autowired
	private SalerManager salerManager;
	

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public @ResponseBody JSONObject addUser(@RequestParam String openid, 
			@RequestParam String nickname, @RequestParam int gender, @RequestParam String area, 
			@RequestParam String avatar_url,@RequestParam String telephone, 
			@RequestParam JSONArray detailAddress ) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(openid==null || "".equals(openid)) {
			map.put("ret", 0);
			map.put("msg", "授权失败");
		}
		User user = new User();
        user.setWxOpenid(openid);
        user.setNicknae(nickname);
        user.setGender(gender);
        user.setArea(area);
		user.setAvatarUrl(avatar_url);
        //user.setTelphone(telephone);
        //user.setDetailAddress(detailAddress.toString());
		int i = userManager.addUser(user);
		if (i == 1) {
			map.put("ret", 1);
			map.put("msg", "授权成功");
		} else {
			map.put("ret", 0);
			map.put("msg", "授权失败");
		}
		JSONObject js = JSONObject.fromObject(map);
		return js;
	}

	// 个人中心页面
	@RequestMapping(value = "/findUserAllInfo", method = RequestMethod.GET)
	public @ResponseBody JSONObject findUserAllInfo(@RequestParam("uid") int uid, 
			@RequestParam("openid") String openid, @RequestParam("oStatus") int oStatus, 
			@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userManager.findUserByUid(uid);
		map.put("nickname", user.getNicknae());
		map.put("avatarUrl", user.getAvatarUrl());
		JSONObject js = JSONObject.fromObject(map);
		return js;
	}

	// 查询收货地址
	@RequestMapping(value = "/getAddress", method = RequestMethod.GET)
	public @ResponseBody JSONArray getAddress(@RequestParam("uid") int uid, @RequestParam("openid") String openid) {
		// System.out.println(uId+":"+oStatus+":"+offset);
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userManager.findUserByUid(uid);
		JSONArray js = JSONArray.fromObject(user.getDetailAddress());
		for (Object object : js) {
			JSONObject jsObject = JSONObject.fromObject(object);
			map.put("receiver", jsObject.get("receiver"));
			map.put("phone", jsObject.get("phone"));
			map.put("region", jsObject.get("region"));
			map.put("detail_address", jsObject.get("detail_address"));
			map.put("default_address", jsObject.get("default_address"));
		}
		JSONArray jsArray = JSONArray.fromObject(map);
		return jsArray;
	}

	// 添加收货地址
	@RequestMapping(value = "/addAddress", method = RequestMethod.GET)
	public @ResponseBody JSONObject addAddress(@RequestParam("uid") int uid, @RequestParam("wx_openid") String wx_openid,
			@RequestParam("receiver") String receiver, @RequestParam("phone") String phone,
			@RequestParam("region") String region, @RequestParam("detail_address") String detail_address,
			@RequestParam("default_address") String default_address) {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		User user = new User();
		map.put("receiver", receiver);
		map.put("phone", phone);
		map.put("region", region);
		map.put("detail_address", detail_address);
		map.put("default_address", default_address);
		String address = this.findUserAddress(uid);
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(address).append(map.toString());
		user.setDetailAddress(strBuffer.toString());
		
		System.out.println(strBuffer.toString());
		user.setuId(uid);
		int i = userManager.modifyUser(user);
		if (i == 1) {
			map1.put("ret", 1);
			map1.put("msg", "添加地址成功");
		} else {
			map1.put("ret", 0);
			map1.put("msg", "添加地址失败");
		}
		JSONObject js = JSONObject.fromObject(map1);
		return js;
	}
	
	//找到用户已经有的地址
	public String findUserAddress(int uid ) {
		User user = userManager.findUserByUid(uid);
		System.out.println(user);
		String detailAddress=user.getDetailAddress();
		System.out.println(detailAddress);
		return detailAddress;
	}
	
	    /* // 查询用户的所有订单
		@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
		public @ResponseBody JSONArray getAllOrders(@RequestParam("uid") int uid, 
				   @RequestParam("wx_openid") String wx_openid, @RequestParam("offset") int offset,
				   @RequestParam("pageSize") int pageSize) {
			// System.out.println(uId+":"+oStatus+":"+offset);
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<Order> orderList = orderManager.findAllOrderByUser(uid);
			Order order1=new Order();
			for (Order order : orderList) {
				map.put("order_id", order.getId());
			    //order1 = orderManager.findOrderById(order.getId());
				map.put("good_id", order.getpId());
				
				//map.put("good_title", order1.getProduct().getpName());
				map.put("good_market_price", order.getMarketPrice());
				map.put("good_auction_price", order.getBuyPrice());
				map.put("order_status", order.getoStatus());
				
			}
			//map.put("order1", order1);
			//System.out.println(map.get("order1"));
			JSONArray jsArray = JSONArray.fromObject(map);
			return jsArray;
		}*/

}
