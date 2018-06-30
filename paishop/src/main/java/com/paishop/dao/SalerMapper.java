package com.paishop.dao;

import com.paishop.entity.Saler;

public interface SalerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Saler record);

    int insertSelective(Saler record);

    Saler selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Saler record);

    int updateByPrimaryKey(Saler record);
}