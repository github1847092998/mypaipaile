package com.paishop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paishop.entity.Product;

public interface ProductMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);
    
    List<Product> selectAllProducts(@Param("offset") int offset, @Param("pageSize") int pageSize);
    
    List<Product> selectProductsBySaler();
    
    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}