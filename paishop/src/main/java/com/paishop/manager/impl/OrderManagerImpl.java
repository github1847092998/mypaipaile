package com.paishop.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.OrderMapper;
import com.paishop.entity.Order;
import com.paishop.manager.OrderManager;
@Service
public class OrderManagerImpl implements OrderManager {

	@Autowired
	private OrderMapper orderMapper;
	
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addOrderInfo(Order record) {
		orderMapper.insertSelective(record);
		return false;
	}

	public Order findOrderById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateOrderInfo(Order record) {
		// TODO Auto-generated method stub
		return false;
	}
//0:寰呬粯娆� 1锛氬緟鍙戣揣 2锛氬緟鏀惰揣 3锛氬緟璇勪环 4锛氬緟鍞悗
	public List<Order> findAllOrderByUser(int uId, int oStatus,  int offset, int pageSize) {
		List<Order> orderList = orderMapper.selectAllOrderByUser(uId, oStatus, offset, pageSize);
		return orderList;
	}

	public List<Order> findUserOrderByUId(int uId, int offset, int pageSize) {
		List<Order> allOrderList = orderMapper.selectAllOrderByUserUId(uId, offset, pageSize);
		return allOrderList;
	}

}
