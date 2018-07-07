package com.paishop.manager;

import java.util.List;
import java.util.Map;

import com.paishop.entity.Order;
import com.paishop.entity.Product;


public interface ProductManager {
	//删除商品信息
	public boolean deleteProductById(Integer id);
   //添加商品信息
	public boolean addProduct(Product product);
   //查询商品信息
	public  Product findProductById(Integer id);
	//查询商家的产品
	public  List<Product> findProductsBySaler(Integer id);
	//
	public  List<Product> findAllProducts(int offset, int pageSize);
    //修改商品信息
    public void modifyProductInfo(Product product);
    
    public List<Product>findGoodsByName(String keyword, int offset, int pageSize);
}
