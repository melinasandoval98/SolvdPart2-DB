package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.Date;
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
	private final String GET_BY_ID_QUERY = "SELECT * FROM Computers_For_Repair WHERE id = ?";
	private final String INSERT_QUERY = "INSERT INTO Computers_For_Repair (computer_id, client_id, computer_repairer_id entry_date) VALUES (?,?,?,?)";
	private final String UPDATE_QUERY = "UPDATE Computers_For_Repair SET computer_id = ?, client_id = ?,computer_repairer_id = ?, entry_date = ?";
	private final String REMOVE_QUERY = "DELETE FROM Computers_For_Repair WHERE id = ?";
	private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Computers_For_Repair";
	private ComputerDAO computerDAO;
	private UserDAO userDAO;
	private UserDAO employeeDAO;
	private Connection connection;

	public ComputerForRepairDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	private ComputerForRepair createComputerForRepair(ResultSet rs) {
		ComputerForRepair computerForRepair = null;
		try {
			computerForRepair = new ComputerForRepair(rs.getLong("id"),
					computerDAO.getEntityByID(rs.getLong("computer_id")), rs.getDate("entry_date"),
					userDAO.getEntityByID(rs.getLong("client_id")).getUserID(),
					userDAO.getEntityByID(rs.getLong("computer_repairer_id")).getUserID());
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
			prepStat.setLong(1, entity.getComputer().getComputerID());
			prepStat.setLong(2, entity.getClientID());
			prepStat.setLong(3, entity.getRepairerID());
			prepStat.setDate(4, (Date) entity.getEntryDate());
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
			prepStat.setLong(1, entity.getComputer().getComputerID());
			prepStat.setLong(2, entity.getClientID());
			prepStat.setLong(3, entity.getRepairerID());
			prepStat.setDate(4, (Date) entity.getEntryDate());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Computer For Repair data of id = " + entity.getComputerForRepairID()
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
					.filter(computerForRepair -> computerForRepair.getClientID() == userID).toList();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return computersForRepair;
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(UserDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
