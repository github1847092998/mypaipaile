package com.paishop.dao;

import com.paishop.entity.Auction;

public interface AuctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auction record);

    int insertSelective(Auction record);

    Auction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auction record);

    int updateByPrimaryKey(Auction record);
}