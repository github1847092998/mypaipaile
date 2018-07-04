package com.paishop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paishop.entity.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> selectAllOrderByUser(@Param("uId") int uId, @Param("oStatus") int oStatus, @Param("offset") int offset, @Param("pageSize") int pageSize);
    
    List<Order> selectAllOrderByUserUId(@Param("uId") int uId, @Param("offset") int offset, @Param("pageSize") int pageSize);
}