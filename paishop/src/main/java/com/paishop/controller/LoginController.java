package com.paishop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.paishop.utils.WechatAccessToken;
import com.paishop.utils.WechatAuthProcessUtil;
import com.paishop.utils.WechatSNSUserInfo;
@Controller
@RequestMapping("/weixin")
public class LoginController {
	/**
	 * 确认请求来自微信服务器  微信的回调
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String OAuthTest(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");
	   response.setCharacterEncoding("utf-8");
	   PrintWriter out = response.getWriter();
	   // 用户同意授权后，能获取到code
	   String code = request.getParameter("code");
	   String state = request.getParameter("state");
	   String appid="wx7d6d5a75539c9e8e";
	   String secret=" ca733bea9f1e0143fa7e719ead933069";
	   String result="";
	   // 用户同意授权
	   if (!"authdeny".equals(code)) {
	      // 获取网页授权access_token
	      WechatAccessToken wechatAccessToken = WechatAuthProcessUtil.getOauthAccessToken(appid,secret,code);
//	      if(null == wechatAccessToken){
//	         if(state.equals("register")){
//	            return "redirect:/system/wechatregisterpage";
//	         }
//	         return "redirect:/system/loginpage";
//	      }
	      // 网页授权接口访问凭证
	      String accessToken = wechatAccessToken.getAccessToken();
	      // 用户标识
	      String openId = wechatAccessToken.getOpenId();
	      // 获取用户信息
	      WechatSNSUserInfo snsUserInfo = WechatAuthProcessUtil.getSNSUserInfo(accessToken, openId);
	      Map map = new HashMap();
	      map.put("wechatAccessToken",wechatAccessToken);
	      map.put("snsUserInfo",snsUserInfo);
	      String s = JSON.toJSONString(map);
	      // 用户unionid
	      String unionId = snsUserInfo.getUnionid();
	      //把字符串存redis里面
	      //RedisUtil.set("WeChat"+unionId,s);
	      //访问数据库操作
	      //直接登录
	      result=openId;
	      return result;
	   }
	   return result;
	}
}
