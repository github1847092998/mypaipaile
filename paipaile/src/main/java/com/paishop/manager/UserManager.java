package com.paishop.manager;

import java.util.List;

import com.paishop.entity.Auction;
import com.paishop.entity.Order;
import com.paishop.entity.User;

public interface UserManager {
	//娣诲姞鐢ㄦ埛
	public boolean addUser(User user);
	//淇敼鐢ㄦ埛
	public boolean modifyUser(User user);
/*	//淇敼鐢ㄦ埗鍦板潃
	public void modifyUserAddress(User user);*/
	//鍒犻櫎鐢ㄦ埛
	public void deleteUserById(int userId);
	//鏌ヨ涓�涓敤鎴�
	public User findUserByOpenid(String openid);
	//妯＄硦鏌ヨ

}

