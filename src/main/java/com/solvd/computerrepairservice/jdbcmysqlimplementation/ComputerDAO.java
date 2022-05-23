package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IComputerDAO;
import com.solvd.computerrepairservice.model.Computer;

public class ComputerDAO implements IComputerDAO {
	public static final Logger LOGGER = LogManager.getLogger(ComputerDAO.class);
	private final String GET_BY_ID_QUERY = " ";
	private final String INSERT_QUERY = " ";
	private final String UPDATE_QUERY = " ";
	private final String REMOVE_QUERY = " ";
	private final String GET_ALL_VALUES_QUERY = " ";
	private OperatingSystemDAO operatingSystemDAO;
	private ProcessorDAO processorDAO;
	private DataStorageDeviceTypeDAO dataStorageTypeDAO;
	private Connection connection;

	public ComputerDAO(OperatingSystemDAO operatingSystemDAO, ProcessorDAO processorDAO, Connection connection) {
		super();
		this.operatingSystemDAO = operatingSystemDAO;
		this.processorDAO = processorDAO;
		this.connection = connection;
	}

	private Computer createComputer(ResultSet rs) {
		Computer computer = null;
		try {
			long computerID = rs.getLong("id");
			computer = new Computer(computerID, rs.getString("model"), rs.getInt("year"),
					operatingSystemDAO.getEntityByID(computerID), processorDAO.getEntityByID(computerID),
					dataStorageTypeDAO.getEntityByID(computerID), rs.getString("data_storage_device_type_capacity"));
		} catch (SQLException e) {
			LOGGER.error("SQLEception catched", e);
		}
		return computer;
	}

	@Override
	public Computer getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		Computer computer = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()) {
				computer = createComputer(resultSet);
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
		return computer;
	}

	@Override
	public void insertEntity(Computer entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			long nextComputerID = getAll().size() + 1;
			prepStat.setLong(1, nextComputerID);
			prepStat.setString(2, entity.getComputerModel());
			prepStat.setInt(3, entity.getComputerYear());
			prepStat.setLong(4, operatingSystemDAO.getEntityByID(nextComputerID).getOperatingSystemID());
			prepStat.setLong(5, processorDAO.getEntityByID(nextComputerID).getProcesorID());
			prepStat.setLong(6, dataStorageTypeDAO.getEntityByID(nextComputerID).getDataStorageTypeID());
			prepStat.setString(7, entity.getComputerDataStorageDeviceCapacity());
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public void updateEntity(Computer entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setLong(1, entity.getComputerID());
			prepStat.setString(2, entity.getComputerModel());
			prepStat.setInt(3, entity.getComputerYear());
			prepStat.setLong(4, operatingSystemDAO.getEntityByID(entity.getComputerID()).getOperatingSystemID());
			prepStat.setLong(5, processorDAO.getEntityByID(entity.getComputerID()).getProcesorID());
			prepStat.setLong(6, dataStorageTypeDAO.getEntityByID(entity.getComputerID()).getDataStorageTypeID());
			prepStat.setString(7, entity.getComputerDataStorageDeviceCapacity());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Computers data of id = " + entity.getComputerID() + " has been updated successfully");
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
				LOGGER.info("Computers data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public List<Computer> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<Computer> computers = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()) {
				computers.add(createComputer(resultSet));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next()) {
				computers.add(createComputer(resultSet));
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
		return computers;
	}

}
