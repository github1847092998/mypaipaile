package com.paishop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.paishop.entity.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);
    
    List<Order> selectAllOrderByUser(Map<String, Object> map);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}