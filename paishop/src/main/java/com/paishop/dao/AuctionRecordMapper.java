package com.paishop.dao;

import com.paishop.entity.AuctionRecord;

public interface AuctionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuctionRecord record);

    int insertSelective(AuctionRecord record);

    AuctionRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuctionRecord record);

    int updateByPrimaryKey(AuctionRecord record);
}