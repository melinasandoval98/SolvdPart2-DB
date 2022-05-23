package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IEmployeeIDDAO;
import com.solvd.computerrepairservice.model.EmployeeID;

public class EmployeeIDDAO implements IEmployeeIDDAO {
	public static final Logger LOGGER = LogManager.getLogger(EmployeeIDDAO.class);
	private final String GET_BY_ID_QUERY = " ";
	private final String INSERT_QUERY = " ";
	private final String UPDATE_QUERY = " ";
	private final String REMOVE_QUERY = " ";
	private final String GET_ALL_VALUES_QUERY = " ";
	private UserDAO userDAO;
	private Connection connection;

	public EmployeeIDDAO(UserDAO userDAO, Connection connection) {
		super();
		this.userDAO = userDAO;
		this.connection = connection;
	}

	private EmployeeID createEmployeeID(ResultSet rs) {
		EmployeeID employeeID = null;
		try {
			long userID = rs.getLong("id");
			employeeID = new EmployeeID(userID, rs.getLong("employee_id"), userDAO.getEntityByID(userID));
		} catch (SQLException e) {
			LOGGER.error("SQLEception catched", e);
		}
		return employeeID;
	}

	@Override
	public EmployeeID getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		EmployeeID employeeID = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()) {
				employeeID = createEmployeeID(resultSet);
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (prepStat != null) {
				try {
					prepStat.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException catched while closing the PreparedStatement connection", e);
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException catched while closing the ResultSet connection", e);
				}
			}
		}
		return employeeID;
	}

	@Override
	public void insertEntity(EmployeeID entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			long nextEmployeeID = getAll().size() + 1;
			prepStat.setLong(1, nextEmployeeID);
			prepStat.setLong(2, entity.getEmployeeID());
			prepStat.setLong(3, userDAO.getEntityByID(nextEmployeeID).getUserID());
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public void updateEntity(EmployeeID entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setLong(1, entity.getId());
			prepStat.setLong(2, entity.getEmployeeID());
			prepStat.setLong(3, userDAO.getEntityByID(entity.getId()).getUserID());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Employee ID's data of id = " + entity.getId() + " has been updated successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public void removeEntity(long id) {
		try (PreparedStatement prepStat = connection.prepareStatement(REMOVE_QUERY)) {
			prepStat.setLong(1, id);
			prepStat.executeUpdate();
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Employee ID's data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public List<EmployeeID> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<EmployeeID> employeeIDs = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()) {
				employeeIDs.add(createEmployeeID(resultSet));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next()) {
				employeeIDs.add(createEmployeeID(resultSet));
			} else {
				throw new SQLException();
			}
			if (prepStat != null) {
				try {
					prepStat.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException catched while closing the PreparedStatement connection", e);
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException catched while closing the ResultSet connection", e);
				}
			}
		}
		return null;
	}

}
