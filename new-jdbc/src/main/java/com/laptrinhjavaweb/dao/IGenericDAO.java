package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.IRowMapper;

public interface IGenericDAO<T> {
	List<T> query(String sql, IRowMapper<T> rowMapper,Object... parameters);
	void update(String sql,Object... parameters);
	Long insert(String sql,Object... parameters);
	int count(String sql,Object... parameters);
}
