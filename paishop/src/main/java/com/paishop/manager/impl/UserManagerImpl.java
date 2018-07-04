package com.paishop.manager.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.UserMapper;

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
	public boolean modifyUser(User user) {
		boolean b = userMapper.updateByPrimaryKeySelective(user);
		return b;
	}

	public void deleteUserById(int userId) {
		// TODO Auto-generated method stub
		
	}

	public User findUserByOpenid(String openid) {
		User user = userMapper.selectByOpenid(openid);
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
	public void modifyUserAddress(User user) {
		 userMapper.updateByPrimaryKeySelective(user);
		
	}
	


}

