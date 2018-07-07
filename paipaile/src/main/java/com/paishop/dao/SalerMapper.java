package com.paishop.dao;

import com.paishop.entity.Saler;

public interface SalerMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Saler record);

    int insertSelective(Saler record);

    Saler selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(Saler record);

    int updateByPrimaryKey(Saler record);
}