package com.paishop.manager;

import java.util.List;

import com.paishop.entity.Auction;
import com.paishop.entity.Order;
import com.paishop.entity.PageModel;
import com.paishop.entity.User;

public interface UserManager {
	//添加用户
	public boolean addUser(User user);
	//修改用户
	public void modifyUser(User user);
	//删除用户
	public void deleteUserById(int userId);
	//查询一个用户
	public User findUserById(int userId);
	//模糊查询
	public PageModel<User> findAllUserByName(String username);
	//查询所有用户
    public PageModel<User> findAllUser(int offset, int pageSize);
    //查询出属于这个用户的所有订单
    PageModel<Order> findAllOrderByUser(int userId);
    //通过用户id找到属于这个用户的所有竞拍
    public PageModel<Auction> findAuctionInfoByUserId(int userId);

}
