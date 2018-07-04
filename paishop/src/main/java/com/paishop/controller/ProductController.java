package com.paishop.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paishop.entity.Product;
import com.paishop.manager.ProductManager;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductManager productManager;
	//首页商品查询
	@RequestMapping(value = "/findAllProducts", method = RequestMethod.GET)
	public @ResponseBody JSONArray showIndex(@RequestParam int uId, @RequestParam String openid,
			     @RequestParam int offset, @RequestParam int pageSize ) {
		      //String str = String.valueOf(uId);
		     // int lastNum = str.charAt(str.length()-1);
		    //
			List<Product> productList = productManager.findAllProducts(offset, pageSize);
			Map<String, Object> collectMap= new HashMap<String, Object>();
			for (Product product : productList) {
				collectMap.put("id", product.getId());
				collectMap.put("pfOne", product.getPfOne());
				collectMap.put("sId", product.getsId());
				collectMap.put("sUsername", product.getsUsername());
				collectMap.put("CollectNum", product.getCollectNum());
				collectMap.put("starNum", product.getpNum());
				collectMap.put("marketPrice", product.getpMarketPrice());
				collectMap.put("status", product.getStatus());
				Date saleTime=product.getSaleTime();
				Date date = new Date();
				long time = saleTime.getTime() - date.getTime();
				int hour = (int)time/(1000*60*60);
				int second = (int)(time%(1000*60*60))/(1000*60);
				String countTime=hour+":"+second;
				collectMap.put("countTime", countTime);
			}
			JSONArray jsArray = JSONArray.fromObject(collectMap);
			/*Map<String, Object> map= new HashMap<String, Object>();
			map.put("productList", collectMap);
			Random random= new Random();
			JSONArray jsArray=new JSONArray();
			for(int i=0;i<productList.size();i++) {
				int index=random.nextInt(productList.size()-1);
				jsArray.add(map.get(index));
			}*/
			
			
		  return jsArray;
	}
	//状态切换
	@RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
	public @ResponseBody JSONObject modifyProduct(@RequestParam int id,@RequestParam int status) {
		    Product product = new Product();
		    product.setStatus(status);
		    product.setId(id);
		    productManager.modifyProductInfo(product);
		    Product productResult = productManager.findProductById(id);
		   //System.out.println(productResult);
		    JSONObject jsArray = new JSONObject();
		    jsArray.element("id", productResult.getId());
		    jsArray.element("status", productResult.getStatus());
		    return jsArray;
	}
	
	//根据关键词搜索商品
		@RequestMapping(value = "/search_goods", method = RequestMethod.GET)
		public @ResponseBody JSONArray findGoodsByName(@RequestParam int uid,@RequestParam String openid,
				@RequestParam String keyword,@RequestParam int page_index, @RequestParam int page_num) {
			    
			    List<Product> productList = productManager.findGoodsByName(keyword, page_index, page_num);
			   //System.out.println(productResult);
			    JSONArray jsArray= new JSONArray();
			    
			    return jsArray;
	      

		}
}
