package com.paishop.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.paishop.entity.Auction;
import com.paishop.entity.Product;
import com.paishop.manager.AuctionManager;
import com.paishop.manager.ProductManager;
import com.paishop.websocket.MyHandler;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/goods")
//@MessageMapping("")
public class ProductController {
	@Autowired
	private ProductManager productManager;
	@Autowired
	private AuctionManager auctionManager;
	//@Autowired
	//private MyHandler handler;
	//首页商品查询
	@RequestMapping(value = "/getAllGoods", method = RequestMethod.GET)
	public @ResponseBody JSONArray showIndex(@RequestParam int offset, @RequestParam int pageSize) {
		     //@RequestParam int uId, @RequestParam String openid,
	        //@RequestParam int offset, @RequestParam int pageSize
		    Map<String, Object> map= new HashMap<String, Object>(); 
			List<Product> productList = productManager.findAllProducts(offset, pageSize);
			for (Product product : productList) {
				    map.put("id", product.getpId());
				    map.put("titile", product.getpName());
				    map.put("media_url", product.getMediaMain());
				    map.put("sale_price", product.getpSalePrice());
				    map.put("market_price", product.getpMarketPrice());
				    //库存
				    map.put("repertory", product.getpNum());
			}
			JSONArray jsonArray = JSONArray.fromObject(map);
		    return jsonArray;
	}
	     //商品详情查询
	    //@SendTo
		@RequestMapping(value = "/getGoodById", method = RequestMethod.GET)
		public @ResponseBody JSONObject showProductDetail(@RequestParam int good_id) {
			    Map<String, Object> map= new HashMap<String, Object>();
			    Product product = productManager.findProductById(good_id);
			    System.out.println(product);
			    //System.out.println(product.getAuction().getAuctionName());
			    map.put("id", product.getpId());
			    map.put("sid", product.getsId());
			    map.put("title", product.getpName());
			    map.put("url_list", product.getMediaMain());
		    	map.put("detail_url", product.getMediaDetail());
		    	map.put("market_price", product.getpMarketPrice());
		    	map.put("repertory", product.getpNum());
			    //开始拍卖的时间
		    	if(product.getAuction()!=null) {
		    		map.put("start_time", product.getSaleTime());   
				    map.put("auction_id", product.getAuction().getId());
			    	map.put("action_pv", product.getAuction().getAutionPv());
			    	map.put("auction_person", product.getAuction().getAuctionName());
			    	map.put("auction_time", product.getAuction().getAuctionTime());
			    	map.put("auction_price", product.getAuction().getAuctionPrice());
			    	map.put("status", product.getAuction().getStatus());//正在竞拍，竞拍结束
			    	System.out.println(product.getAuction().getAuctionName());
		    	}else {
		    		map.put("auction", "");
		    	}
		    	
			    //如果当前商品正在拍卖
			   /* if(product.getStatus()==20) {
			    	Auction auction = auctionManager.findAuctionInfoByPid(good_id);
			    	System.out.println(auction);
			    	map.put("auction_id", auction.getId());
			    	map.put("action_pv", auction.getAutionPv());
			    	map.put("auction_person", auction.getAuctionName());
			    	map.put("auction_time", auction.getAuctionTime());
			    	map.put("auction_price", auction.getAuctionPrice());
			    	map.put("status", auction.getStatus());//正在竞拍，竞拍结束
			    }*/
			    
				JSONObject js = JSONObject.fromObject(map);
			    return js;
		}
		
		 //商品详情查询
		@RequestMapping(value = "/auction", method = RequestMethod.POST)
		public @ResponseBody JSONObject auction(@RequestParam int good_id) {
			    Map<String, Object> map= new HashMap<String, Object>();
			    Product product = productManager.findProductById(good_id);
			    System.out.println(product);
			    System.out.println(product.getAuction());
			    //System.out.println(product.getAuction().getAuctionName());
			    map.put("id", product.getpId());
			    map.put("sid", product.getsId());
			    map.put("title", product.getpName());
			    map.put("url_list", product.getMediaMain());
		    	map.put("detail_url", product.getMediaDetail());
		    	map.put("market_price", product.getpMarketPrice());
		    	map.put("repertory", product.getpNum());
			    //开始拍卖的时间
			    map.put("start_time", product.getSaleTime());   
			    map.put("auction_id", product.getAuction().getId());
		    	map.put("action_pv", product.getAuction().getAutionPv());
		    	map.put("auction_person", product.getAuction().getAuctionName());
		    	map.put("auction_time", product.getAuction().getAuctionTime());
		    	map.put("auction_price", product.getAuction().getAuctionPrice());
		    	map.put("status", product.getAuction().getStatus());//正在竞拍，竞拍结束
		    	System.out.println(product.getAuction().getAuctionName());
			    
			    /*handler.sendMessageToUsers(new TextMessage(map.toString()));*/
				JSONObject js = JSONObject.fromObject(map);
			    return js;
		}
		
		//竞拍
		@RequestMapping(value = "/auctionGood", method = RequestMethod.GET)
		 public @ResponseBody JSONObject modifyProduct(@RequestParam int id,@RequestParam int status) {
			    Product product = new Product();
			    //如果到拍卖时间
			    product.setStatus(status);
			    product.setpId(id);
			    productManager.modifyProductInfo(product);
			    Product productResult = productManager.findProductById(id);
			   //System.out.println(productResult);
			    JSONObject jsArray = new JSONObject();
			    jsArray.element("id", productResult.getpId());
			    jsArray.element("status", productResult.getStatus());
			    return jsArray;
		}
		 //根据关键词搜索商品
		@RequestMapping(value = "/search_goods", method = RequestMethod.GET)
		public @ResponseBody JSONArray findGoodsByName(@RequestParam String openid,
				@RequestParam String keyword,@RequestParam int page_index, @RequestParam int page_num) {
			Map<String, Object> map= new HashMap<String, Object>();
			List<Product> productList = productManager.findGoodsByName(keyword, page_index, page_num);
			
			   System.out.println(productList);
			   for (Product product : productList) {
				    map.put("id", product.getpId());
			    	map.put("sid", product.getsId());
			    	map.put("p_name", product.getpName());
			    	map.put("media_main", product.getMediaMain());
					 
				}
			    JSONArray jsArray= new JSONArray();
			    jsArray.add(map);
			    return jsArray;
		}
		
		/*//商品添加
				@RequestMapping(value = "/addProducts", method = RequestMethod.GET)
				public @ResponseBody String addProducts(@RequestParam int id, @RequestParam int pfOne,
						@RequestParam int sId, @RequestParam String sUsername,@RequestParam String pName,
						@RequestParam int pNum, @RequestParam int pAuctionNum, @RequestParam float pMarketPrice, 
						@RequestParam float pSalePrice, @RequestParam JSONObject mediaMain, 
						@RequestParam JSONObject mediaDetail, @RequestParam JSONObject pSpec,
						@RequestParam String pDesc, @RequestParam int status, @RequestParam Date saleTime) {
					    String result="";
					    Product product = new Product();
					    product.setId(id);
					    product.setPfOne(pfOne);
					    product.setsId(sId);
					    product.setsUsername(sUsername);
					    product.setpName(pName);
					    product.setpNum(pNum);
					    product.setpAuctionNum(pAuctionNum);
					    product.setpMarketPrice(pMarketPrice);
					    product.setpSalePrice(pSalePrice);
					    product.setMediaMain(mediaMain.toString());
					    product.setMediaMain(mediaDetail.toString());
					    product.setpSpec(pSpec.toString());
					    product.setpDesc(pDesc);
					    product.setStatus(1);
					    product.setSaleTime(saleTime);
					    product.setCreateTime(new Date());
					    boolean bo = productManager.addProduct(product);
						if(bo==true) {
							 return "商品上架成功";
						}else {
							return "失败，请重新上架商品";
						}
					   
				}*/
	
	//状态切换
		/*@RequestMapping(value = "/auctionGood", method = RequestMethod.GET)
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
	}*/
	
	   
		
		
}
