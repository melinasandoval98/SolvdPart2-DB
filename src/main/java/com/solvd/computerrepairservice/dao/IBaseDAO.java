package com.solvd.computerrepairservice.dao;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<T> {

	T getEntityByID(long id);

	void insertEntity(T entity);

	void updateEntity(T entity);

	void removeEntity(long id);

	List<T> getAll() throws SQLException;

}
