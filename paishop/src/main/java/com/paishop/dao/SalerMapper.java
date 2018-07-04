package com.paishop.dao;

import com.paishop.entity.Saler;
import com.sun.tools.javac.util.List;

public interface SalerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Saler record);

    int insertSelective(Saler record);

    Saler selectByPrimaryKey(Integer id);
    
    List<Saler> selectAllSalers();

    int updateByPrimaryKeySelective(Saler record);

    int updateByPrimaryKey(Saler record);
}