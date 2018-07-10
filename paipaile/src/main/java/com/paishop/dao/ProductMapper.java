package com.paishop.dao;

import java.util.List;
import java.util.Map;

import com.paishop.entity.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer pId);
    
    List<Product> selectProductsBySaler(Map<String, Object> map);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}