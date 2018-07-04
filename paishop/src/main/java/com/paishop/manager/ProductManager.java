package com.paishop.manager;

import java.util.List;
import java.util.Map;

import com.paishop.entity.Order;
import com.paishop.entity.Product;


public interface ProductManager {
	//涓嬫灦鍟嗗搧
	public boolean deleteProductById(Integer id);
   //涓婃灦鍟嗗搧
	public boolean addProduct(Product product);
   //閫氳繃id鎵惧埌涓�涓晢鍝�
	public  Product findProductById(Integer id);
	//鎵惧埌涓�涓晢瀹朵笅鎵�鏈夌殑鍟嗗搧
	public  List<Product> findProductsBySaler(Integer id);
	//鎵惧埌鎵�鏈夌殑鍟嗗搧
	public  List<Product> findAllProducts(int offset, int pageSize);
    //淇敼鍟嗗搧
    public void modifyProductInfo(Product product);
    
    public List<Product>findGoodsByName(String keyword, int offset, int pageSize);
}
