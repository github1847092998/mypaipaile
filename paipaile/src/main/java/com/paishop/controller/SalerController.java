package com.paishop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paishop.dao.ProductMapper;
import com.paishop.entity.Product;
import com.paishop.entity.Saler;
import com.paishop.manager.ProductManager;
import com.paishop.manager.SalerManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/saler")
public class SalerController {
	@Autowired
	private SalerManager salerManager;
	@Autowired
	private ProductManager productManager;
	//添加商家信息
	@RequestMapping("/addSaler")
      public @ResponseBody JSONObject addSalerInfo(@RequestParam String wx_openid){
		Saler saler = new Saler();
		saler.setWxOpenid(wx_openid);
		saler.setCreateTime(new Date());
		int i = salerManager.addSaler(saler);
		System.out.println(i);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ret", i);
		map.put("msg", "添加成功");
		JSONObject js=  JSONObject.fromObject(map);
    	  return js;
      }
	//修改商家信息
	@RequestMapping("/modifySaler")
    public @ResponseBody JSONObject modifySaler(@RequestParam int sid,
    		@RequestParam String wx_openid, @RequestParam String s_telephone,
    		@RequestParam float s_money){
		Saler saler = new Saler();
		saler.setsId(sid);
		saler.setWxOpenid(wx_openid);
		saler.setsMoney(s_money);
		saler.setsTelephone(s_telephone);
		saler.setModifyTime(new Date());
		int i = salerManager.modifySalerInfo(saler);
		System.out.println(i);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ret", i);
		map.put("msg", "修改商家信息成功");
		JSONObject js=  JSONObject.fromObject(map);
  	  return js;
    }
	//查询商家正在出售的商品或者在仓库中的商品
	@RequestMapping("/findProductsBySaler")
    public @ResponseBody JSONArray findProductsBySaler(@RequestParam int sid,
    		@RequestParam String wx_openid,@RequestParam int status,
    		@RequestParam int offset, @RequestParam int pageSize){
			//3条数据
			List<Product> productList = productManager.findProductsBySaler(sid, status, offset, pageSize);
			System.out.println(productList);
		    Map<String, Object> map = new HashMap<String, Object>();
		    Map<String, Object> productMap = new HashMap<String, Object>();
		    Product product = new Product();
		    JSONArray js = new JSONArray();
		    for(Iterator iterator = productList.iterator(); iterator.hasNext();) {  
		    	product=(Product) iterator.next();
                map.put("pid", product.getpId());
		    	
		    	map.put("sid", product.getsId());
		    	//商品名称
		    	map.put("pName", product.getpName());
		    	//主图
		    	map.put("pPic", product.getMediaMain());
		    	//市场价
		    	map.put("marketPrice", product.getpMarketPrice());
		    	//定价
		    	map.put("fixedPrice", product.getpSalePrice());
		    	
		    	//System.out.println(map);
		    	js.add(map);
		    }
		    
		    return js;
    }
	
	     
	
}
