package com.paishop.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paishop.dao.CollectMapper;
import com.paishop.entity.Collect;
import com.paishop.manager.CollectManager;
@Service
public class CollectManagerImpl implements CollectManager{
@Autowired
private CollectMapper collectMapper;
	public void deleteCollectInfo(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public void insert(Collect record) {
		// TODO Auto-generated method stub
		
	}

	public Collect findCollectInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public void modifyCollectInfo(Collect record) {
		
		
	}

	public Collect findUserCollectInfo(int uid, int pid) {
		Collect collect = collectMapper.selectUserCollectInfo(uid, pid);
		return collect;
	}
     
}
