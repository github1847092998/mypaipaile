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

import com.paishop.entity.Auction;
import com.paishop.entity.Collect;
import com.paishop.entity.Product;
import com.paishop.manager.AuctionManager;
import com.paishop.manager.CollectManager;
import com.paishop.manager.ProductManager;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductManager productManager;
	@Autowired
	private AuctionManager auctionManager;
	@Autowired
	private CollectManager collectManager;
	
	//首页商品查询
	@RequestMapping(value = "/showIndex", method = RequestMethod.GET)
	public @ResponseBody JSONArray showIndex(@RequestParam int uId, @RequestParam String openid,
			     @RequestParam int offset, @RequestParam int pageSize ) {
		      //String str = String.valueOf(uId);
		     // int lastNum = str.charAt(str.length()-1);
		    Map<String, Object> collectMap= new HashMap<String, Object>();
		    Map<String, Object> auctionMap= new HashMap<String, Object>();
			List<Product> productList = productManager.findAllProducts(offset, pageSize);
			JSONArray jsonArray = JSONArray.fromObject(productList);
		    return jsonArray;
	}
	     //商品详情查询
		@RequestMapping(value = "/showProductDetail", method = RequestMethod.GET)
		public @ResponseBody JSONArray showProductDetail(@RequestParam int uid, @RequestParam String openid,
				@RequestParam int pid) {
			    Map<String, Object> map= new HashMap<String, Object>();
			    Product product = productManager.findProductById(pid);
			    System.out.println(product);
			    //System.out.println(product.getAuction().getAuctionName());
			    map.put("id", product.getId());
			    map.put("sid", product.getsId());
			    map.put("pName", product.getpName());
			    map.put("pPic", product.getpSpec());
			   
			    map.put("marketPrice", product.getpMarketPrice());
			    map.put("auctionId", "");
		    	map.put("userId", "");
		    	map.put("auctionPv", "");
		    	map.put("cuPrice", product.getpSalePrice()*0.8);
		    	//System.out.println(product.getpSalePrice());
			    /*if(product.getAuction()==null) {
			    	map.put("auctionId", "");
			    	map.put("userId", "");
			    	map.put("auctionPv", "");
			    	map.put("cuPrice", (float)product.getpSalePrice()*0.8);
			    }else {
			    	map.put("auctionId", product.getAuction().getId());
			    	map.put("userId", product.getAuction().getuId());
			    	//map.put("userId", product.getAuction().getuId());
			    	//map.put("auctionPv", product.getAuction().getAutionPv());
			    	map.put("cuPrice", product.getAuction().getAuctionPrice());
			    }*/
				JSONArray jsArray = JSONArray.fromObject(map);
			    return jsArray;
		}
		
		//商品添加
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
					   
				}
	
	/*@RequestMapping(value = "/showIndex", method = RequestMethod.GET)
	public @ResponseBody JSONArray showIndex(@RequestParam int uId, @RequestParam String openid,
			     @RequestParam int offset, @RequestParam int pageSize ) {
		      //String str = String.valueOf(uId);
		     // int lastNum = str.charAt(str.length()-1);
		    Map<String, Object> collectMap= new HashMap<String, Object>();
		    Map<String, Object> auctionMap= new HashMap<String, Object>();
			List<Product> productList = productManager.findAllProducts(offset, pageSize);
			for (Product product : productList) {
				if(product.getStatus()==1) {
					//如果商品还没有开卖
					
				}
				if(product.getStatus()==2) {
					//如果商品正在竞拍
					Auction auction = auctionManager.findAuctionInfo(product.getId());
					auctionMap.put("aucId", auction.getId());
					auctionMap.put("aucName", auction.getAuctionName());
					auctionMap.put("aucStage", auction.getAuctionStage());
					auctionMap.put("aucPrice", auction.getAuctionPrice());
				}
			}
			
			for (Product product : productList) {
				collectMap.put("id", product.getId());
				collectMap.put("pfOne", product.getPfOne());
				collectMap.put("sId", product.getsId());
				collectMap.put("sUsername", product.getsUsername());
				collectMap.put("CollectNum", product.getCollectNum());
				collectMap.put("starNum", product.getpNum());
				collectMap.put("marketPrice", product.getpMarketPrice());
				collectMap.put("status", product.getStatus());
				collectMap.put("auction", auctionMap);
				Date saleTime=product.getSaleTime();
				Date date = new Date();
				long time = saleTime.getTime() - date.getTime();
				int hour = (int)time/(1000*60*60);
				int second = (int)(time%(1000*60*60))/(1000*60);
				String countTime=hour+":"+second;
				collectMap.put("countTime", countTime);
			}
			JSONArray jsArray = JSONArray.fromObject(collectMap);
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("productList", collectMap);
			Random random= new Random();
			JSONArray jsArray=new JSONArray();
			for(int i=0;i<productList.size();i++) {
				int index=random.nextInt(productList.size()-1);
				jsArray.add(map.get(index));
			}
		  return jsArray;
	}*/
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
		public @ResponseBody JSONArray findGoodsByName(@RequestParam String openid,
				@RequestParam String keyword,@RequestParam int page_index, @RequestParam int page_num) {
			JSONObject js = new JSONObject();
			JSONObject js1 = new JSONObject();
			JSONObject js2 = new JSONObject();
			JSONObject js3 = new JSONObject();
			List<Product> productList = productManager.findGoodsByName(keyword, page_index, page_num);
			
			   System.out.println(productList);
			   for (Product product : productList) {
					//Auction auction = auctionManager.findAuctionInfoByPid(product.getId());
					//Collect collect = collectManager.findCollectInfoByUid(auction.getuId());	
					/*js3.put("title", product.getpName());
					js3.put("media_url", product.getMediaMain());
					js3.put("star", product.getStarNum());
					js3.put("comment",product.getCountComment());
					js3.put("market_price",product.getpMarketPrice());
					js3.put("repertory", product.getpNum());
					js3.put("collect_cnt", product.getCollectNum());*/
				   
				   //Collect collect = new ProductController().findUserCollectInfo(uid, product.getId());
				  // System.out.println(collect);
					/*if(collect!=null) {
						js3.put("is_collect", 1);
					}else {
						js3.put("is_collect", 0);
					}*/
					/*js2.put("play_cnt",auction.getAuctionUv());
					js2.put("start_time",product.getSaleTime());
					js2.put("countdown", 50);
					js2.put("auction_person", 3);
					js2.put("auction_time",auction.getAuctionTime());
					js2.put("auction_stage",auction.getAuctionStage());
					js2.put("auction_stage_ratio",auction.getAuctionStage());
					js2.put("start_price", product.getpMarketPrice()*0.8);
					js2.put("auction_person", 3);
					js2.put("auction_price",auction.getAuctionPrice());*/
					 js1.put("total", 10);
					 js1.put("num", 3);
					 js1.put("good_detail", js3);
					 js1.put("auction", js2);
				}
				js.put("ret", 0);
				js.put("msg", "success");
				js.put("products", js1);
				
			   //System.out.println(productResult);
			    JSONArray jsArray= new JSONArray();
			    jsArray.add(productList);
			    return jsArray;
		}
		
		public Collect findUserCollectInfo(int uid, int pid) {
			Collect collect = collectManager.findUserCollectInfo(uid, pid);
			return collect;
		}
}
