package com.paishop.dao;

import com.paishop.entity.ProductType;

public interface ProductTypeMapper {
    int deleteByPrimaryKey(Integer pfOne);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(Integer pfOne);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);
}