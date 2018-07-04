package com.paishop.manager;

import java.util.List;
import java.util.Map;

import com.paishop.entity.Order;
import com.paishop.entity.Product;


public interface ProductManager {
	//下架商品
	public boolean deleteProductById(Integer id);
   //上架商品
	public boolean addProduct(Product product);
   //通过id找到一个商品
	public  Product findProductById(Integer id);
	//找到一个商家下所有的商品
	public  List<Product> findProductsBySaler(Integer id);
	//找到所有的商品
	public  List<Product> findAllProducts(int offset, int pageSize);
    //修改商品
    public void modifyProductInfo(Product product);
}
