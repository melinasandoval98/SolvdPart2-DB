package com.solvd.computerrepairservice.dao;

import com.solvd.computerrepairservice.model.EmployeeID;

public interface IEmployeeIDDAO extends IBaseDAO<EmployeeID> {

	EmployeeID getUserByEmployeeID(long employeeID);
}
