package com.paishop.manager;

import java.util.List;
import java.util.Map;

import com.paishop.entity.Order;


public interface OrderManager {
	
	public int deleteById(Integer id);

	public int addOrderInfo(Order record);

	public  Order findOrderById(Integer id);

    public int updateOrderInfo(Order record);

    public  List<Order> findAllOrderByUser(int uId);
    
    public  List<Order> findUserOrderByUId(int uId, int offset, int pageSize);
}
