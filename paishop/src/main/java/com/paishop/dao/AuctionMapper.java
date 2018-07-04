package com.paishop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paishop.entity.Auction;

public interface AuctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auction record);

    int insertSelective(Auction record);

    Auction selectByPrimaryKey(Integer id);
    
    List<Auction> selectAuctionInfoByUser(@Param("uId") int uId,@Param("offset") int offset, @Param("pageSize") int pageSize);

    int updateByPrimaryKeySelective(Auction record);

    int updateByPrimaryKey(Auction record);
}