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
	public Collect findCollectInfoByUid(int uid) {
		Collect collectInfo = collectMapper.findCollectInfoByUid(uid);
		return collectInfo;
	}

	public void modifyCollectInfo(Collect record) {
		
		
	}
     
}
