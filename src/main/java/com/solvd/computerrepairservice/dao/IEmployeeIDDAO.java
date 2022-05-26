package com.solvd.computerrepairservice.dao;

import com.solvd.computerrepairservice.model.EmployeeID;
import com.solvd.computerrepairservice.model.User;

public interface IEmployeeIDDAO extends IBaseDAO<EmployeeID> {
	EmployeeID getUserByEmployeeID(long employeeID)
}
