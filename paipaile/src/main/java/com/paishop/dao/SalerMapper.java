package com.paishop.dao;

import java.util.List;
import java.util.Map;

import com.paishop.entity.Product;
import com.paishop.entity.Saler;

public interface SalerMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Saler record);

    int insertSelective(Saler record);

    Saler selectByPrimaryKey(Integer sId);
    
    List<Product> selectProductsBySaler(Map<String, Object> map);

    int updateByPrimaryKeySelective(Saler record);

    int updateByPrimaryKey(Saler record);
}