package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IEmployeeWorkScheduleDAO;
import com.solvd.computerrepairservice.model.EmployeeWorkSchedule;

public class EmployeeWorkScheduleDAO implements IEmployeeWorkScheduleDAO {
	public static final Logger LOGGER = LogManager.getLogger(EmployeeWorkScheduleDAO.class);
	private final String GET_BY_ID_QUERY = " ";
	private final String INSERT_QUERY = " ";
	private final String UPDATE_QUERY = " ";
	private final String REMOVE_QUERY = " ";
	private final String GET_ALL_VALUES_QUERY = " ";
	private EmployeeDAO employeeIDDAO;
	private WorkScheduleDAO workScheduleDAO;
	private Connection connection;

	public EmployeeWorkScheduleDAO(EmployeeDAO employeeIDDAO, WorkScheduleDAO workScheduleDAO,
                                   Connection connection) {
		super();
		this.employeeIDDAO = employeeIDDAO;
		this.workScheduleDAO = workScheduleDAO;
		this.connection = connection;
	}

	private EmployeeWorkSchedule createEmployeeWorkSchedule(ResultSet rs) {
		EmployeeWorkSchedule employeeWorkSchedule = null;
		try {
			long employeeWorkScheduleID = rs.getLong("id");
			employeeWorkSchedule = new EmployeeWorkSchedule(employeeWorkScheduleID,
					employeeIDDAO.getEntityByID(employeeWorkScheduleID),
					workScheduleDAO.getEntityByID(employeeWorkScheduleID));
		} catch (SQLException e) {
			LOGGER.error("SQLException caught", e);
		}
		return employeeWorkSchedule;
	}

	@Override
	public EmployeeWorkSchedule getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		EmployeeWorkSchedule employeeWorkSchedule = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()) {
				employeeWorkSchedule = createEmployeeWorkSchedule(resultSet);
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException caught", e);
		} finally {
			if (prepStat != null) {
				try {
					prepStat.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException caught while closing the PreparedStatement connection", e);
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException caught while closing the ResultSet connection", e);
				}
			}
		}
		return employeeWorkSchedule;
	}

	@Override
	public void insertEntity(EmployeeWorkSchedule entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			long nextEmployeeWorkScheduleID = getAll().size() + 1;
			prepStat.setLong(1, nextEmployeeWorkScheduleID);
			prepStat.setLong(2, employeeIDDAO.getEntityByID(nextEmployeeWorkScheduleID).getEmployeeID());
			prepStat.setLong(3, workScheduleDAO.getEntityByID(nextEmployeeWorkScheduleID).getId());
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException caught", e);
		}

	}

	@Override
	public void updateEntity(EmployeeWorkSchedule entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			long nextEmployeeWorkScheduleID = entity.getId();
			prepStat.setLong(1, nextEmployeeWorkScheduleID);
			prepStat.setLong(2, employeeIDDAO.getEntityByID(nextEmployeeWorkScheduleID).getEmployeeID());
			prepStat.setLong(3, workScheduleDAO.getEntityByID(nextEmployeeWorkScheduleID).getId());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Employee Work Schedule data of id = " + entity.getId() + " has been updated successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException caught", e);
		}

	}

	@Override
	public void removeEntity(long id) {
		try (PreparedStatement prepStat = connection.prepareStatement(REMOVE_QUERY)) {
			prepStat.setLong(1, id);
			prepStat.executeUpdate();
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Employee Work Schedule data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException caught", e);
		}
	}

	@Override
	public List<EmployeeWorkSchedule> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<EmployeeWorkSchedule> employeeWorkSchedule = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()) {
				employeeWorkSchedule.add(createEmployeeWorkSchedule(resultSet));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException caught", e);
		} finally {
			if (resultSet.next()) {
				employeeWorkSchedule.add(createEmployeeWorkSchedule(resultSet));
			} else {
				throw new SQLException();
			}
			if (prepStat != null) {
				try {
					prepStat.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException caught while closing the PreparedStatement connection", e);
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException caught while closing the ResultSet connection", e);
				}
			}
		}
		return employeeWorkSchedule;
	}

}
