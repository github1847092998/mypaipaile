package com.paishop.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.paishop.entity.Order;
import com.paishop.entity.Product;
import com.paishop.entity.User;
import com.paishop.manager.OrderManager;
import com.paishop.manager.ProductManager;
import com.paishop.manager.UserManager;
import com.paishop.weixinpay.Constant;
import com.paishop.weixinpay.PayInfo;
import com.paishop.weixinpay.util.CommonUtils;
import com.paishop.weixinpay.util.HttpResult;
import com.paishop.weixinpay.util.HttpUtil;
import com.paishop.weixinpay.util.RandomUtils;
import com.paishop.weixinpay.util.TimeUtils;

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

	private static Logger log = Logger.getLogger(OrderController.class);

	@RequestMapping(value = "/addOrder", method = RequestMethod.GET)
	public @ResponseBody JSONObject addOrder(@RequestParam int pId, @RequestParam int uId, @RequestParam String address,
			@RequestParam String openid, @RequestParam int sId, @RequestParam String sUsername,
			@RequestParam String pName, @RequestParam String pPic, @RequestParam float marketPrice,
			@RequestParam float buyPrice, @RequestParam int coupon, @RequestParam String oSpec,
			@RequestParam String oExpress, @RequestParam String uRemark, @RequestParam int oStatus,
			@RequestParam float expressFee, @RequestParam float sumMoney) {
		Map<String, Object> map = new HashMap<String, Object>();
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setpId(pId);
		order.setuId(uId);
		order.setoAddress(address);
		order.setMarketPrice(marketPrice);
		order.setBuyPrice(buyPrice);
		order.setoStatus(oStatus);
		order.setUserRemark(uRemark);
		order.setoCreatetime(new Date());
		int i = orderManager.addOrderInfo(order);
		String result = "";
		if (i == 1) {
			map.put("ret", 1);
			map.put("msg", "订单添加成功");

		} else {
			map.put("ret", 0);
			map.put("msg", "订单添加失败");
		}
		JSONObject js = JSONObject.fromObject(map);
		return js;
	}

	// 查看用户的所有订单
	@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAllOrders(@RequestParam int uid, @RequestParam String wx_openid) {
		Map<String, Object> orderMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Order> orderList = orderManager.findAllOrderByUser(uid);
		for (Order order : orderList) {
			orderMap.put("order_id", order.getId());
			orderMap.put("good_id", order.getProduct().getpId());
			orderMap.put("good_title", order.getProduct().getpName());
			orderMap.put("good_market_price", order.getMarketPrice());
			orderMap.put("good_auction_price", order.getBuyPrice());
			orderMap.put("order_status", order.getoStatus());
		}
		map.put("order", orderMap);
		JSONObject js = JSONObject.fromObject(map);
		return js;

	}

	// 确认收货
	@RequestMapping(value = "/receiveGood", method = RequestMethod.GET)
	public @ResponseBody JSONObject receiveGood(@RequestParam int uid, @RequestParam String wx_openid,
			@RequestParam int oid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Order order = new Order();
		order.setoStatus(20);
		order.setoModifytime(new Date());
		order.setId("" + oid);
		int i = orderManager.updateOrderInfo(order);
		if (i == 1) {
			map.put("ret", 1);
			map.put("msg", "收货成功");
		} else {
			map.put("ret", 0);
			map.put("msg", "收货失败");
		}
		JSONObject js = JSONObject.fromObject(map);
		return js;
	}

	// 调用统一下单接口,返回给前端5个参数
	@RequestMapping(value="/prepay", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody JSONObject unifyOrder(String openid, ModelMap model, HttpServletRequest request) {
		// 随机字符串
		String randomNonceStr = RandomUtils.generateMixString(32);
		String clientIP = CommonUtils.getClientIp(request);
		// 通过统一下单接口获取预支付id
		JSONObject js = this.unifiedOrder(openid, clientIP, randomNonceStr);

		return js;
	}

	// 确认订单，调用支付
	@RequestMapping(value = "/pay", produces = "text/html;charset=UTF-8")
	public @ResponseBody String pay(String code, ModelMap model, HttpServletRequest request) {

		String content = null;
		Map map = new HashMap();
		ObjectMapper mapper = new ObjectMapper();

		boolean result = true;
		String info = "";

		log.error("\n======================================================");
		log.error("code: " + code);
		// 通过code获取到openid
		String openId = this.getOpenId(code);
		if (StringUtils.isBlank(openId)) {
			result = false;
			info = "获取到openId为空";
		} else {
			openId = openId.replace("\"", "").trim();

			String clientIP = CommonUtils.getClientIp(request);

			log.error("openId: " + openId + ", clientIP: " + clientIP);
			// 随机字符串
			String randomNonceStr = RandomUtils.generateMixString(32);
			// 通过统一下单接口获取预支付id
		     JSONObject js = this.unifiedOrder(openId, clientIP, randomNonceStr);
			// 如果出错，打印出错误信息
			log.error("prepayId: " + js.get("prepay_Id").toString());

			if (StringUtils.isBlank(js.get("prepay_Id").toString())) {
				result = false;
				info = "出错了，未获取到prepayId";
			} else {
				// 获取到了预支付id和随机字符串，保存到map中
				map.put("prepayId", js.get("prepay_Id").toString());
				map.put("nonceStr", randomNonceStr);
			}
		}
		try {
			// 把结果保存在map中
			map.put("result", result);
			map.put("info", info);
			content = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}

	// 私有的得到openid方法
	private String getOpenId(String code) {
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APP_ID + "&secret="
				+ Constant.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";

		HttpUtil httpUtil = new HttpUtil();
		try {

			HttpResult httpResult = httpUtil.doGet(url, null, null);

			if (httpResult.getStatusCode() == 200) {

				JsonParser jsonParser = new JsonParser();
				JsonObject obj = (JsonObject) jsonParser.parse(httpResult.getBody());

				log.error("getOpenId: " + obj.toString());

				if (obj.get("errcode") != null) {
					log.error("getOpenId returns errcode: " + obj.get("errcode"));
					return "";
				} else {
					return obj.get("openid").toString();
				}
				// return httpResult.getBody();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private JSONObject unifiedOrder(String openId, String clientIP, String randomNonceStr) {
            Map<String, Object> map = new HashMap<String, Object>();
            JSONObject js = new JSONObject();
		try {

			String url = Constant.URL_UNIFIED_ORDER;
			// 将支付信息注入到实体类中
			PayInfo payInfo = createPayInfo(openId, clientIP, randomNonceStr);
			// 将支付信息加密
			String md5 = getSign(payInfo);
			// 获取加密后的签名
			payInfo.setSign(md5);

			log.error("md5 value: " + md5);
			// 把支付信息转成xml
			String xml = CommonUtils.payInfoToXML(payInfo);

			xml = xml.replace("__", "_").replace("<![CDATA[1]]>", "1");
			// xml = xml.replace("__", "_").replace("<![CDATA[", "").replace("]]>", "");
			log.error(xml);
			// 使用https请求方法请求统一下单接口
			StringBuffer buffer = HttpUtil.httpsRequest(url, "POST", xml);

			log.error("unifiedOrder request return body: \n" + buffer.toString());
			// 把获取到的字节流解析成xml
			Map<String, String> result = CommonUtils.parseXml(buffer.toString());
			/* 获取到返回码：结果为SUCCESS的时候，会返回
			 * 小程序ID appid 
			 * 商户号 mch_id 
			 * 设备号 device_info 
			 * 随机字符串 nonce_str 
			 * 签名 sign 
			 * 业务结果 result_code 
			 * 错误代码 err_code 
			 * 错误代码描述 err_code_des
			 * 
			 */
			String return_code = result.get("return_code");
			if (StringUtils.isNotBlank(return_code) && return_code.equals("SUCCESS")) {

				String return_msg = result.get("return_msg");
				
				if (StringUtils.isNotBlank(return_msg) && !return_msg.equals("OK")) {
					// log.error("统一下单错误！");
					map.put("ret", 0);
					map.put("msg", "统一下单失败");
					return js.fromObject(map.toString());
				}
				String appId =result.get("appId");
				long timeStamp = new Date().getTime();
                String nonce_str =result.get("nonce_str");
				String prepay_Id = result.get("prepay_id");
				String signType = "MD5";
				String stringSignTemp="appId="+appId+"&nonce_str="+nonce_str+"&prepay_Id="+prepay_Id+"&signType="
				+signType+"&timeStamp="+timeStamp;
                String paySign=CommonUtils.getMD5((stringSignTemp.trim()).toUpperCase());
                map.put("ret", 1);
				map.put("msg", "统一下单成功");
				map.put("timeStamp", timeStamp);
				map.put("nonce_str", nonce_str);
				map.put("prepay_Id", prepay_Id);
				map.put("signType", signType);
				map.put("paySign", paySign);
				return js.fromObject(map.toString());

			} else {
				map.put("ret", 0);
				map.put("msg", "统一下单失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return js.fromObject("下单失败");
	}
	
	/*public String md5(String signTemp) {
		String sign="";
		try {	 
			String[] signFields = new String[5];
			signFields[0] = "appId";
			signFields[1] = "nonce_str";
			signFields[2] = "prepay_Id";
			signFields[3] = "signType";
			signFields[4] = "timeStamp";
			JSONObject param = (JSONObject) JSONObject.toJSON(signTemp);
			// 生成签名原文
			String signSrc = orgSignSrc(signFields, param);
			// MD5的方式签名
			signSrc += "&KEY=" + key;
			genSign = MD5Encrypt.getMessageDigest(signSrc);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genSign;

		return null;
	}*/

	private PayInfo createPayInfo(String openId, String clientIP, String randomNonceStr) {

		Date date = new Date();
		String timeStart = TimeUtils.getFormatTime(date, Constant.TIME_FORMAT);
		String timeExpire = TimeUtils.getFormatTime(TimeUtils.addDay(date, Constant.TIME_EXPIRE), Constant.TIME_FORMAT);

		String randomOrderId = CommonUtils.getRandomOrderId();

		PayInfo payInfo = new PayInfo();
		payInfo.setAppid(Constant.APP_ID);
		payInfo.setMch_id(Constant.MCH_ID);
		payInfo.setDevice_info("WEB");
		payInfo.setNonce_str(randomNonceStr);
		payInfo.setSign_type("MD5"); // 默认即为MD5
		payInfo.setBody("xxx");
		payInfo.setAttach("附加数据");
		payInfo.setOut_trade_no(randomOrderId);
		payInfo.setTotal_fee(1);
		payInfo.setSpbill_create_ip(clientIP);
		payInfo.setTime_start(timeStart);
		payInfo.setTime_expire(timeExpire);
		payInfo.setNotify_url(Constant.URL_NOTIFY);
		payInfo.setTrade_type("JSAPI");
		payInfo.setLimit_pay("no_credit");
		payInfo.setOpenid(openId);

		return payInfo;
	}

	private String getSign(PayInfo payInfo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("appid=" + payInfo.getAppid()).append("&attach=" + payInfo.getAttach())
				.append("&body=" + payInfo.getBody()).append("&device_info=" + payInfo.getDevice_info())
				.append("&limit_pay=" + payInfo.getLimit_pay()).append("&mch_id=" + payInfo.getMch_id())
				.append("&nonce_str=" + payInfo.getNonce_str()).append("&notify_url=" + payInfo.getNotify_url())
				.append("&openid=" + payInfo.getOpenid()).append("&out_trade_no=" + payInfo.getOut_trade_no())
				.append("&sign_type=" + payInfo.getSign_type())
				.append("&spbill_create_ip=" + payInfo.getSpbill_create_ip())
				.append("&time_expire=" + payInfo.getTime_expire()).append("&time_start=" + payInfo.getTime_start())
				.append("&total_fee=" + payInfo.getTotal_fee()).append("&trade_type=" + payInfo.getTrade_type())
				.append("&key=" + Constant.APP_KEY);

		log.error("排序后的拼接参数：" + sb.toString());
		// 签名
		return CommonUtils.getMD5(sb.toString().trim()).toUpperCase();
	}
}
