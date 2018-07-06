package com.paishop.manager;

import com.paishop.entity.Collect;

public interface CollectManager {
	public void  deleteCollectInfo(Integer id);

	public void insert(Collect record);

	public Collect findCollectInfo(Integer id);
	
	public Collect findUserCollectInfo(int uid, int pid);

	public void modifyCollectInfo(Collect record);
}
