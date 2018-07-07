package com.paishop.manager.impl;

import java.util.List;

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

	public boolean deleteProductById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	public Product findProductById(Integer id) {
		Product product = productMapper.selectByPrimaryKey(id);
		return product;
	}

	public List<Product> findProductsBySaler(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> findAllProducts(int offset, int pageSize) {
		/*List<Product> productList = productMapper.selectAllProducts(offset, pageSize);
		return productList;*/
		return null;
	}

	public void modifyProductInfo(Product product) {
		productMapper.updateByPrimaryKeySelective(product);
	}

	public List<Product> findGoodsByName(String keyword, int offset, int pageSize) {
		// 
		/*List<Product> productList = productMapper.selectGoodsByName(keyword, offset, pageSize);
		return productList;*/
		return null;
	}
	
	


	
     
}
