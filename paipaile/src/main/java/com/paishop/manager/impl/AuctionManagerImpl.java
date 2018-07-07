package com.paishop.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.AuctionMapper;
import com.paishop.entity.Auction;
import com.paishop.manager.AuctionManager;
@Service
public class AuctionManagerImpl implements AuctionManager {
   
	@Autowired
	private AuctionMapper auctionMapper;
	
	public void deleteAuctionById(Integer id) {
		// TODO Auto-generated method stub

	}

	public void addAuctionInfo(Auction record) {
		

	}

	public Auction findAuctionInfo(Integer id) {
		auctionMapper.selectByPrimaryKey(id);
		return null;
	}

	public void modifyAuctionInfo(Auction record) {
		// TODO Auto-generated method stub

	}

	public List<Auction> findAuctionInfoByUser(int uId, int offset, int pageSize) {
		/*List<Auction> auctionList = auctionMapper.se
				selectAuctionInfoByUser(uId, offset, pageSize);
		return auctionList;*/
		return null;
	}

	public Auction findAuctionInfoByPid(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
