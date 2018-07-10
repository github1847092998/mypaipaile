package com.paishop.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.SalerMapper;
import com.paishop.entity.Product;
import com.paishop.entity.Saler;
import com.paishop.manager.SalerManager;
@Service
public class SalerManagerImpl implements SalerManager{
	
@Autowired
private SalerMapper salerMapper;

public int deleteSalerById(Integer id) {
	// TODO Auto-generated method stub
	return 0;
}

public int addSaler(Saler record) {
	int i = salerMapper.insertSelective(record);
	return i;
}

public Saler findSalerById(Integer id) {
	// TODO Auto-generated method stub
	return null;
}

public List<Saler> findAllSalersInfo(Saler record) {
	// TODO Auto-generated method stub
	return null;
}

public List<Product> findAllProductsBySaler(int sid, int status) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("sid", sid);
	map.put("status", status);
	List<Product> productList = salerMapper.selectProductsBySaler(map);
	return null;
}

public int modifySalerInfo(Saler record) {
	int i = salerMapper.updateByPrimaryKeySelective(record);
	return i;
}

	
     
}
