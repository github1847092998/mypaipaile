package com.paishop.manager;

import java.util.List;

import com.paishop.entity.Auction;

public interface AuctionManager {
	
	public void deleteAuctionById(Integer id);

    public int  addAuctionInfo(Auction record);

    public Auction findAuctionInfo(Integer id);
    
    public Auction findAuctionInfoByPid(Integer pid);
    
    public List<Auction> findAuctionInfoByUser(int uId, int offset, int pageSize);

    public int modifyAuctionInfo(Auction record);
}
