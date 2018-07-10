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
	
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int addOrderInfo(Order record) {
		int i = orderMapper.insertSelective(record);
		return i;
	}

	public Order findOrderById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateOrderInfo(Order record) {
		int i = orderMapper.updateByPrimaryKeySelective(record);
		return i;
	}

	public List<Order> findAllOrderByUser(int uId) {
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("uId", uId);
		List<Order> orderList = orderMapper.selectAllOrderByUser(map);
		return orderList;
	}

	public List<Order> findUserOrderByUId(int uId, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
