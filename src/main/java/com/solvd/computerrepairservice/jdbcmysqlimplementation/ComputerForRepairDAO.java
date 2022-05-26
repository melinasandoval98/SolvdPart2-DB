package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IComputerForRepairDAO;
import com.solvd.computerrepairservice.model.ComputerForRepair;

public class ComputerForRepairDAO implements IComputerForRepairDAO {
	public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairDAO.class);
	private final String GET_BY_ID_QUERY = " ";
	private final String INSERT_QUERY = " ";
	private final String UPDATE_QUERY = " ";
	private final String REMOVE_QUERY = " ";
	private final String GET_ALL_VALUES_QUERY = " ";
	private ComputerDAO computerDAO;
	private UserDAO userDAO;
	private EmployeeIDDAO employeeIDDAO;
	private Connection connection;

	public ComputerForRepairDAO(ComputerDAO computerDAO, UserDAO userDAO, EmployeeIDDAO employeeIDDAO,
			Connection connection) {
		super();
		this.computerDAO = computerDAO;
		this.userDAO = userDAO;
		this.employeeIDDAO = employeeIDDAO;
		this.connection = connection;
	}

	private ComputerForRepair createComputerForRepair(ResultSet rs) {
		ComputerForRepair computerForRepair = null;
		try {
			long computerForRepairID = rs.getLong("id");
			computerForRepair = new ComputerForRepair(computerForRepairID,
					computerDAO.getEntityByID(computerForRepairID), userDAO.getEntityByID(computerForRepairID),
					employeeIDDAO.getEntityByID(computerForRepairID));
		} catch (SQLException e) {
			LOGGER.error("SQLEception catched", e);
		}
		return computerForRepair;
	}

	@Override
	public ComputerForRepair getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		ComputerForRepair computerForRepair = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()) {
				computerForRepair = createComputerForRepair(resultSet);
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
		return computerForRepair;
	}

	@Override
	public void insertEntity(ComputerForRepair entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			long nextComputerForRepairID = getAll().size() + 1;
			prepStat.setLong(1, getAll().size() + 1);
			prepStat.setLong(2, computerDAO.getEntityByID(nextComputerForRepairID).getComputerID());
			prepStat.setLong(3, userDAO.getEntityByID(nextComputerForRepairID).getUserID());
			prepStat.setLong(4, employeeIDDAO.getEntityByID(nextComputerForRepairID).getId());
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public void updateEntity(ComputerForRepair entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			long nextComputerForRepairID = entity.getComputerForRepairID();
			prepStat.setLong(1, getAll().size() + 1);
			prepStat.setLong(2, computerDAO.getEntityByID(nextComputerForRepairID).getComputerID());
			prepStat.setLong(3, userDAO.getEntityByID(nextComputerForRepairID).getUserID());
			prepStat.setLong(4, employeeIDDAO.getEntityByID(nextComputerForRepairID).getId());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Computer For Repair data of id = " + nextComputerForRepairID
						+ " has been updated successfully");
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
				LOGGER.info("Address data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public List<ComputerForRepair> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<ComputerForRepair> computersForRepair = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()) {
				computersForRepair.add(createComputerForRepair(resultSet));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next()) {
				computersForRepair.add(createComputerForRepair(resultSet));
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
		return computersForRepair;
	}

	@Override
	public List<ComputerForRepair> getComputerForRepairByUserID(long userID) {
		List<ComputerForRepair> computersForRepair = new ArrayList<>();

		try {
			computersForRepair = getAll().stream()
					.filter(computerForRepair -> computerForRepair.getclientID() == userID).toList();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return computersForRepair;
	}

}
