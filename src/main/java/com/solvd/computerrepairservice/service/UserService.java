package com.solvd.computerrepairservice.service;

import java.util.List;

import com.solvd.computerrepairservice.jdbcmysqlimplementation.UserDAO;
import com.solvd.computerrepairservice.model.ComputerForRepair;
import com.solvd.computerrepairservice.model.User;

public class UserService {
	List<ComputerForRepair> getComputersForRepairByClientID() {
		return null;

	}

	List<User> getAllEmployees() {
		return null;
//		RightJoin users, employee_id
	}

	List<User> getAllClients() {
//		employeeDAO.getAll()
		UserDAO userDAO = new UserDAO();
		userDAO.getAll().stream().filter(user -> !getAllEmployees().contains(user));
		return null;

	}

	ComputerForRepair getComputerForRepairByEmployeeID() {
		return null;

	}

}
