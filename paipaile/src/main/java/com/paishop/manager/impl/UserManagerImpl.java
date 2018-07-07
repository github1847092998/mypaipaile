package com.paishop.manager.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.UserMapper;

import com.paishop.entity.User;
import com.paishop.manager.UserManager;
@Service
public class UserManagerImpl implements UserManager{
	@Autowired
	private UserMapper userMapper;

	public boolean addUser(User user) {
		/* boolean b = userMapper.insertSelective(user);
		return b;*/
		return false;
	}
    //淇敼鐢ㄦ埛鏀惰揣鍦板潃锛氫粠null鍒扮幇鍦ㄧ殑鍊�
	public boolean modifyUser(User user) {
		/*boolean b = userMapper.updateByPrimaryKeySelective(user);
		return b;*/
		return false;
	}

	public void deleteUserById(int userId) {
		// TODO Auto-generated method stub
		
	}

	public User findUserByOpenid(String openid) {
		/*User user = userMapper.selectByOpenid(openid);
		return user;*/
		return null;
	}

	/*public List<User> findAllUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<User> findAllUser(int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}*/
	public void modifyUserAddress(User user) {
		 userMapper.updateByPrimaryKeySelective(user);
		
	}
	


}

