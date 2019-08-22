package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.IPageble;

public interface INewDAO extends IGenericDAO<NewModel> {
	List<NewModel> findByCategoryId(Long categoryId);
	Long save(NewModel newModel);
	NewModel findOne(Long id);
	void update(NewModel updateNew);
	void delete(long id);
	List<NewModel> findAll(IPageble pageble);
	int getTotalItem();
}
