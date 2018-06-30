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
import com.paishop.entity.User;
import com.paishop.manager.UserManager;
import com.paishop.util.AesCbcUtil;
import com.paishop.util.HttpRequest;

import net.sf.json.JSONObject;



@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserManager userManager;
	//��ȡ��΢���û���Ϣ������
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public @ResponseBody Map addUserInfo(String encryptedData, String iv, String code) {
		Map map = new HashMap();
	    //��¼ƾ֤����Ϊ��
	    if (code == null || code.length() == 0) {
	        map.put("status", 0);
	        map.put("msg", "code ����Ϊ��");
	        return map;
	    }
	    //С����Ψһ��ʶ   (��΢��С��������̨��ȡ)
	    String wxspAppid = "***********************";
	    //С����� app secret (��΢��С��������̨��ȡ)
	    String wxspSecret = "************************";
	    //��Ȩ�����
	    String grant_type = "***************************";


	    //////////////// 1����΢�ŷ����� ʹ�õ�¼ƾ֤ code ��ȡ session_key �� openid ////////////////
	    //�������
	    String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
	    //��������
	    String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
	    //������Ӧ���ݣ�ת����json����
	    JSONObject json = JSONObject.fromObject(sr);
	    //��ȡ�Ự��Կ��session_key��
	    String session_key = json.get("session_key").toString();
	    //�û���Ψһ��ʶ��openid��
	    String openid = (String) json.get("openid");

	    //////////////// 2����encryptedData�������ݽ���AES���� ////////////////
	    try {
	        String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
	        if (null != result && result.length() > 0) {
	            map.put("ret", 1);
	            map.put("msg", "�ɹ�");
	            JSONObject userInfoJSON = JSONObject.fromObject(result);
	            String openId=(String)userInfoJSON.get("openId");
	            String unionid=(String)userInfoJSON.get("unionId");
	            String nickName=(String)userInfoJSON.get("nickName");
	            String gender=(String)userInfoJSON.get("gender");
	            int gender1=Integer.parseInt(gender);
	            String city=(String)userInfoJSON.get("city");
	            String province=(String)userInfoJSON.get("province");
	            String country=(String)userInfoJSON.get("country");
	            String avatarUrl=(String)userInfoJSON.get("avatarUrl");
	            User user = new User();
	            user.setOpenid(openId);


	            UserController contro=new UserController();
	            contro.addUserInfo(user);
	            return map;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    map.put("ret", 0);
	    map.put("msg", "ʧ��");
	    return map;
	}
	
	public void addUserInfo(User user) {
		
	    
	}
	
	@RequestMapping(value = "/addUser1", method = RequestMethod.GET)
	public @ResponseBody String addUser(@RequestParam String openid, @RequestParam String unionid, @RequestParam String nickname, 
			@RequestParam int gender, @RequestParam String city, @RequestParam String province, @RequestParam String country, 
			@RequestParam String avatarUrl) {
		    User user =new User();
		    
		    user.setOpenid(openid);
		    user.setUnionid(unionid);
            user.setNickname(nickname);
            user.setGender(gender);
            user.setCity(city);
            user.setProvince(province);
            user.setCountry(country);
            user.setAvatarUrl(avatarUrl);
            
            boolean bo = userManager.addUser(user);
            if(bo==true) {
            	String res="�ɹ�";
            	return res;
            }else {
            	String res="ʧ��";
            	return res;
            }
            
	       
	}
	//��������ҳ��
	@RequestMapping(value = "/findUserInfo", method = RequestMethod.GET)
	public @ResponseBody JSONObject findUserInfo(@RequestBody int userId) {
		User user = userManager.findUserById(userId);
		String nickname=user.getNickname();
		String avatarUrl=user.getAvatarUrl();
		List<Order> orderList = user.getOrder();
		
		for (Order order : orderList) {
			int orderId=order.getId();
			int pId=order.getpId();
			int uId=order.getuId();
			float marketPrice = order.getMarketPrice();
			float buyPrice = order.getBuyPrice();
			int oStatus = order.getoStatus();
			String oAddress=order.getoAddress();		
		}
		JSONObject js = JSONObject.fromObject(user);
		return js;	
		
	}
	//��ӻ��޸��û���ַ
	@ResponseBody
	@RequestMapping(value = "/addAddress", method = RequestMethod.GET)
	public void modifyUser(@RequestParam(value="detailAddress") String detailAddress, @RequestParam(value="openid") String openid) {
		User user = new User();
		user.setDetailAddress(detailAddress);
		user.setOpenid(openid);
		userManager.modifyUser(user);		
	}
	    //�ҵľ���
	@ResponseBody
	@RequestMapping(value = "/myAuction", method = RequestMethod.GET)
	public JSONObject auction(@RequestParam(value="userId") int userId) {
		List<Auction> auctionList = (List<Auction>) userManager.findAuctionInfoByUserId(userId);
		JSONObject js = JSONObject.fromObject(auctionList);
		return js;				
	}
	
	
	@RequestMapping(value = "/addAddress")
	public @ResponseBody String addAddress(@RequestParam String openid) {
		   String userId = openid;
			return userId;	
	}
	
	/*@RequestMapping("/addUser1")
	public String addUser1(@RequestParam String username) {
		ModelAndView mv = new ModelAndView("common/pub_add_success");
			return "success";	
	}*/
	
}
