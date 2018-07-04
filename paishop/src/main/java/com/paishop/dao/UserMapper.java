package com.paishop.dao;

import java.util.List;

import com.paishop.entity.Auction;
import com.paishop.entity.Order;
import com.paishop.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    boolean insertSelective(User user);

    User selectByOpenid(String openid);

    boolean updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User record);
    
    List<User> selectAllUser();
    
    List<User> selectAllUserByName(String username);
    
    
    List<Auction> findAuctionInfoByUserId(int userId);
}