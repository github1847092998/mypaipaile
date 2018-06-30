package com.paishop.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.UserMapper;
import com.paishop.entity.Auction;
import com.paishop.entity.Order;
import com.paishop.entity.PageModel;
import com.paishop.entity.User;
import com.paishop.manager.UserManager;
@Service
public class UserManagerImpl implements UserManager{
	@Autowired
	private UserMapper userMapper;

	public boolean addUser(User user) {
		 boolean b = userMapper.insertSelective(user);
		 return b;
		
	}
    //修改用户收货地址：从null到现在的值
	public void modifyUser(User user) {
		
		
	}

	public void deleteUserById(int userId) {
		// TODO Auto-generated method stub
		
	}

	public User findUserById(int userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	public PageModel<User> findAllUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	public PageModel<User> findAllUser(int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	public PageModel<Auction> findAuctionInfoByUserId(int userId) {
		//通过用户id找到属于这个用户的所有竞拍
		PageModel<Auction> auctionList = (PageModel<Auction>) userMapper.findAuctionInfoByUserId(userId);
		return auctionList;
	}
	
	public PageModel<Order> findAllOrderByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


}
