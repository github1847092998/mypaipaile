package com.paishop.dao;



import org.apache.ibatis.annotations.Param;

import com.paishop.entity.Collect;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);
    
    Collect selectUserCollectInfo(@Param("uid")int uid, @Param("pid")int pid);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}