package com.paishop.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Order> findAllOrderByUser(int uId, int offset, int pageSize) {
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("uId", uId);
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		List<Order> orderList = orderMapper.selectAllOrderByUser(map);
		return orderList;
	}

	public List<Order> findUserOrderByUId(int uId, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
