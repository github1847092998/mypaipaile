package com.paishop.manager;

import java.util.List;

import com.paishop.entity.Auction;

public interface AuctionManager {
	
	public void deleteAuctionById(Integer id);

    public void  addAuctionInfo(Auction record);

    public Auction findAuctionInfo(Integer id);
    
    public List<Auction> findAuctionInfoByUser(int uId, int offset, int pageSize);

    public void modifyAuctionInfo(Auction record);
}
