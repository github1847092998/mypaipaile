package com.paishop.manager;

import java.util.List;

import com.paishop.entity.Auction;
import com.paishop.entity.Order;
import com.paishop.entity.PageModel;
import com.paishop.entity.User;

public interface UserManager {
	//����û�
	public boolean addUser(User user);
	//�޸��û�
	public void modifyUser(User user);
	//ɾ���û�
	public void deleteUserById(int userId);
	//��ѯһ���û�
	public User findUserById(int userId);
	//ģ����ѯ
	public PageModel<User> findAllUserByName(String username);
	//��ѯ�����û�
    public PageModel<User> findAllUser(int offset, int pageSize);
    //��ѯ����������û������ж���
    PageModel<Order> findAllOrderByUser(int userId);
    //ͨ���û�id�ҵ���������û������о���
    public PageModel<Auction> findAuctionInfoByUserId(int userId);

}
