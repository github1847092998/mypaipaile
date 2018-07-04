package com.paishop.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.SalerMapper;
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
	// TODO Auto-generated method stub
	return 0;
}

public Saler findSalerById(Integer id) {
	// TODO Auto-generated method stub
	return null;
}

public List<Saler> findAllSalersInfo(Saler record) {
	// TODO Auto-generated method stub
	return null;
}

public int modifySalerInfo(Saler record) {
	// TODO Auto-generated method stub
	return 0;
}

	
     
}
