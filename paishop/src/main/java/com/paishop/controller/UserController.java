package com.paishop.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
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
import com.paishop.entity.PageModel;
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
	@Autowired
	private AuctionManager auctionManager;
    
	

	@RequestMapping(value = "/addUser1", method = RequestMethod.GET)
	public @ResponseBody String addUser(@RequestParam String openid, @RequestParam String unionid,
			@RequestParam String nickname, @RequestParam int gender, @RequestParam String city, @RequestParam String province,
			@RequestParam String country, @RequestParam String avatarUrl) {
		String res;
		User user = new User();
		user.setOpenid(openid);
		user.setUnionid(unionid);
		user.setNickname(nickname);
		user.setGender(gender);
		user.setCity(city);
		user.setProvince(province);
		user.setCountry(country);
		user.setAvatarUrl(avatarUrl);

		boolean bo = userManager.addUser(user);
		if (bo == true) {
			res = "鎴愬姛";
			return res;
		} else {
			res = "澶辫触";
			return res;
		}
	}

	// 涓汉涓績
	@RequestMapping(value = "/findUserAllInfo", method = RequestMethod.GET)
	public @ResponseBody JSONObject findUserAllInfo(@RequestParam("uId") int uId, @RequestParam("openid") String openid,
			@RequestParam("oStatus") int oStatus, @RequestParam("offset") int offset,
			@RequestParam("pageSize") int pageSize) {
		System.out.println(uId + ":" + oStatus + ":" + offset);
		User user = userManager.findUserByOpenid(openid);
		List<Order> orderList = orderManager.findAllOrderByUser(uId, oStatus, offset, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", user.getNickname());
		map.put("avatarUrl", user.getAvatarUrl());
		// 璁㈠崟鐨勬暟閲�
		map.put("num", orderList.size());
		map.put("detailAddress", user.getDetailAddress());
		JSONObject jsArray = JSONObject.fromObject(map);
		return jsArray;
	}

	// 涓汉淇℃伅
	@RequestMapping(value = "/findUserInfo", method = RequestMethod.GET)
	public @ResponseBody JSONObject findUserInfo(@RequestParam("uId") int uId, @RequestParam("openid") String openid) {
		// System.out.println(uId+":"+oStatus+":"+offset);
		User user = userManager.findUserByOpenid(openid);

		JSONObject jsArray = JSONObject.fromObject(user);
		return jsArray;
	}

	// 鏍规嵁璁㈠崟鐘舵�佸拰鐢ㄦ埛鏌ヨ涓�涓敤鎴风殑璁㈠崟
	@RequestMapping(value = "/findUserOrder", method = RequestMethod.GET)
	public @ResponseBody JSONArray findUserOrder(@RequestParam("uId") int uId, @RequestParam("openid") String openid,
			@RequestParam("oStatus") int oStatus, @RequestParam("offset") int offset,
			@RequestParam("pageSize") int pageSize) {
		// System.out.println(uId+":"+oStatus+":"+offset);
		List<Order> orderList = orderManager.findAllOrderByUser(uId, oStatus, offset, pageSize);
		// System.out.println(orderList);
		JSONArray jsArray = JSONArray.fromObject(orderList);
		// 濡傛灉瀛楁涓殑鍊兼湁null鐨勶紝鎬庝箞杩斿洖鍒板墠绔細鍓嶇鍦ㄤ紶鍏ヤ繚瀛樻暟鎹椂淇濆瓨涓�0鎴栬�呯┖涓�
		return jsArray;
	}

	// 鏌ヨ鐢ㄦ埛鐨勬墍鏈夎鍗�
	@RequestMapping(value = "/findUserOrderByUId", method = RequestMethod.GET)
	public @ResponseBody JSONArray findUserOrderByUId(@RequestParam("uId") int uId,
			@RequestParam("openid") String openid, @RequestParam("offset") int offset,
			@RequestParam("pageSize") int pageSize) {
		List<Order> orderList = orderManager.findUserOrderByUId(uId, offset, pageSize);
		JSONArray jsArray = JSONArray.fromObject(orderList);
		// 濡傛灉瀛楁涓殑鍊兼湁null鐨勶紝鎬庝箞杩斿洖鍒板墠绔細鍓嶇鍦ㄤ紶鍏ヤ繚瀛樻暟鎹椂淇濆瓨涓�0鎴栬�呯┖涓�

		return jsArray;
	}

	// 淇敼鐢ㄦ埛淇℃伅
	@RequestMapping(value = "/modifyUser", method = RequestMethod.GET)
	public @ResponseBody String modifyUser(@RequestParam String openid, @RequestParam String detailAddress) {
		User user = new User();
		String res;
		user.setDetailAddress(detailAddress.toString());
		user.setOpenid(openid);
		boolean b = userManager.modifyUser(user);
		if (b == true) {
			res = "鎴愬姛";
			return res;
		} else {
			res = "澶辫触";
			return res;
		}
	}

	// 鎴戠殑绔炴媿
	@RequestMapping(value = "/findAuctionInfo", method = RequestMethod.GET)
	public @ResponseBody JSONArray findAuctionInfoByUser(@RequestParam int uId, @RequestParam String openid,
			@RequestParam int oStatus, @RequestParam int offset, @RequestParam int pageSize) {

		List<Order> orderList = orderManager.findAllOrderByUser(uId, oStatus, offset, pageSize);
		// System.out.println(auctionList);
		JSONArray jsArray1 = JSONArray.fromObject(orderList);
		List<Auction> auctionList = auctionManager.findAuctionInfoByUser(uId, offset, pageSize);
		JSONArray jsArray2 = JSONArray.fromObject(auctionList);
		JSONArray jsArray = JSONArray.fromObject(jsArray1 + "" + jsArray2);

		return jsArray;
	}

}
