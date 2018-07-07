package com.paishop.manager;

import java.util.List;
import java.util.Map;

import com.paishop.entity.Order;


public interface OrderManager {
	
	public boolean deleteById(Integer id);

	public boolean addOrderInfo(Order record);

	public  Order findOrderById(Integer id);

    public boolean updateOrderInfo(Order record);

    public  List<Order> findAllOrderByUser(int uId, int offset, int pageSize);
    
    public  List<Order> findUserOrderByUId(int uId, int offset, int pageSize);
}
