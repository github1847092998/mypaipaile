package com.paishop.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
import com.paishop.entity.User;
import com.paishop.manager.AuctionManager;
import com.paishop.manager.ProductManager;
import com.paishop.manager.UserManager;
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
	@Autowired
	private UserManager userManager;
	//@Autowired
	//private MyHandler handler;
	//首页商品查询
	@RequestMapping(value = "/getAllGoods", method = RequestMethod.GET)
	public @ResponseBody JSONArray showIndex(@RequestParam int offset, @RequestParam int pageSize) {
		     //@RequestParam int uId, @RequestParam String openid,
	        //@RequestParam int offset, @RequestParam int pageSize
		    Map<String, Object> map= new HashMap<String, Object>(); 
		    Map<String, Object> goodMap= new HashMap<String, Object>(); 
		    Map<String, Object> infoMap= new HashMap<String, Object>(); 
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
			goodMap.put("good_detail", map);
			goodMap.put("total", productList.size());
			infoMap.put("data", goodMap);
			infoMap.put("msg", "成功");
			infoMap.put("ret", 1);
			JSONArray jsonArray = JSONArray.fromObject(infoMap);
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
		    		map.put("auction", "可以收藏");
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
		
		//竞拍
		@RequestMapping(value = "/auctionGood", method = RequestMethod.GET)
		 public @ResponseBody JSONObject auctionGood(HttpServletRequest request) {
			//@RequestParam int uid,
			//@RequestParam String openid, @RequestParam int good_id
			Map<String, Object> map= new HashMap<String, Object>();
			   int auction_pv=0;
			   Date auctionTime;
			   double auctionPrice = Double.parseDouble(request.getParameter("auctionPrice"));
			   int pid=Integer.parseInt(request.getParameter("good_id"));
			   Product product1 = this.findProduct(pid);
			    Product product = new Product();
			    Auction auction = new Auction();
			    
			    if(request.getRemoteAddr()!=null && !"".equals(request.getRemoteAddr().toString())) {
			    //if(request.getRequestURL()!=null && !"".equals(request.getRequestURL().toString())) {
			    	 auction_pv++;
			    	 auctionPrice=auctionPrice*1.1;
			    	 auctionTime = new Date();
			    }
			    System.out.println("auction_pv:"+auction_pv);
			    if(auction_pv==1) {
			    	
				    auction.setpId(Integer.parseInt(request.getParameter("good_id")));
				    auction.setuId(Integer.parseInt(request.getParameter("uid")));
				    auction.setAutionPv(auction_pv);
				    auction.setAuctionPrice((float)auctionPrice);
				    //竞拍中
				    auction.setStatus(Integer.parseInt(request.getParameter("status")));
				    auction.setAuctionName(request.getParameter("auctionName"));
				    auction.setpPrice(product1.getpMarketPrice());
				    
				    auction.setStartTime(product1.getSaleTime());
				    auction.setCreateTime(new Date());
				   //System.out.println(productResult);
				    int i = auctionManager.addAuctionInfo(auction);
				    //Product product2 = productManager.findProductById(pid);
				   if(i==1) {
					   map.put("ret",1);
					   map.put("msg", "出价成功");
					   
				   }else {
					   map.put("ret",0);
					   map.put("msg", "出价失败");
				   }
			    }else if(auction_pv>1) {
			    	auction.setuId(Integer.parseInt(request.getParameter("uid")));
			    	int uid = Integer.parseInt(request.getParameter("uid"));
			    	User user = userManager.findUserByUid(uid);
			    	auction.setpId(Integer.parseInt(request.getParameter("good_id")));
			    	auction.setAuctionName(user.getNicknae());
			    	auction.setAuctionPrice((float)auctionPrice);
			    	auction.setAuctionTime(new Date());
			    	int i = auctionManager.modifyAuctionInfo(auction);
			    	if(i==1) {
						   map.put("ret",1);
						   map.put("msg", "出价成功");
						   
					   }else {
						   map.put("ret",0);
						   map.put("msg", "出价失败");
					   }
			    }
			    
			   JSONObject js = JSONObject.fromObject(map);
			    return js;
		}
		
		/*public int countAuctionPv() {
			int i=0;
			return i;
		}*/
		
		public Product findProduct(int pid) {
			Product product = productManager.findProductById(pid);
			return product;
		}
		public User findUser(int uid ) {
			User user = userManager.findUserByUid(uid);
			return user;
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
		
		        //商品下架
				@RequestMapping(value = "/deleteGoods", method = RequestMethod.GET)
				public @ResponseBody JSONObject findGoodsByName(@RequestParam int sid,
						@RequestParam int pid) {
					Map<String, Object> map= new HashMap<String, Object>();
					   //System.out.println(productList);
					   int i = productManager.deleteProductById(pid);
					   if(i==1) {
						  map.put("ret", i);
						  map.put("msg", "下架成功");
					   }else {
						   map.put("ret", i);
						   map.put("msg", "下架失败");
					   }
					    JSONObject js = JSONObject.fromObject(map);
					   
					    return js;
				}
				//商品删除
				@RequestMapping(value = "/changeGoodsStatus", method = RequestMethod.GET)
				public @ResponseBody JSONObject changeGoodsStatus(@RequestParam int sid,
						@RequestParam int status, @RequestParam int pid) {
					Map<String, Object> map= new HashMap<String, Object>();
					   //System.out.println(productList);
					Product product = new Product();
					product.setStatus(status);
					product.setpId(pid);
					   int i = productManager.modifyProductInfo(product);
					   if(i==1) {
						  map.put("ret", i);
						  map.put("msg", "删除成功");
					   }else {
						   map.put("ret", i);
						   map.put("msg", "删除失败");
					   }
					    JSONObject js = JSONObject.fromObject(map);
					   
					    return js;
				}
				
				 //商品详情查询
				/*@RequestMapping(value = "/auction", method = RequestMethod.POST)
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
					    
					    handler.sendMessageToUsers(new TextMessage(map.toString()));
						JSONObject js = JSONObject.fromObject(map);
					    return js;
				}*/
		
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
