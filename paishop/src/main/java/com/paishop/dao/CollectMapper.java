package com.paishop.dao;

import com.paishop.entity.Collect;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);
    
    Collect findCollectInfoByUid(int uid);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}