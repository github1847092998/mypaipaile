package com.paishop.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.ProductMapper;
import com.paishop.dao.SalerMapper;
import com.paishop.entity.Product;
import com.paishop.entity.Saler;
import com.paishop.manager.ProductManager;
import com.paishop.manager.SalerManager;
@Service
public class ProductManagerImpl implements ProductManager{
   
	@Autowired
	private ProductMapper productMapper;

	public int deleteProductById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Product findProductById(Integer id) {
		Product product = productMapper.selectByPrimaryKey(id);
		return product;
	}

	public List<Product> findProductsBySaler(Integer sid, int status, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("status", status);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		List<Product> productList = productMapper.selectProductsBySaler(map);
		return productList;
	}

	public List<Product> findAllProducts(int offset, int pageSize) {
		/*List<Product> productList = productMapper.selectAllProducts(offset, pageSize);
		return productList;*/
		return null;
	}

	public int modifyProductInfo(Product product) {
		int i = productMapper.updateByPrimaryKeySelective(product);
		return i;
	}

	public List<Product> findGoodsByName(String keyword, int offset, int pageSize) {
		// 
		/*List<Product> productList = productMapper.selectGoodsByName(keyword, offset, pageSize);
		return productList;*/
		return null;
	}
	
	


	
     
}
