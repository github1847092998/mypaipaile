package com.paishop.manager;

import com.paishop.entity.Collect;

public interface CollectManager {
	public void  deleteCollectInfo(Integer id);

	public void insert(Collect record);

	public Collect findCollectInfo(Integer id);
	
	public Collect findCollectInfoByUid(int uid);

	public void modifyCollectInfo(Collect record);
}
