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
	public boolean modifyUser(User user);
/*	//修改用戶地址
	public void modifyUserAddress(User user);*/
	//删除用户
	public void deleteUserById(int userId);
	//查询一个用户
	public User findUserByOpenid(String openid);
	//模糊查询
	public PageModel<User> findAllUserByName(String username);
	//查询所有用户
    public PageModel<User> findAllUser(int offset, int pageSize);

}

