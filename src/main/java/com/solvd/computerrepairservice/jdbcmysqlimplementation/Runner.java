package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IUserDAO;

public class Runner {
	public static final Logger LOGGER = LogManager.getLogger(Runner.class);

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://52.59.193.212:3306/Melina_Sandoval?user=root", "root",
				"devintern");
		IUserDAO userDAO = new UserDAO(conn);
		userDAO.getAll().stream().forEach(user -> LOGGER.info(user));
	}
}
