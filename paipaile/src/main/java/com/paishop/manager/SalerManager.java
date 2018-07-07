package com.paishop.manager;

import java.util.List;

import com.paishop.entity.Saler;

public interface SalerManager {
	
	public int deleteSalerById(Integer id);

	public int addSaler(Saler record);

	public Saler findSalerById(Integer id);

	public List<Saler> findAllSalersInfo(Saler record);

	public int modifySalerInfo(Saler record);
}
