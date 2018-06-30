package com.paishop.dao;

import java.util.List;

import com.paishop.entity.Auction;
import com.paishop.entity.Order;
import com.paishop.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    boolean insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectAllUser();
    
    List<User> selectAllUserByName(String username);
    
    List<Order> selectOrderByUser(int userId);
    
    List<Auction> findAuctionInfoByUserId(int userId);
}